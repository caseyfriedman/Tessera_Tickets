package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.finalproject.data.City
import com.example.finalproject.data.Pass
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_buy_pass.*
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_buy_pass.view.*

class BuyPassActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    var city = "Budapest"
    var cityUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_pass)




        loadOptionsIntoObject()



        btnBuy.setOnClickListener {
            val passCreated = onBuy()
            storePassInDatabase(passCreated)
        }


    }

    private fun onBuy(): Pass {

        return Pass(
            city,
            findViewById<RadioButton>(groupModes.checkedRadioButtonId).text.toString(),
            findViewById<RadioButton>(groupDurations.checkedRadioButtonId).text.toString(),
            findViewById<RadioButton>(groupDiscounts.checkedRadioButtonId).text.toString(),
            cityUrl
        )
    }


    private fun loadOptionsIntoObject() {

        tvCity.text = city
        db.collection("cities").document("Budapest").get().addOnSuccessListener { document ->
            if (document != null) {
                val cityObject = document.toObject(City::class.java)!!
                setRadioButtons(cityObject)
                cityUrl = cityObject.imgUrl
            } else {
                Log.w("MY_TAG", "NULL OBJECT")
            }


        }
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
            Toast.makeText(this@BuyPassActivity, "Error: ${it.message}", Toast.LENGTH_LONG).show()
        }

    }


}



