package kz.kd.converterapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kz.kd.converterapp.domain.GetPhotoByIdUseCase
import org.koin.android.ext.android.inject

const val KEY_IMAGE_URL = "ImageURL"
const val KEY_IMAGE_URL_SEND = "ImageURLSend"
const val TAG_ANALYTICS_IMAGE = "ImageLoaded"
private const val PHOTO_ID = 1

class MainActivity : AppCompatActivity() {

    private val getPhotoByIdUseCase: GetPhotoByIdUseCase by inject()
    private var imageURL: String? = null

    private lateinit var myData: Data
    private lateinit var myConstraints: Constraints
    private lateinit var myDownloadImageWorkRequest: OneTimeWorkRequest
    private lateinit var myUploadImageWorkRequest: OneTimeWorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getImage()
    }

    private fun getImage() {
        MainScope().launch(Dispatchers.IO) {
            imageURL = getPhotoByIdUseCase(PHOTO_ID).url
            myData = Data.Builder().putString(KEY_IMAGE_URL, imageURL).build()

            initWorkRequestConstraints()
            initWorkRequests()
            initWorkerManager()
        }
    }

    private fun initWorkRequestConstraints() {
        myConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .build()
    }

    private fun initWorkRequests() {
        myDownloadImageWorkRequest =
            OneTimeWorkRequestBuilder<DownloadImageWorker>()
                .setConstraints(myConstraints)
                .setInputData(myData)
                .build()

        myUploadImageWorkRequest =
            OneTimeWorkRequestBuilder<UploadImageWorker>()
                .setConstraints(myConstraints)
                .build()
    }

    private fun initWorkerManager() {
        WorkManager.getInstance(this)
            .beginWith(myDownloadImageWorkRequest)
            .then(myUploadImageWorkRequest)
            .enqueue()
    }
}