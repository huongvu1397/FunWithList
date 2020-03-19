package com.excalibur.funwithlist.pack1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.excalibur.funwithlist.R
import com.excalibur.funwithlist.pack1.callback.ItemTouchHelperCallback
import com.excalibur.funwithlist.pack1.extension.ItemTouchHelperExtension
import com.excalibur.funwithlist.pack1.model.Test
import kotlinx.android.synthetic.main.activity_item_helper_swipe_list.*

class ItemHelperSwipeListActivity : AppCompatActivity() {

    private var dummyAdapter: DummyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_helper_swipe_list)
        dummyAdapter = DummyAdapter()
        rcl.adapter = dummyAdapter
        rcl.layoutManager = LinearLayoutManager(this)
        rcl.setHasFixedSize(true)
        rcl.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        val callback = ItemTouchHelperCallback()
        val itemTouchHelper = ItemTouchHelperExtension(callback)
        itemTouchHelper.attachToRecyclerView(rcl)
        dummyAdapter?.updateData(createTestData())
    }

    private fun createTestData(): List<Test> {
        val result = mutableListOf<Test>()
        for (i in 0..20) {
            val testModel= when (i) {
                1 ->  Test(i, "Item swipe with action container width and no spring")
                2 ->Test(i, "Item Swipe with RecyclerView Width")
                3 -> Test(i, "Item No swipe")
                else -> Test(i, "Item Swipe Action Button Container Width")
            }
            result.add(testModel)
        }
        return result
    }
}
