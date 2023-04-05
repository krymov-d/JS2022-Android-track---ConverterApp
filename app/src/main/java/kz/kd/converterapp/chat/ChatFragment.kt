package kz.kd.converterapp.chat

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kz.kd.converterapp.R

private const val KEY_NAME = "Name"
private const val USER_MESSAGES = "UserMessages"

class ChatFragment : Fragment(), IFSaveName {

    private lateinit var database: DatabaseReference

    private lateinit var preferences: SharedPreferences
    private var userName: String? = null

    private lateinit var etMessage: EditText
    private lateinit var ibSend: ImageButton
    private lateinit var rvChat: RecyclerView
    private lateinit var chatAdapter: MessageAdapter

    private var messageId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDatabase()
        getSavedName()
        initBSDNameRequest()
    }

    private fun initDatabase() {
        database = Firebase.database.reference
    }

    private fun getSavedName() {
        preferences =
            androidx.preference.PreferenceManager.getDefaultSharedPreferences(requireContext())
        userName = preferences.getString(KEY_NAME, getString(R.string.anonymous))
    }

    private fun initBSDNameRequest() {
        BSDNameRequest(userName, this).show(childFragmentManager, null)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initChat()
        initRecycler(view)
        requestDatabaseContent()
    }

    private fun initViews(view: View) {
        etMessage = view.findViewById(R.id.et_message)
        ibSend = view.findViewById(R.id.ib_send)
    }

    private fun initChat() {
        ibSend.setOnClickListener {
            writeNewMessage(messageId.toString(), etMessage.text.toString())
            messageId++
            etMessage.text.clear()
        }
    }

    private fun writeNewMessage(id: String, messageText: String) {
        val name = userName ?: ""
        val letter = userName?.subSequence(0, 1).toString()
        val message = Message(id, name, letter, messageText)
        database.child(USER_MESSAGES).child(id).setValue(message)
    }

    private fun initRecycler(view: View) {
        rvChat = view.findViewById(R.id.rv_message)
        chatAdapter = MessageAdapter(layoutInflater)

        rvChat.adapter = chatAdapter
        rvChat.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun requestDatabaseContent() {
        val messageListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Toast.makeText(context, "onChildAdded", Toast.LENGTH_SHORT).show()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Toast.makeText(context, "onChildChanged", Toast.LENGTH_SHORT).show()
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        database.child(USER_MESSAGES).addChildEventListener(messageListener)
    }

    override fun saveName(name: String) {
        preferences.edit().putString(KEY_NAME, name).apply()
    }
}