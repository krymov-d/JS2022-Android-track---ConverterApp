package kz.kd.converterapp

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class DownloadImageWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val imageUrlInput = inputData.getString(KEY_IMAGE_URL) ?: return Result.failure()
        val myData = Data.Builder().putString(KEY_IMAGE_URL_SEND, imageUrlInput).build()
        return Result.success(myData)
    }
}