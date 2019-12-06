package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.adapter.PassAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSwitch.setOnClickListener {
            startActivity(Intent(this@MainActivity,AllPassesActivity::class.java))
        }

        btnPass.setOnClickListener {
            startActivity(Intent(this@MainActivity,BuyPassActivity::class.java))
        }

        //setSupportActionBar(toolbar)

        /*Z
        fab.setOnClickListener {
            //startActivity(Intent(this@ForumActivity, CreatePostActivity::class.java))
        }

        passAdapter = PassAdapter(
            this, FirebaseAuth.getInstance().currentUser!!.uid
        )

        var linLayoutManager = LinearLayoutManager(this)
        linLayoutManager.reverseLayout = true

        linLayoutManager.stackFromEnd = true


        recyclerPosts.layoutManager = linLayoutManager

        recyclerPosts.adapter = passAdapter

        queryPosts()

         */
    }

    private fun queryPosts() {

        /*
        val db = FirebaseFirestore.getInstance()
        val query = db.collection("posts")


        var allPostsListener = query.addSnapshotListener(
            object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    querySnapshot: QuerySnapshot?,
                    e: FirebaseFirestoreException?
                ) {

                    if (e != null) {
                        Toast.makeText(
                            this@ForumActivity,
                            "listen error: ${e.message}",
                            Toast.LENGTH_LONG
                        ).show()
                        return
                    }

                    for (dc in querySnapshot!!.documentChanges) {
                        when (dc.type) {
                            DocumentChange.Type.ADDED -> {
                                val post = dc.document.toObject(Post::class.java)
                                passAdapter.addPost(post, dc.document.id)
                            }
                            DocumentChange.Type.MODIFIED -> {
                                Toast.makeText(
                                    this@ForumActivity,
                                    "update: ${dc.document.id}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            DocumentChange.Type.REMOVED -> {
                                passAdapter.removePostByKey(dc.document.id)
                            }
                        }
                    }


                }
            })
    }*/
}
}
