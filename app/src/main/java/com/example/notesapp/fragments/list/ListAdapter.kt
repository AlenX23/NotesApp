package com.example.notesapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.model.User
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlin.coroutines.coroutineContext

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        //holder.itemView.u_id.text = currentItem.id.toString()
        holder.itemView.u_title.text = currentItem.title
        holder.itemView.u_description.text = currentItem.description
        holder.itemView.u_date.text = currentItem.date

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>) {
        this.userList = user
    }

}