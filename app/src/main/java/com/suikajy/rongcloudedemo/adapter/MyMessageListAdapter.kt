package com.suikajy.rongcloudedemo.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.suikajy.rongcloudedemo.App
import com.suikajy.rongcloudedemo.R
import com.suikajy.rongcloudedemo.utils.GlideCircleTransform
import io.rong.imkit.RongContext
import io.rong.imkit.RongIM
import io.rong.imkit.userInfoCache.RongUserInfoManager
import io.rong.imlib.RongIMClient
import io.rong.imlib.model.Conversation
import io.rong.imlib.model.UserInfo
import io.rong.message.ImageMessage
import io.rong.message.TextMessage
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by zjy on 2017/9/25.
 */
class MyMessageListAdapter(private var conversations: MutableList<Conversation>, private val mContext: Context) : BaseAdapter(), AdapterView.OnItemClickListener {

    private val TAG = "MyMessageListAdapter"

    override fun getItem(position: Int): Any = conversations[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = conversations.size

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val conversation = conversations[position]
        RongIM.getInstance().startConversation(mContext, conversation.conversationType, conversation.targetId, conversation.targetId)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: MessageListViewHolder
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_message, parent, false)
            viewHolder = MessageListViewHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = convertView.tag as MessageListViewHolder
            view = convertView
        }
        val conversation = conversations[position]
        logConversation(position, conversation)

        viewHolder.mTvContent.text = if (conversation.latestMessage is TextMessage) {
            (conversation.latestMessage as TextMessage).content
        } else if (conversation.latestMessage is ImageMessage) {
            "[图片]"
        } else {
            "[文件]"
        }
        if (conversation.unreadMessageCount == 0) {
            viewHolder.mTvNoReadMessageCount.visibility = View.GONE
        } else {
            viewHolder.mTvNoReadMessageCount.visibility = View.VISIBLE
            viewHolder.mTvNoReadMessageCount.text = conversation.unreadMessageCount.toString()
        }
        viewHolder.mTvTime.text = stamp2String(conversation.sentTime)
        viewHolder.mTvTitle.text = conversation.senderUserId

        val userInfo = RongUserInfoManager.getInstance().getUserInfo(conversation.targetId)
        if (userInfo != null)
            Glide.with(mContext).load(userInfo.portraitUri).error(R.drawable.toy_pic).transform(GlideCircleTransform.getInstance()).into(viewHolder.mIvAvatar)
        return view
    }

    fun refreshItem(items: List<Conversation>) {
        conversations.clear()
        conversations.addAll(items)
        notifyDataSetChanged()
    }

    fun addMoreItem(items: List<Conversation>) {
        conversations.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        conversations.clear()
        notifyDataSetChanged()
    }

    fun removeConversation(position: Int) {
        val conversation = conversations[position]
        RongIM.getInstance().removeConversation(conversation.conversationType, conversation.targetId, object : RongIMClient.ResultCallback<Boolean>() {
            override fun onSuccess(aBoolean: Boolean?) {
                Log.e(TAG, aBoolean.toString())
                if (aBoolean!!) {
                    conversations.removeAt(position)
                    notifyDataSetChanged()
                }
            }

            override fun onError(errorCode: RongIMClient.ErrorCode) {
                Log.e(TAG, errorCode.message)
                Toast.makeText(App.getInstance(), "删除失败 ${errorCode.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun stamp2String(stamp: Long): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = Date(stamp)
        return simpleDateFormat.format(date)
    }

    private fun logConversation(position: Int, conversation: Conversation) {
        Log.e(TAG, "item position is $position \n" +
                "content is : ${if (conversation.latestMessage is TextMessage) {
                    (conversation.latestMessage as TextMessage).content
                } else {
                    "not a text msg"
                }} \n" +
                "latestMessageId is : ${conversation.latestMessageId} \n" +
                "conversationTitle is : ${conversation.conversationTitle} \n" +
                "conversationType is : ${conversation.conversationType.name} \n" +
                "draft is : ${conversation.draft} \n" +
                "isTop is : ${conversation.isTop} \n" +
                "mentionedCount is : ${conversation.mentionedCount} \n" +
                "notificationStatus is : ${conversation.notificationStatus.value}  0:DO_NOT_DISTURB,1:NOTIFY\n" +
                "objectName is : ${conversation.objectName} \n" +
                "portraitUrl is : ${conversation.portraitUrl} \n" +
                "receivedTime is : ${conversation.receivedTime} \n" +
                "sentTime is : ${conversation.sentTime} \n" +
                "senderUserId is : ${conversation.senderUserId} \n" +
                "senderUserName is : ${conversation.senderUserName} \n" +
                "sentStatus is : ${conversation.sentStatus.name} \n" +
                "targetId is : ${conversation.targetId} \n" +
                "unreadMessageCount is : ${conversation.unreadMessageCount} \n")
    }
}
