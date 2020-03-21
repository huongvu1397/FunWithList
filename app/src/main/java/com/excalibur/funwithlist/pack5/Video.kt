package com.excalibur.funwithlist.pack5

import android.net.Uri
import android.provider.MediaStore

class Video : MediaModelBase(){
    override fun getUriInternal(): Uri {
        return MediaStore.Video.Media.INTERNAL_CONTENT_URI
    }

    @MediaInfo(MediaStore.Video.VideoColumns._ID)
    var id:Long = -1L
    @MediaInfo(MediaStore.Video.VideoColumns.DATA)
    var path:String = ""
    @MediaInfo(MediaStore.Video.VideoColumns.DURATION)
    var duration:Long = -1
    @MediaInfo(MediaStore.Video.VideoColumns.DATE_ADDED)
    var dateAdded:Long = -1
    @MediaInfo(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
    var bucketName:String = ""
    override fun getUri(): Uri {
        return MediaStore.Video.Media.EXTERNAL_CONTENT_URI
    }

}