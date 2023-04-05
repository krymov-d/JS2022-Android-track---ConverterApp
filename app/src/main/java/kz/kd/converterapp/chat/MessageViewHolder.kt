package kz.kd.converterapp.chat

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kz.kd.converterapp.R

class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val customTVMessage: CustomTVMessage = itemView.findViewById(R.id.custom_tv_message)
    private val customTVFirstLetter: CustomTVFirstLetter =
        itemView.findViewById(R.id.custom_tv_letter)

    fun bind(message: Message) {
        customTVMessage.text = message.messageText
        customTVFirstLetter.text = message.userNameLetter
    }
}