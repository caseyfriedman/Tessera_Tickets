package com.example.finalproject.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView


import com.example.finalproject.data.Pass
import com.example.finalproject.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.pass_row.view.*

class PassAdapter(
    private val context: Context, private val uid: String
) : RecyclerView.Adapter<PassAdapter.ViewHolder>() {

    private var postList = mutableListOf<Pass>()
    private var postKeys = mutableListOf<String>()
    private var lastIndex = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.pass_row, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var post = postList[position]


/*
        if (post.imgUrl.isEmpty()) { //have to edit build gradle for this
            holder.ivPhoto.visibility = View.GONE
        } else {
            holder.ivPhoto.visibility = View.VISIBLE
            // Glide.with(context).load(post.imgUrl).into(holder.ivPhoto)
        }


        //if this is my post
        if (post.uid == uid) {
            holder.btnDelete.visibility = View.VISIBLE
            holder.btnDelete.setOnClickListener {
                removePass(holder.adapterPosition)
            }
        } else {
            holder.btnDelete.visibility = View.GONE
        }



        setAnimation(holder.itemView, position)


 */
    }


    fun addPass(pass: Pass, key: String) {
        postList.add(pass)
        postKeys.add(key)
        notifyDataSetChanged()
    }


    //this function is used when we delete our own messgage
    private fun removePass(index: Int) {

        //remove from the cloud
        FirebaseFirestore.getInstance().collection("posts").document(
            postKeys[index]
        ).delete()


        //remove from out recycler
        postList.removeAt(index)
        postKeys.removeAt(index)
        notifyItemRemoved(index)
    }


    //this message is used when someone else removes a message
    fun removePostByKey(key: String) {
        val index = postKeys.indexOf(key)
        if (index != -1) {
            postList.removeAt(index)
            postKeys.removeAt(index)
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