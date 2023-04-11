package kz.kd.converterapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

private const val CHANNEL_ID = "33"
private const val OPEN_FRAGMENT_WITH_ID = R.id.favoritesFragment

class FirebaseMessagingServiceInstance : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        createNotificationChannel()
        displayNotification(message)
    }

    override fun onNewToken(token: String) = Unit

    private fun displayNotification(message: RemoteMessage) {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setContentIntent(getPendingIntent())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this).notify(Int.MIN_VALUE, notification)

    }

    private fun getPendingIntent(): PendingIntent {
        val mainActivityIntent = Intent(this, MainActivity::class.java).apply {
            this.putExtra(INTENT_DATA_NAME, OPEN_FRAGMENT_WITH_ID)
        }
        val splashScreenActivityIntent = Intent(this, CustomScreenActivity::class.java)

        return TaskStackBuilder.create(this).apply {
            addNextIntent(mainActivityIntent)
            addNextIntent(splashScreenActivityIntent)
        }.getPendingIntent(0, FLAG_ONE_SHOT)
    }

    private fun createNotificationChannel() {
        val name = "Homework 33"
        val descriptionText = "Notification from Firebase"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}