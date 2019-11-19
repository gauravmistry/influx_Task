package com.influx2.adapters.recyclerviewAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.influx2.R
import java.util.*

class CartAdapter(internal var context: Context, cartArrayList: ArrayList<String>) : RecyclerView.Adapter<CartAdapter.CustomHolder>() {
    internal var cartArrayList = ArrayList<String>()

    init {
        this.cartArrayList = cartArrayList
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomHolder {
        return CustomHolder(LayoutInflater.from(context).inflate(R.layout.cart_item, viewGroup, false))
    }

    override fun onBindViewHolder(customHolder: CustomHolder, i: Int) {
        customHolder.tvOption.text = cartArrayList[i]

    }

    override fun getItemCount(): Int {
        return cartArrayList.size
    }

    inner class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tvOption: TextView

        init {

            tvOption = itemView.findViewById(R.id.tvOption)
        }
    }
}
