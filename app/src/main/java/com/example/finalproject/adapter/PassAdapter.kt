package com.example.finalproject.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


import com.example.finalproject.data.Pass
import com.example.finalproject.R
import com.example.finalproject.fragments.BuyPassActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.pass_row.view.*

class PassAdapter(
    private val context: Context,uid: String
) : RecyclerView.Adapter<PassAdapter.ViewHolder>() {

    private var passList = mutableListOf<Pass>()
    private var passKeys = mutableListOf<String>()
    private var lastIndex = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.pass_row, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return passList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pass = passList[position]

        holder.tvCity.text = pass.city
        holder.tvExpiry.text = pass.duration



        if (pass.imgUrl.isEmpty()) {
            holder.ivSymbol.visibility = View.GONE
        } else {
            holder.ivSymbol.visibility = View.VISIBLE
            Glide.with(context).load(pass.imgUrl).into(holder.ivSymbol)
        }

    }


    fun addPass(pass: Pass) {
        passList.add(pass)
        notifyDataSetChanged()
    }


    //this function is used when we delete our own messgage
    private fun removePass(index: Int) {

        //remove from the cloud
        FirebaseFirestore.getInstance().collection("posts").document(
            passKeys[index]
        ).delete()


        //remove from out recycler
        passList.removeAt(index)
        passKeys.removeAt(index)
        notifyItemRemoved(index)
    }


    //this message is used when someone else removes a message
    fun removePostByKey(key: String) {
        val index = passKeys.indexOf(key)
        if (index != -1) {
            passList.removeAt(index)
            passKeys.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastIndex) {
            val animation = AnimationUtils.loadAnimation(
                context,
                android.R.anim.slide_in_left
            )
            viewToAnimate.startAnimation(animation)
            lastIndex = position
        }
    }


    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) { // holds every item that is visible on screen

        val tvCity = itemView.tvCity
        val tvExpiry = itemView.tvExpiry
        val btnRenew = itemView.btnRenew
        val ivSymbol = itemView.ivSymbol
        val ivBarcode = itemView.ivBarcode

    }
}