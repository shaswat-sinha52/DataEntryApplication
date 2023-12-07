 package com.example.dataentryapplication

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

 class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var fab:FloatingActionButton
    lateinit var image_preview1:ImageView
    lateinit var edt_name:EditText
    lateinit var edt_number:EditText
    lateinit var btn_choose_image:Button
    lateinit var btn_addcontact:Button
    lateinit var contactAdapter:ContactAdapter
    val  listofcontact= mutableListOf<Contact>()
     lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.rv)
        recyclerView.layoutManager=LinearLayoutManager(this)
        contactAdapter= ContactAdapter(listofcontact)
        recyclerView.adapter=contactAdapter

        fab=findViewById(R.id.rv_floating_button)

        fab.setOnClickListener{
            showdialog()
        }
    }
     private fun showdialog(){
         dialog=Dialog(this)
         dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
         dialog.setCancelable(false)
         dialog.setContentView(R.layout.layout_dialog)

         edt_name=dialog.findViewById(R.id.pop_edt1)
         edt_number=dialog.findViewById(R.id.pop_edt2)
         image_preview1=dialog.findViewById(R.id.image_preview)
         btn_choose_image=dialog.findViewById(R.id.btn_image_choose)
         btn_addcontact=dialog.findViewById(R.id.btn_addcontact)

         btn_choose_image.setOnClickListener {
             val galleryintent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
             startActivityForResult(galleryintent,101)
         }

         dialog.show()
     }
     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         if(requestCode== 101 && resultCode == RESULT_OK) {
             image_preview1.visibility=View.VISIBLE
             image_preview1.setImageURI(data?.data)

             btn_addcontact.setOnClickListener {
                 val namefromedt=edt_name.text.toString()
                 val numberfromedt=edt_number.text.toString()
                 val imagefromedt=data?.data

                 val contact=Contact(
                     name=namefromedt,
                     phonenumber = numberfromedt,
                     image = imagefromedt!!
                 )
                 listofcontact.add(contact)
                 contactAdapter.notifyDataSetChanged()
                 dialog.dismiss()
             }
         }
     }
 }