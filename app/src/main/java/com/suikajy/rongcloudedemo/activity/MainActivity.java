package com.suikajy.rongcloudedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.suikajy.rongcloudedemo.R;
import com.suikajy.rongcloudedemo.event.ReceiveMessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

import static com.suikajy.rongcloudedemo.Const.DEBUG_TOKEN;
import static com.suikajy.rongcloudedemo.Const.DEBUG_TOKEN2;
import static com.suikajy.rongcloudedemo.Const.DEBUG_USER_ID;
import static com.suikajy.rongcloudedemo.Const.DEBUG_USER_ID2;

public class MainActivity extends AppCompatActivity implements RongIMClient.OnReceiveMessageListener {

    private Button mBtnSubConversationList;
    private Button mBtnConversationList;
    private Button mBtnConversation;
    private Button mBtnCustomMessageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RongIM.setOnReceiveMessageListener(this);
        initView();
        initClick();
        connectRongIM();
    }

    private void initClick() {
        mBtnConversationList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Boolean> supportedConversation = new HashMap<>();
                supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false);
                supportedConversation.put(Conversation.ConversationType.GROUP.getName(), true);
                RongIM.getInstance().startConversationList(MainActivity.this, supportedConversation);
            }
        });
        mBtnConversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RongIM.getInstance().startPrivateChat(MainActivity.this, DEBUG_USER_ID2, "聊以自慰");
            }
        });
        mBtnSubConversationList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RongIM.getInstance().startSubConversationList(MainActivity.this, Conversation.ConversationType.PRIVATE);
            }
        });
        mBtnCustomMessageList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomConversationListActivity.class));
            }
        });
    }

    private void connectRongIM() {
        RongIM.connect(DEBUG_TOKEN, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.e("**** TAG ****", "onTokenIncorrect");
            }

            @Override
            public void onSuccess(String s) {
                Log.e("**** TAG ****", "onSuccess: s is " + s);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("**** TAG ****", "onError: errorCode is " + errorCode.getValue() + "\nerrorMessage is " + errorCode.getMessage());
            }
        });
    }

    private void initView() {
        mBtnSubConversationList = (Button) findViewById(R.id.btn_sub_conversation_list);
        mBtnConversationList = (Button) findViewById(R.id.btn_conversation_list);
        mBtnConversation = (Button) findViewById(R.id.btn_conversation);
        mBtnCustomMessageList = (Button) findViewById(R.id.btn_custom_message_list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RongIM.getInstance().disconnect();
    }

    @Override
    public boolean onReceived(Message message, int i) {
        Log.e("**** TAG ****", "onReceived\n" + i + "\n" +
                "message.getExtra()" + message.getExtra() + "\n" +
                "message.getContent()" + ((TextMessage) message.getContent()).getContent() + "\n" +
                "message.getObjectName()" + message.getObjectName() + "\n" +
                "message.getSenderUserId()" + message.getSenderUserId() + "\n" +
                "message.getTargetId()" + message.getTargetId() + "\n" +
                "message.getUId()" + message.getUId() + "\n" +
                "message.getMessageDirection().getValue()" + message.getMessageDirection().getValue() + "\n" +
                "message.getReceivedTime()" + message.getReceivedTime() + "\n" +
                "message.getSentTime()" + message.getSentTime() + "\n" +
                "message.getReadReceiptInfo()" + message.getReadReceiptInfo()
        );
        EventBus.getDefault().post(new ReceiveMessageEvent(message));
//                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(App.this);
//                builder.setSmallIcon(R.mipmap.ic_launcher);
//                builder.setContentTitle(
//                        "user id为空"
//                );
//                builder.setContentText("content" + message.getExtra());
//                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
//                builder.setLargeIcon(icon);
//                builder.setAutoCancel(true);
//                builder.setTicker("来通知了..");
//                Notification notification = builder.build();
//                manager.notify(RONG_IM_CHANNEL_ID, notification);
        return false;
    }
}
