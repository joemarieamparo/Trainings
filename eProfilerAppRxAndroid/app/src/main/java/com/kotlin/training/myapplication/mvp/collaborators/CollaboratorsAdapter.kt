package com.kotlin.training.myapplication.mvp.collaborators

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.kotlin.training.myapplication.R
import com.kotlin.training.myapplication.inflate
import kotlinx.android.synthetic.main.item_collaborators.view.*

class CollaboratorsAdapter(var context: Context, var list: ArrayList<User> = arrayListOf()) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = context.inflate(R.layout.item_collaborators)
        Glide.with(context)
            .load(list[position].picture.medium)
            .into(view.imageView)
        view.name.text = "${list[position].name.first.capitalize()} ${list[position].name.last.capitalize()}"
        view.nationality.text = "Nationality: ${list[position].nationality}"
        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0L
    }

    override fun getCount(): Int {
        return list.size
    }
}