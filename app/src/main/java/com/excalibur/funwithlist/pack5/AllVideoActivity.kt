package com.excalibur.funwithlist.pack5

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import com.excalibur.funwithlist.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class AllVideoActivity : AppCompatActivity(),CoroutineScope ,MediaUtils{


    private var loadingImageJob :Job? = null
    private var handlerAction: Handler? = null

    override fun onDestroy() {
        super.onDestroy()
        cancelDelay(handlerAction)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_video)
        getAlbumList()
    }

    @SuppressLint("InlinedApi")
    private fun getAlbumList(){
        val countColumnName = "count"
        val projection = arrayOf(MediaStore.Video.VideoColumns._ID,MediaStore.Video.VideoColumns.BUCKET_DISPLAY_NAME,MediaStore.Video.VideoColumns.DATE_TAKEN,MediaStore.Video.VideoColumns.DATA)
        val bucketGroupBy = "1) GROUP BY 1,(2"
        val bucketOrderBy = "MAX(datetaken) DESC"
        loadingImageJob = sync({
            val videos = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            val cur = contentResolver.query(videos,projection,bucketGroupBy,null,bucketOrderBy)
            if(cur!=null) {
                if (cur.moveToFirst()) {
                    val bucketColumn =
                        cur.getColumnIndex(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
                    val dateColumn = cur.getColumnIndex(MediaStore.Video.Media.DATE_TAKEN)
                    val dataColumn = cur.getColumnIndex(MediaStore.Video.Media.DATA)
                    do{
                        val bucket = cur.getString(bucketColumn)
                        val date = cur.getString(dateColumn)
                        val data = cur.getString(dataColumn)
                        Log.e("HVV1312", " bucket=" + bucket
                                + "  date_taken=" + date
                                + "  _data=" + data);
                    }while (cur.moveToNext())
                }
            }

        })
    }


}
