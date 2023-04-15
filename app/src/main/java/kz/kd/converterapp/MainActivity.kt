package kz.kd.converterapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kz.kd.converterapp.domain.GetPhotoByIdUseCase
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

private const val TAG = "WorkManagerHW"
class MainActivity : AppCompatActivity() {
    val getPhotoById: GetPhotoByIdUseCase by inject()
    var photo: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getImage()

        //initDownloadWorker()
    }
    private fun getImage() {
        MainScope().launch(Dispatchers.IO) {
            photo = getPhotoById(1).url
            Log.d(TAG, photo.toString())
        }
    }
    private fun initDownloadWorker() {
        val myDownloadImageWorker =
            PeriodicWorkRequestBuilder<DownloadImageWorker>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(this).enqueue(myDownloadImageWorker)
    }
}