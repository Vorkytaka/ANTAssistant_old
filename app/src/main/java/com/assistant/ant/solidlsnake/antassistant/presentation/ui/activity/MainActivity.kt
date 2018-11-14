package com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.presentation.model.UserDataUI
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.MainPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.adapter.MarginDivider
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.adapter.UserInfoAdapter
import com.assistant.ant.solidlsnake.antassistant.presentation.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), MainView {

    private val presenter: MainPresenter by inject()

    private val adapter = UserInfoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data.adapter = adapter
        data.layoutManager = LinearLayoutManager(baseContext)
        data.addItemDecoration(MarginDivider(10))
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun setProgress(progress: Boolean) {

    }

    override fun showUserData(data: UserDataUI) {
        adapter.setData(data.data)
    }
}
