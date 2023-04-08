package kz.kd.converterapp.chat

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kz.kd.converterapp.R

private const val KEY_NAME = "ChatFragmentUserName"
private const val USER_MESSAGES = "ChatFragmentUserMessages"

class ChatFragment : Fragment(), UserNameRepository {

    private lateinit var database: DatabaseReference
    private lateinit var preferences: SharedPreferences

    private lateinit var etMessage: EditText
    private lateinit var ibSend: ImageButton
    private lateinit var rvChat: RecyclerView
    private lateinit var chatAdapter: MessageAdapter
    private lateinit var chatLayoutManager: LinearLayoutManager

    private lateinit var userName: String
    private lateinit var userNameAnonymous: String
    private var messageId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDatabase()
        getUserNameFromSharedPreference()
        initBSDUserNameRequest()
    }

    private fun initDatabase() {
        database = Firebase.database.reference
    }

    private fun getUserNameFromSharedPreference() {
        preferences =
            androidx.preference.PreferenceManager.getDefaultSharedPreferences(requireContext())
        userNameAnonymous = getString(R.string.anonymous)
        userName = preferences.getString(KEY_NAME, userNameAnonymous) ?: userNameAnonymous //trick to keep the non-null type for userName variable
    }

    private fun initBSDUserNameRequest() {
        if (userName == userNameAnonymous) {
            BSDUserNameRequest(this).show(childFragmentManager, null)
        }
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
        initRecycler()
        initChat()
        requestDatabaseContent()
    }

    private fun initViews(view: View) {
        with(view) {
            etMessage = findViewById(R.id.et_message)
            ibSend = findViewById(R.id.ib_send)
            rvChat = findViewById(R.id.rv_message)
        }
    }

    private fun initRecycler() {
        chatAdapter = MessageAdapter(layoutInflater)
        chatLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        rvChat.adapter = chatAdapter
        rvChat.layoutManager = chatLayoutManager
    }

    private fun initChat() {
//        val keyList = mutableListOf<String?>()
//        val valueEventListener = object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                Log.d(USER_MESSAGES, snapshot.toString())
//                val mapOfMessages = snapshot.value as Map<String, String>
//                val keysList = mapOfMessages.keys.toMutableList().map { it.toInt() }.sortedBy { it.toInt() }
//                val sortedMapOfMessages = mapOfMessages.toSortedMap()
//                Log.d(
//                    USER_MESSAGES, sortedMapOfMessages.lastKey().toString())
//                Log.d(
//                    USER_MESSAGES, sortedMapOfMessages.toString())
//                Log.d(
//                    USER_MESSAGES, keysList.toString())
//                if (snapshot.exists()) {
//                    var i = 0
//                    for (d in snapshot.children) {
//                        keyList[i] = d.key
//                        i++
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        }
//        database.child(USER_MESSAGES).child(userName).addValueEventListener(valueEventListener)

        ibSend.setOnClickListener {
            writeNewMessage(messageId.toString(), etMessage.text.toString())
            messageId++
            etMessage.text.clear()
        }
    }

    private fun writeNewMessage(id: String, messageText: String) {
        val message = Message(id, userName, userName[0].toString(), messageText)
        //database.child(USER_MESSAGES).child(userName).child(id).setValue(message)
        database.child(USER_MESSAGES).child(userName).setValue(message)
    }

    private fun requestDatabaseContent() {
//        val messageListener = object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for (messageSnapshot in snapshot.children) {
//                    val message = messageSnapshot.getValue(Message::class.java)
//                    chatAdapter.addNewMessage(message)
//                }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        }

        val messageListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d(USER_MESSAGES, snapshot.toString())
                val message = snapshot.getValue(Message::class.java)
                chatAdapter.addNewMessage(message)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(Message::class.java)
                chatAdapter.addNewMessage(message)
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

    override fun putUserNameToSharedPreference(name: String) {
        userName = name
        preferences.edit().putString(KEY_NAME, name).apply()
    }
}