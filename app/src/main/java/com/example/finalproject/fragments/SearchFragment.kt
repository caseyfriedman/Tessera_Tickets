package com.example.finalproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import com.example.finalproject.adapter.CitiesAdapter
import com.example.finalproject.data.City
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

import kotlinx.android.synthetic.main.content_search_fragment.*

class SearchFragment : Fragment() {


    companion object {
        const val TAG = "SearchFragment"
    }

    private lateinit var cityAdapter: CitiesAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.activity_search_fragment, container, false)


    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        cityAdapter = CitiesAdapter(
            context as MainActivity, FirebaseAuth.getInstance().currentUser!!.uid
        )

        var linLayoutManager = LinearLayoutManager(context as MainActivity)
        linLayoutManager.reverseLayout = true

        linLayoutManager.stackFromEnd = true



        recyclerCity.layoutManager = linLayoutManager

        recyclerCity.adapter = cityAdapter


        queryPosts()
    }


    private fun queryPosts() {
        val db = FirebaseFirestore.getInstance()

        val query = db.collection("cities")


        var allPostsListener = query.addSnapshotListener(
            object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    querySnapshot: QuerySnapshot?,
                    e: FirebaseFirestoreException?
                ) {

                    for (doc in querySnapshot!!.documents) {
                        val city = doc.toObject(City::class.java)
                        cityAdapter.addCity(city!!)
                    }
                }
            })
    }
}


