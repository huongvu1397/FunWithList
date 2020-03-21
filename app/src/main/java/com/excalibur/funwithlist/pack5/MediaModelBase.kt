package com.excalibur.funwithlist.pack5

import android.net.Uri
import java.io.Serializable

abstract class MediaModelBase : Serializable {
    abstract fun getUri(): Uri
    abstract fun getUriInternal():Uri
}