package com.excalibur.funwithlist.pack3

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.SurfaceView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.excalibur.funwithlist.R
import org.opencv.android.*
import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc


class OpenCVActivity : AppCompatActivity(),CameraBridgeViewBase.CvCameraViewListener2 {

    private var mOpenCvCameraView: CameraBridgeViewBase? = null
    private var mIsJavaCamera = true
    private var mItemSwitchCamera: MenuItem? = null

    // These variables are used (at the moment) to fix camera orientation from 270degree to 0degree
    var mRgba: Mat? = null
    var mRgbaF: Mat? = null
    var mRgbaT: Mat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_c_v)
        if (OpenCVLoader.initDebug()) {
            Toast.makeText(applicationContext, "OpenCVLoader Successful", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(applicationContext, "OpenCVLoader Fail", Toast.LENGTH_LONG).show()
        }
        mOpenCvCameraView = findViewById(R.id.color_blob_detection_activity_surface_view)

        mOpenCvCameraView?.visibility = SurfaceView.VISIBLE

        mOpenCvCameraView?.setCvCameraViewListener(this)
    }

    private val mLoaderCallback: BaseLoaderCallback = object : BaseLoaderCallback(this) {
        override fun onManagerConnected(status: Int) {
            when (status) {
                LoaderCallbackInterface.SUCCESS -> {
                    Log.i("HVV1312", "OpenCV loaded successfully")
                    mOpenCvCameraView?.enableView()
                }
                else -> {
                    super.onManagerConnected(status)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mOpenCvCameraView?.disableView()
    }

    override fun onResume() {
        super.onResume()
        if(!OpenCVLoader.initDebug()){
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0,this,mLoaderCallback)
        }else{
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mOpenCvCameraView?.disableView()
    }

    override fun onCameraViewStarted(width: Int, height: Int) {
        mRgba = Mat(height, width, CvType.CV_8UC4)
        mRgbaF = Mat(height, width, CvType.CV_8UC4)
        mRgbaT = Mat(width, width, CvType.CV_8UC4)
    }

    override fun onCameraViewStopped() {
        mRgba?.release()
    }

    override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame?): Mat {
        // TODO Auto-generated method stub
        mRgba = inputFrame?.rgba()
        // Rotate mRgba 90 degrees
        Core.transpose(mRgba, mRgbaT);
        Imgproc.resize(mRgbaT, mRgbaF, mRgbaF!!.size(), 0.0, 0.0, 0);
        Core.flip(mRgbaF, mRgba, 1 )
        return mRgba!! // This function must return
    }
}

