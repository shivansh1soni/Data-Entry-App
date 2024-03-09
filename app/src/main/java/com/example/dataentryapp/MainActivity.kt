package com.example.dataentryapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dataentryapp.R.id
import com.example.dataentryapp.R.layout.activity_main
import com.example.dataentryapp.R.layout.layout_dialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var contactAdapter: CustomAdapter
    private val listOfContacts = mutableListOf<Contact>()

    private lateinit var rv: RecyclerView
    private lateinit var fab: FloatingActionButton

    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var image: ImageView

    private lateinit var chooseImageBtn: Button
    private lateinit var addContactBtn: Button

    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        rv = findViewById(id.rv)
        rv.layoutManager = LinearLayoutManager(this)

        contactAdapter = CustomAdapter(listOfContacts)
        rv.adapter = contactAdapter


        fab = findViewById(id.fab)

        fab.setOnClickListener {
            showDialog()
        }

    }

    private fun showDialog() {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(false)
        dialog.setContentView(layout_dialog)

        name = dialog.findViewById(id.et_name)
        phone = dialog.findViewById(id.et_phone)
        image = dialog.findViewById(id.image_preview)

        chooseImageBtn = dialog.findViewById(id.btn_choose_image)
        addContactBtn = dialog.findViewById(id.btn_add_contact)

        chooseImageBtn.setOnClickListener {
            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, 100)
        }

        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                image.visibility = View.VISIBLE
                image.setImageURI(data?.data)
            }
        }

        addContactBtn.setOnClickListener {
            val nameFromEditText = name.text.toString()
            val phoneFromEditText = phone.text.toString()
            val imageFromImageView = data?.data

            val contact = Contact(
                profileImage = imageFromImageView!!,
                profileName = nameFromEditText,
                phoneNumber = phoneFromEditText

            )

            listOfContacts.add(contact)
            contactAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
    }
}