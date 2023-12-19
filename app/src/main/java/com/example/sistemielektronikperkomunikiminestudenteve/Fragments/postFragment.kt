package com.example.sistemielektronikperkomunikiminestudenteve.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sistemielektronikperkomunikiminestudenteve.Models.GetPostsModel
import com.example.sistemielektronikperkomunikiminestudenteve.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class postFragment : Fragment(R.layout.fragment_post) {

    private lateinit var title:EditText
    private lateinit var description:EditText

    private lateinit var databaseReference:DatabaseReference
    var max:Long = 0;



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        title = view.findViewById(R.id.title)
        description = view.findViewById(R.id.postDescription)

        databaseReference = FirebaseDatabase.getInstance().getReference("POSTS")

        view.findViewById<Button>(R.id.postButton).setOnClickListener {
            savePostData()
        }


    }

    @SuppressLint("SuspiciousIndentation")
    private fun savePostData() {

        val title = title.text.toString()
        val description = description.text.toString()






        val postID = databaseReference.push().key!!.toString()
        val post = GetPostsModel(title, description, "0" , "0")

            databaseReference.child(postID).setValue(post)
            .addOnCompleteListener{
                Toast.makeText(context, "" +
                        "Added", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            }


    }
}


