package com.assistant.ant.solidlsnake.antassistant.presentation.ui.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import com.assistant.ant.solidlsnake.antassistant.presentation.model.generateList
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.adapter.MarginDivider
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.adapter.UserInfoAdapter
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {

    private val adapter = UserInfoAdapter()

    private val clipboardManager: ClipboardManager by lazy {
        requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        data.adapter = adapter
        data.layoutManager = LinearLayoutManager(requireContext())
        data.addItemDecoration(MarginDivider(3))

        adapter.itemClickListener = {
            val data = ClipData.newPlainText("ANTData", it.info.text)
            clipboardManager.primaryClip = data
            Toast.makeText(context, R.string.s_main_copy_message, Toast.LENGTH_SHORT).show()
        }
    }

    fun setData(data: UserData) {
        adapter.setData(data.generateList())
    }
}