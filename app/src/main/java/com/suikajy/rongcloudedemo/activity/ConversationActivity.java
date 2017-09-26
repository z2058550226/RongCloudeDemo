package com.suikajy.rongcloudedemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;

import com.suikajy.rongcloudedemo.R;
import com.suikajy.rongcloudedemo.event.NotifyEvent;

import org.greenrobot.eventbus.EventBus;

import io.rong.imkit.fragment.ConversationFragment;

/**
 * Created by zjy on 2017/9/21.
 */

public class ConversationActivity extends FragmentActivity {


    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        ConversationFragment conversationFragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);
        String s = conversationFragment.getTargetId();
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(s);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().post(new NotifyEvent());
        super.onDestroy();
    }
}
