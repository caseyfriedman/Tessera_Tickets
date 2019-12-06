package com.example.finalproject

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.adapter.PassAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*

import kotlinx.android.synthetic.main.activity_all_passes.*

class AllPassesActivity : AppCompatActivity() {


    private lateinit var passAdapter: PassAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_passes)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }




        passAdapter = PassAdapter(
            this, FirebaseAuth.getInstance().currentUser!!.uid
        )

        var linLayoutManager = LinearLayoutManager(this)
        linLayoutManager.reverseLayout = true

        linLayoutManager.stackFromEnd = true


        recyclerPass.layoutManager = linLayoutManager

        recyclerPass.adapter = passAdapter

        queryPosts()
    }


    private fun queryPosts() {
        val db = FirebaseFirestore.getInstance()
        val query = db.collection("posts")
    }
}