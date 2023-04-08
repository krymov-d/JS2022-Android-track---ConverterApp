package kz.kd.converterapp.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kz.kd.converterapp.R

class BSDUserNameRequest(private val userNameRepository: UserNameRepository) :
    BottomSheetDialogFragment() {

    private lateinit var etName: EditText
    private lateinit var ibSend: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dbs_username_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initClickListener()
    }

    private fun initViews(view: View) {
        with(view) {
            etName = findViewById(R.id.bsd_et_name_request)
            ibSend = findViewById(R.id.bsd_iv_send)
        }
    }

    private fun initClickListener() {
        ibSend.setOnClickListener {
            userNameRepository.putUserNameToSharedPreference(etName.text.toString())
            dismiss()
        }
    }

}