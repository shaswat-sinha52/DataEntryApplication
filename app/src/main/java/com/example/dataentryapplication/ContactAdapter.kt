package com.example.dataentryapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private val listofcontact:List<Contact>
) :RecyclerView.Adapter<ContactAdapter.Contactlistviewholder>(){

    class Contactlistviewholder
        (itemview:View): RecyclerView.ViewHolder(itemview){
            val image:ImageView=itemview.findViewById(R.id.rv_lo_image)
            val heading:TextView=itemview.findViewById(R.id.rv_lo_heading)
            val phonenumber:TextView=itemview.findViewById(R.id.rv_lo_phonenumber)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Contactlistviewholder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.rv_layout,parent,false)
        return Contactlistviewholder(view)
    }

    override fun getItemCount(): Int {
        return listofcontact.size
    }

    override fun onBindViewHolder(holder: Contactlistviewholder, position: Int) {
        val currentcontact=listofcontact[position]
        holder.heading.text=currentcontact.name
        holder.phonenumber.text=currentcontact.phonenumber
        holder.image.setImageURI(currentcontact.image)

    }
}