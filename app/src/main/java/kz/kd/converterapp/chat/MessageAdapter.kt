package kz.kd.converterapp.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.kd.converterapp.R

class MessageAdapter(private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<MessageViewHolder>() {

    private var messageList: MutableList<Message> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_message, parent, false)
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