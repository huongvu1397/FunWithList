package com.excalibur.funwithlist

import android.app.Application


class MyApp : Application() {

    companion object{
        private lateinit var app : MyApp
        @JvmStatic
        fun self() = app
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}