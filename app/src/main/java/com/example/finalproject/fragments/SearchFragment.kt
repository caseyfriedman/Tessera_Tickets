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
import com.google.firebase.firestore.FirebaseFirestore
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


        val intent: String = if (arguments?.getString("INTENT").isNullOrEmpty()) {
            "INFO" //this means we are coming from search page directly
        } else { //we are coming from the buy page or something else
            arguments?.getString("INTENT").toString() //it's never gonna be info

        }

        cityAdapter = CitiesAdapter(
            context as MainActivity, FirebaseAuth.getInstance().currentUser!!.uid, intent
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


        var allPostsListener = query.addSnapshotListener { querySnapshot, _ ->
            for (doc in querySnapshot!!.documents) {
                val city = doc.toObject(City::class.java)
                cityAdapter.addCity(city!!)
            }
        }
    }
}


