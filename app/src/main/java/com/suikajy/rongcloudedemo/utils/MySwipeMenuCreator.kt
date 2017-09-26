package com.suikajy.rongcloudedemo.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.suikajy.rongcloudedemo.App


/**
 * Created by zjy on 2017/9/25.
 */
class MySwipeMenuCreator : SwipeMenuCreator {
    override fun create(menu: SwipeMenu) {
        val openItem = SwipeMenuItem(App.getInstance())

        // set item background
        openItem.background = ColorDrawable(0xFF66CCFF.toInt())
        // set item width
        openItem.width = 180
        // set item title
        openItem.title = "Open"
        // set item title fontsize
        openItem.titleSize = 18
        // set item title font color
        openItem.titleColor = Color.WHITE
        // add to menu
        menu.addMenuItem(openItem)

        // create "delete" item
        val deleteItem = SwipeMenuItem(
                App.getInstance())
        // set item background
        deleteItem.background = ColorDrawable(0xFFF93F25.toInt())
        // set item width
        deleteItem.width = 180
        // set a icon
        deleteItem.title = "删除"
        deleteItem.titleColor = Color.WHITE
        deleteItem.titleSize = 18
        // add to menu
        menu.addMenuItem(deleteItem)
    }

}