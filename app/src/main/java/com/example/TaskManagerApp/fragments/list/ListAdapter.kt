package com.example.TaskManagerApp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.TaskManagerApp.R
import com.example.TaskManagerApp.model.Note
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var noteList = emptyList<Note>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
       return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.title_txt.text = currentItem.title
        holder.itemView.content_txt.text = currentItem.content
        holder.itemView.priority_txt.text = currentItem.priority.toString()

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(note: List<Note>){
        this.noteList = note
        notifyDataSetChanged()
    }

    fun filterList(filteredNotes: List<Note>) {
        noteList = filteredNotes
        notifyDataSetChanged()
    }
}