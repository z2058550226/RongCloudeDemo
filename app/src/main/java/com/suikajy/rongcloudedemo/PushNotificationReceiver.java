package com.suikajy.rongcloudedemo;

import android.content.Context;
import android.util.Log;

import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 * Created by zjy on 2017/9/22.
 */

public class PushNotificationReceiver extends PushMessageReceiver {
    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        Log.e("**** TAG ****", "onNotificationMessageArrived:" + pushNotificationMessage.getPushContent() + "\n" +
                "message.getExtra()" + pushNotificationMessage.getExtra() + "\n" +
                "message.getPushTitle()" + pushNotificationMessage.getPushTitle() + "\n" +
                "message.getSenderName()" + pushNotificationMessage.getSenderName() + "\n" );
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
//        Log.e("**** TAG ****", "onNotificationMessageClicked");
        return false;
    }
}
