package com.example.dataentryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(private val dataSet: List<Contact>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById(R.id.profile_image)
        val name: TextView = view.findViewById(R.id.tv_name)
        val phoneNumber: TextView = view.findViewById(R.id.tv_phone)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_contact, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentContact = dataSet[position]
        viewHolder.name.text = currentContact.profileName
        viewHolder.phoneNumber.text = currentContact.phoneNumber
        viewHolder.image.setImageURI(currentContact.profileImage)
    }

    override fun getItemCount() = dataSet.size

}
