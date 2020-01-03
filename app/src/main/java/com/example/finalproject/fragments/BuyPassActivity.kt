package com.example.finalproject.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import com.example.finalproject.data.City
import com.example.finalproject.data.Pass
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_buy_pass.*
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_buy_pass.view.*

class BuyPassActivity : Fragment() {

    val db = FirebaseFirestore.getInstance()
    var city: String = ""
    var cityUrl: String = ""

    companion object {

        const val TAG = "BuyPassActivity"

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.activity_buy_pass, container, false)



        city = arguments?.getString("CITY").toString()



        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadOptionsIntoObject()
        btnBuy.setOnClickListener {


            if (allBoxesChecked()) {

                val passCreated = onBuy()
                storePassInDatabase(passCreated)
                //Wanted to require you to check a box, but it didn't end up working :/

            } else {
                Snackbar.make(view, "All rows must be selected", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun onBuy(): Pass {


        return Pass(
            city,
            view?.findViewById<RadioButton>(groupModes.checkedRadioButtonId)?.text.toString(),
            view?.findViewById<RadioButton>(groupDurations.checkedRadioButtonId)?.text.toString(),
            view?.findViewById<RadioButton>(groupDiscounts.checkedRadioButtonId)?.text.toString(),
            cityUrl
        )

    }


    private fun loadOptionsIntoObject() {

        tvCity.text = city
        db.collection("cities").document(city).get().addOnSuccessListener { document ->
            if (document != null) {
                val cityObject = document.toObject(City::class.java)!!
                setRadioButtons(cityObject)
                cityUrl = cityObject.imgUrl
            } else {
                Log.w("MY_TAG", "NULL OBJECT")
            }


        }
    }


    /**
     * Look up "how to return an object from an on success listener
     */
    fun getCity(city: String): City {

        var cityObject = City()

        val db = FirebaseFirestore.getInstance()
        db.collection("cities").document(city).get().addOnSuccessListener { document ->
            if (document != null) {
                cityObject = document.toObject(City::class.java)!!
            } else {
                Log.w("MY_TAG", "NULL CITY")
            }

        }
        return cityObject
    }

    private fun setRadioButtons(cityObject: City) {


        groupModes.modes_one.text = cityObject.modes[0]
        groupModes.modes_two.text = cityObject.modes[1]
        groupModes.modes_three.text = cityObject.modes[2]

        groupDiscounts.disc_one.text = cityObject.discounts[0]
        groupDiscounts.disc_two.text = cityObject.discounts[1]
        groupDiscounts.disc_three.text = cityObject.discounts[2]

        groupDurations.dur_one.text = cityObject.durations[0]
        groupDurations.dur_two.text = cityObject.durations[1]
        groupDurations.dur_three.text = cityObject.durations[2]

    }

    private fun storePassInDatabase(pass: Pass) {


        val messageRef = db.collection("users")
            .document(FirebaseAuth.getInstance().currentUser!!.uid).collection("passes")

        messageRef.add(pass).addOnSuccessListener {
            tvSuccess.visibility = View.VISIBLE
        }.addOnFailureListener {
            Toast.makeText(context as MainActivity, "Error: ${it.message}", Toast.LENGTH_LONG)
                .show()
        }

    }


    private fun allBoxesChecked(): Boolean {

        when {
            groupModes.checkedRadioButtonId == -1 -> {
                return false
            }
            groupDurations.checkedRadioButtonId == -1 -> {
                return false
            }
            groupDiscounts.checkedRadioButtonId == -1 -> {
                return false
            }
        }
        return true
    }

}



