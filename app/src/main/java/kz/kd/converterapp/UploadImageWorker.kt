package kz.kd.converterapp

import android.content.Context
import android.os.Bundle
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class UploadImageWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val imageUrlInput = inputData.getString(KEY_IMAGE_URL_SEND) ?: return Result.failure()
        val firebaseAnalytics: FirebaseAnalytics = Firebase.analytics
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, imageUrlInput)
        firebaseAnalytics.logEvent(TAG_ANALYTICS_IMAGE, bundle)

        return Result.success()
    }
}