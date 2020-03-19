package com.excalibur.funwithlist.pack1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.excalibur.funwithlist.R
import com.excalibur.funwithlist.pack1.extension.Extension
import com.excalibur.funwithlist.pack1.model.Test
import kotlinx.android.synthetic.main.list_item_main.view.*
import kotlinx.android.synthetic.main.view_list_main_content.view.*

class DummyAdapter : RecyclerView.Adapter<DummyAdapter.BaseViewHolder>() {

    companion object {
        const val ITEM_TYPE_RECYCLER_WIDTH = 1000
        const val ITEM_TYPE_ACTION_WIDTH = 1001
        const val ITEM_TYPE_ACTION_WIDTH_NO_SPRING = 1002
        const val ITEM_TYPE_NO_SWIPE = 1003
    }

    private var list = mutableListOf<Test>()

    private fun setDatas(data: List<Test>) {
        list.clear()
        list.addAll(data)
    }

    fun updateData(data: List<Test>) {
        setDatas(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_main, parent, false)
        return when (viewType) {
            ITEM_TYPE_ACTION_WIDTH -> ItemSwipeWithActionWidthViewHolder(
                view
            )
            ITEM_TYPE_NO_SWIPE -> ItemNoSwipeViewHolder(
                view
            )
            ITEM_TYPE_RECYCLER_WIDTH -> {
                ItemViewHolderWithRecyclerWidth(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.list_item_with_single_delete,
                        parent,
                        false
                    )
                )
            }
            else -> ItemSwipeWithActionWidthNoSpringViewHolder(
                view
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            1 -> ITEM_TYPE_ACTION_WIDTH_NO_SPRING
            2 -> ITEM_TYPE_RECYCLER_WIDTH
            3 -> ITEM_TYPE_NO_SWIPE
            else -> ITEM_TYPE_ACTION_WIDTH
        }
    }

    override fun onBindViewHolder(holderBase: BaseViewHolder, position: Int) {
        holderBase.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(test: Test) {
            itemView.text_list_main_title.text = test.title
            itemView.text_list_main_index.text = test.id.toString()
        }
    }

    /**
     * ViewHolder
     */
    class ItemViewHolderWithRecyclerWidth(itemView: View) : BaseViewHolder(itemView) {

    }

    open class ItemSwipeWithActionWidthViewHolder(itemView: View) : BaseViewHolder(itemView),
        Extension {
        override fun getActionWidth(): Float =
            itemView.view_list_repo_action_container.width.toFloat()
    }

    class ItemSwipeWithActionWidthNoSpringViewHolder(itemView: View) :
        ItemSwipeWithActionWidthViewHolder(itemView), Extension {

        override fun getActionWidth(): Float {
            return itemView.view_list_repo_action_container.width.toFloat()
        }
    }

    class ItemNoSwipeViewHolder(itemView: View) : BaseViewHolder(itemView)
}