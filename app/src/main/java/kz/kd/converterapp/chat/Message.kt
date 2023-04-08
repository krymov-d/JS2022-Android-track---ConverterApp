package kz.kd.converterapp.chat

import java.util.concurrent.atomic.AtomicReference

data class Message(
    val id: String? = "",
    val messageUserName: String? = "",
    val messageLabel: String? = "",
    val messageText: String? = "",
) : java.io.Serializable
