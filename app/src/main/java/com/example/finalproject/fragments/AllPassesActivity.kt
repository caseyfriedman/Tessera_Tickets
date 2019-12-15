package com.example.finalproject.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import com.example.finalproject.adapter.PassAdapter
import com.example.finalproject.data.Pass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*

import kotlinx.android.synthetic.main.activity_all_passes.*

class AllPassesActivity : Fragment() {


    companion object {
        const val TAG = "AllPassesActivity"
    }

    private lateinit var passAdapter: PassAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.activity_all_passes, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        passAdapter = PassAdapter(
            context as MainActivity, FirebaseAuth.getInstance().currentUser!!.uid
        )

        val linLayoutManager = LinearLayoutManager(context as MainActivity)
        linLayoutManager.reverseLayout = true

        linLayoutManager.stackFromEnd = true



        recyclerPass.layoutManager = linLayoutManager

        recyclerPass.adapter = passAdapter




        fab.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction() //open a new fragment, I wonder if there's a better way tho...
                .replace(
                    R.id.fragmentContainer,
                    SearchFragment()
                ).addToBackStack(TAG).commit()
        }
        queryPosts()
    }


    private fun queryPosts() {
        val db = FirebaseFirestore.getInstance()

        val query = db.collection("users")
            .document(FirebaseAuth.getInstance().currentUser!!.uid).collection("passes")


        query.addSnapshotListener { querySnapshot, _ ->
            for (doc in querySnapshot!!.documents) {
                val pass = doc.toObject(Pass::class.java)
                passAdapter.addPass(pass!!)
            }
        }
    }
}