package com.suikajy.rongcloudedemo.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.suikajy.rongcloudedemo.R

/**
 * Created by zjy on 2017/9/25.
 */
class MessageListViewHolder {
    var mIvAvatar: ImageView
    var mTvTitle: TextView
    var mTvContent: TextView
    var mTvTime: TextView
    var mTvNoReadMessageCount: TextView

    constructor(itemView:View){
        mIvAvatar = itemView.findViewById(R.id.iv_avatar) as ImageView
        mTvTitle = itemView.findViewById(R.id.tv_title) as TextView
        mTvContent = itemView.findViewById(R.id.tv_content) as TextView
        mTvTime = itemView.findViewById(R.id.tv_time) as TextView
        mTvNoReadMessageCount = itemView.findViewById(R.id.tv_no_read_message_count) as TextView
    }
}