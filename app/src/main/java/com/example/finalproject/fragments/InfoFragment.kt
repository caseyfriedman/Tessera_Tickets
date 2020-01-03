package com.example.finalproject.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.finalproject.R
import com.example.finalproject.data.City
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {


    var city = ""

    companion object {
        const val TAG = "InfoFragment"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        city = arguments?.getString("CITY").toString()
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        BuyPassActivity().getCity(city)




        btnBuyPassCity.setOnClickListener {

        }

    }

    private fun loadCity() {
        tvCity.text = city
    }


}
