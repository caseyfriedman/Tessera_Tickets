package com.example.finalproject.adapter


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import com.example.finalproject.data.City
import com.example.finalproject.fragments.AllPassesActivity
import com.example.finalproject.fragments.BuyPassActivity
import com.example.finalproject.fragments.InfoFragment
import com.example.finalproject.fragments.SearchFragment
import kotlinx.android.synthetic.main.city_row.view.*

class CitiesAdapter(
    private val context: Context,
    uid: String, private var intent: String
) : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    private var citiesList = mutableListOf<City>()
    private var lastIndex = -1

    /**
     * There are no add or delete methods to make things simpler
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.city_row, parent, false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = citiesList[position]
        holder.tvCity.text = city.name




        setIntent(holder, intent)

    }

    override fun getItemCount(): Int {
        return citiesList.size
    }


    fun addCity(city: City) {
        citiesList.add(city)
        notifyDataSetChanged()
    }


    private fun removeCity(index: Int) {
        /**
         * You shouldn't be able to remove a city from the master list of cities
         * Ideally I would make a separate list if I had more time
         */


/*
        //remove from the cloud
        FirebaseFirestore.getInstance().collection("").document().delete()


        //remove from out recycler
        citiesList.removeAt(index)
        passKeys.removeAt(index)
        notifyItemRemoved(index)


 */
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
        RecyclerView.ViewHolder(itemView) {


        val tvCity = itemView.tvCity

    }

    private fun setIntent(holder: ViewHolder, intent: String) {
        when (intent) {
            "BUY" -> onClickGoToBuy(holder)
            "INFO" -> onClickGoToInfo(holder)
        }

    }


    private fun onClickGoToBuy(holder: ViewHolder) { //need to add a param for the calling activity

        holder.itemView.setOnClickListener {


            goToBuy(holder.tvCity.text.toString())


        }
    }


    private fun onClickGoToInfo(holder: ViewHolder) {

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("CITY", holder.tvCity.text.toString())

            val infoFrag = InfoFragment()
            infoFrag.arguments = bundle


            val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, infoFrag).addToBackStack(SearchFragment.TAG)
                .commit()
        }
    }

    fun goToBuy(name: String) {
        val bundle = Bundle()
        bundle.putString("CITY", name)

        val buyPassActivity = BuyPassActivity()
        buyPassActivity.arguments = bundle


        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, buyPassActivity)
            .addToBackStack(AllPassesActivity.TAG).commit() //need to change TAG

    }
}
