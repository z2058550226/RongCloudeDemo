package com.suikajy.rongcloudedemo;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.suikajy.rongcloudedemo.utils.MyUserInfoProvider;

import org.greenrobot.eventbus.EventBus;

import io.rong.imkit.RongIM;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;

/**
 * Created by zjy on 2017/9/21.
 */

public class App extends Application {

    private static App instance;
    public static final int RONG_IM_CHANNEL_ID = 0x89;
    public MyUserInfoProvider mUserInfoProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RongIM.init(this);
        mUserInfoProvider = new MyUserInfoProvider();
        RongIM.setUserInfoProvider(mUserInfoProvider, true);
    }

    public static Context getInstance() {
        return instance;
    }

    public MyUserInfoProvider getmUserInfoProvider() {
        return mUserInfoProvider;
    }
}
