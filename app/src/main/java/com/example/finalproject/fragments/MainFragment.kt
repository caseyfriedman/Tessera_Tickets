package com.example.finalproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {


    companion object {
        const val TAG = "MainFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBuyNewPass.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(
                "INTENT",
                "BUY"
            )

            val searchFragment = SearchFragment()
            searchFragment.arguments = bundle

            (activity as MainActivity).supportFragmentManager.beginTransaction() //open a new fragment, I wonder if there's a better way tho...
                .replace(
                    R.id.fragmentContainer,
                    searchFragment
                ).addToBackStack(TAG).commit()
        }
    }
}

