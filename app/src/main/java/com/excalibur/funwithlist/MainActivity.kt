package com.excalibur.funwithlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.excalibur.funwithlist.pack1.ItemHelperSwipeListActivity
import com.excalibur.funwithlist.pack2.AnimActivity
import com.excalibur.funwithlist.pack3.OpenCVActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1.setOnClickListener {
            startActivity(Intent(this,
                ItemHelperSwipeListActivity::class.java))
        }
        btn2.setOnClickListener {
            startActivity(Intent(this,
                AnimActivity::class.java))
        }
        btn3.setOnClickListener {
            startActivity(Intent(this,
                OpenCVActivity::class.java))
        }
    }
}
