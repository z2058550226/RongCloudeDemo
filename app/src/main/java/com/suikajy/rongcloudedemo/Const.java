package com.suikajy.rongcloudedemo;

import android.net.Uri;

import io.rong.imlib.model.UserInfo;

/**
 * Created by zjy on 2017/9/22.
 */

public class Const {

    public static final String DEBUG_USER_ID = "15010970408";
    public static final String DEBUG_USER_NAME = "suikajy";
    public static final String DEBUG_TOKEN = "Roy8/UBRyQrIu6Jf/jha9UXgh1gN2hi1KlYtu4qKA+B2jFfTlX8Lz3G0xnvIz4E2/+s21JaZqmYP0AW94N5AESwCPHc8T5NG";
    public static final String DEBUG_AVATAR = "http://192.168.1.181:8093/api/avatar/show.php?userid=33&size=large&time=1506042927";

    public static final String DEBUG_USER_ID2 = "18345978767";
    public static final String DEBUG_USER_NAME2 = "leader";
    public static final String DEBUG_TOKEN2 = "kQzW4KrSLLnAAjbzm+NJrNQ1GeAR1n4DRHnEXgJe2Yubxudrld+EEJmW47p1u9BjABDJLCNZxnckKo47IXWFZWWybpUBylbY";
    public static final String DEBUG_AVATAR2 = "http://192.168.1.181:8093/file/banner/categoryimg0.png";

    public static final String DEBUG_USER_ID3 = "1441";

    public static final UserInfo DEBUG_USER = new UserInfo(DEBUG_USER_ID, DEBUG_USER_NAME, Uri.parse(DEBUG_AVATAR));
    public static final UserInfo DEBUG_USER2 = new UserInfo(DEBUG_USER_ID2, DEBUG_USER_NAME2, Uri.parse(DEBUG_AVATAR2));
}
