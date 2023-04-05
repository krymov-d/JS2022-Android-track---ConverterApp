package kz.kd.converterapp.chat

data class Message(
    val id: String,
    val userName: String,
    val userNameLetter: String,
    val messageText: String,
) : java.io.Serializable
