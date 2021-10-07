package com.example.my_cinema.rcView_Adapter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.my_cinema.R
import com.example.my_cinema.model.ContentX
import com.example.my_cinema.model.ContentXX

class AdapterMain (list: List<ContentX>, cotext_:Context): RecyclerView.Adapter<AdapterMain.ViewHolder>(){

    var myList = list
    var context = cotext_

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val filmClass_Name = view.findViewById<TextView>(R.id.film_group)
        val filmRcView = view.findViewById<RecyclerView>(R.id.RcFilm)


        fun bind(list: ContentX, context:Context)
        {
            filmClass_Name.text = list.title

            filmRcView.hasFixedSize()
            filmRcView.layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            var myAdapter = AdapterItem(list.content, context)

            filmRcView.adapter = myAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(context)
        return ViewHolder(inflater.inflate(R.layout.content_content, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listItem = myList[position]
        holder.bind(listItem, context)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

}