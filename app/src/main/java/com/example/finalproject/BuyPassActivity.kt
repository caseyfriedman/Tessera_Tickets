package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_buy_pass.*

class BuyPassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_pass)


        seg_one.setOnClickListener {
            seg_one.setBackgroundColor(resources.getColor(R.color.colorBlack))
            seg_three.setBackgroundColor(resources.getColor(R.color.colorWhite))
            seg_two.setBackgroundColor(resources.getColor(R.color.colorWhite))
            seg_one.setTextColor(resources.getColor(R.color.colorWhite))
            seg_two.setTextColor(resources.getColor(R.color.colorBlack))
            seg_three.setTextColor(resources.getColor(R.color.colorBlack))
        }


        seg_two.setOnClickListener {
            seg_one.setBackgroundColor(resources.getColor(R.color.colorWhite))
            seg_three.setBackgroundColor(resources.getColor(R.color.colorWhite))
            seg_two.setBackgroundColor(resources.getColor(R.color.colorBlack))
            seg_two.setTextColor(resources.getColor(R.color.colorWhite))
            seg_one.setTextColor(resources.getColor(R.color.colorBlack))
            seg_three.setTextColor(resources.getColor(R.color.colorBlack))
        }

        seg_three.setOnClickListener {
            seg_one.setBackgroundColor(resources.getColor(R.color.colorWhite))
            seg_three.setBackgroundColor(resources.getColor(R.color.colorBlack))
            seg_two.setBackgroundColor(resources.getColor(R.color.colorWhite))
            seg_three.setTextColor(resources.getColor(R.color.colorWhite))
            seg_one.setTextColor(resources.getColor(R.color.colorBlack))
            seg_two.setTextColor(resources.getColor(R.color.colorBlack))
        }

    }
}
