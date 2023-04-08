package kz.kd.converterapp.chat

data class Message(
    val messageUserName: String? = "",
    val messageLabel: String? = "",
    val messageText: String? = "",
) : java.io.Serializable
