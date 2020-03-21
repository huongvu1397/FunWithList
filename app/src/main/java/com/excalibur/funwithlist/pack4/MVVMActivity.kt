package com.excalibur.funwithlist.pack4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.excalibur.funwithlist.R

class MVVMActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_v_v_m)
    }

    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(this, R.id.navHostFragment).navigateUp()
}
