package com.suikajy.rongcloudedemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.suikajy.rongcloudedemo.R;
import com.suikajy.rongcloudedemo.adapter.MyMessageListAdapter;
import com.suikajy.rongcloudedemo.event.NotifyEvent;
import com.suikajy.rongcloudedemo.event.ReceiveMessageEvent;
import com.suikajy.rongcloudedemo.utils.MySwipeMenuCreator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by zjy on 2017/9/23.
 */

public class MessageFragment extends Fragment {
    private String TAG = "MessageFragment";
    private SwipeMenuListView mListView;
    private MyMessageListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
        initView(view);
    }

    private void initView(View view) {
        mListView = (SwipeMenuListView) view.findViewById(R.id.listView);
        mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        mListView.setMenuCreator(new MySwipeMenuCreator());
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        Toast.makeText(getContext(), "open", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        // delete
                        Toast.makeText(getContext(), "delete", Toast.LENGTH_LONG).show();
                        mAdapter.removeConversation(position);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        List<Conversation> list = new ArrayList<>();
        mAdapter = new MyMessageListAdapter(list, getContext());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(mAdapter);
        RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversations) {
                mAdapter.refreshItem(conversations);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e(TAG, errorCode.getValue() + "  " + errorCode.getMessage());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ReceiveMessageEvent event) {//当收到外界通知
        RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversations) {
                mAdapter.refreshItem(conversations);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e(TAG, errorCode.getValue() + "  " + errorCode.getMessage());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NotifyEvent event){//当从conversation界面退出时会刷新未处理信息
        RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversations) {
                mAdapter.refreshItem(conversations);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e(TAG, errorCode.getValue() + "  " + errorCode.getMessage());
            }
        });
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
