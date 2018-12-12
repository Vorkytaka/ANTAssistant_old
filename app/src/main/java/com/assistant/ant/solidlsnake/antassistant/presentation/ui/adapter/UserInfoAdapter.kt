package com.assistant.ant.solidlsnake.antassistant.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.presentation.model.UserDataUI

class UserInfoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data: MutableList<UserDataUI.ListObject> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_user_info, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val item = data[position]

            holder.hint.text = item.hint
            holder.info.text = item.info
            holder.icon.setImageResource(item.iconId)
        }
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: Collection<UserDataUI.ListObject>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val hint: TextView = view.findViewById(R.id.tv_hint)
        val info: TextView = view.findViewById(R.id.tv_info)
        val icon: ImageView = view.findViewById(R.id.iv_copy)
    }
}