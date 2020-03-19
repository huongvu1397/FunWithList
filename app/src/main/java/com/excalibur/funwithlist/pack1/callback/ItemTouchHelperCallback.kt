package com.excalibur.funwithlist.pack1.callback

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.excalibur.funwithlist.pack1.DummyAdapter
import com.excalibur.funwithlist.pack1.extension.ItemTouchHelperExtension
import kotlinx.android.synthetic.main.list_item_main.view.*
import kotlinx.android.synthetic.main.view_list_main_content.view.*

class ItemTouchHelperCallback : ItemTouchHelperExtension.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView?,
        viewHolder: RecyclerView.ViewHolder?
    ): Int {
        if (viewHolder is DummyAdapter.ItemNoSwipeViewHolder) {
            return 0
        }
        return makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.START)
    }

    override fun onMove(
        recyclerView: RecyclerView?,
        viewHolder: RecyclerView.ViewHolder?,
        target: RecyclerView.ViewHolder?
    ): Boolean {
        val adapter = recyclerView?.adapter
        //adapter.move()
        return true
    }

    override fun isLongPressDragEnabled(): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
    }

    override fun onChildDraw(
        c: Canvas?,
        recyclerView: RecyclerView?,
        viewHolder: RecyclerView.ViewHolder?,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (dY != 0f && dX == 0f) super.onChildDraw(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
        val holder = viewHolder as DummyAdapter.BaseViewHolder
        if (viewHolder is DummyAdapter.ItemSwipeWithActionWidthNoSpringViewHolder) {
            val tempDx = if (dX < -holder.itemView.view_list_repo_action_container.width) {
                -holder.itemView.view_list_repo_action_container.width.toFloat()
            } else dX
            holder.itemView.view_list_main_content.translationX = tempDx
            return
        }
        if (viewHolder is DummyAdapter.BaseViewHolder)
            holder.itemView.view_list_main_content.translationX = dX
    }
}