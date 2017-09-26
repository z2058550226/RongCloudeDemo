package com.suikajy.rongcloudedemo.utils

import com.suikajy.rongcloudedemo.Const
import io.rong.imkit.RongIM
import io.rong.imlib.model.UserInfo

/**
 * Created by zjy on 2017/9/25.
 */
class MyUserInfoProvider:RongIM.UserInfoProvider{
    override fun getUserInfo(p0: String?): UserInfo {
        return if (p0 == Const.DEBUG_USER.userId) {
            Const.DEBUG_USER
        } else
            Const.DEBUG_USER2
    }
}