package kz.kd.converterapp.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.kd.converterapp.R

class MessageAdapter(private val layoutInflater: LayoutInflater, private val userName: String) :
    RecyclerView.Adapter<MessageViewHolder>() {

    private var messageList: MutableList<Message> = mutableListOf()

    override fun getItemViewType(position: Int): Int {
        return if (messageList[position].messageUserName == userName) {
            0
        } else {
            1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val itemView = if (viewType == 0) {
            layoutInflater.inflate(R.layout.item_message, parent, false)
        } else {
            layoutInflater.inflate(R.layout.item_message_other, parent, false)
        }
        return MessageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messageList[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun addNewMessage(message: Message?) {
        if (message == null) {
            return
        }
        messageList.add(0, message)
        notifyItemInserted(0)
    }
}