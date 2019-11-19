package com.influx2.adapters.recyclerviewAdapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.influx2.R
import com.influx2.models.MenuResponse
import java.util.*

class OptionAdapter(internal var context: Context, subDishArrayList: ArrayList<MenuResponse.FoodList1.FNBList.SubItems>) : RecyclerView.Adapter<OptionAdapter.CustomHolder>() {
    internal var subDishArrayList = ArrayList<MenuResponse.FoodList1.FNBList.SubItems>()
    internal var index = 0

    init {
        this.subDishArrayList = subDishArrayList
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomHolder {
        return CustomHolder(LayoutInflater.from(context).inflate(R.layout.option_item, viewGroup, false))
    }

    override fun onBindViewHolder(customHolder: CustomHolder, i: Int) {
        customHolder.tvOption.text = subDishArrayList[i].Name
        customHolder.itemView.setOnClickListener {
            index = i
            notifyDataSetChanged()
        }
        if (index == i) {
            customHolder.tvOption.setTextColor(Color.parseColor("#000000"))
            customHolder.itemView.setBackgroundResource(R.drawable.yellow_drawable)
        } else {
            customHolder.tvOption.setTextColor(Color.parseColor("#B3B1B1"))
            customHolder.itemView.setBackgroundResource(R.drawable.gray_border_drawable)
        }
    }

    override fun getItemCount(): Int {
        return subDishArrayList.size
    }

    inner class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tvOption: TextView

        init {

            tvOption = itemView.findViewById(R.id.tvOption)
        }
    }
}
