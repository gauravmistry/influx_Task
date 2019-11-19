package com.influx2.adapters.recyclerviewAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.influx2.R
import com.influx2.models.MenuResponse
import com.squareup.picasso.Picasso
import java.util.*

class ItemsAdapter(internal var context: Context, arrayList: ArrayList<MenuResponse.FoodList1.FNBList>,
                   currency: String) : RecyclerView.Adapter<ItemsAdapter.CustomHolder>() {
    internal var arrayList = ArrayList<MenuResponse.FoodList1.FNBList>()
    internal var subDishArrayList = ArrayList<MenuResponse.FoodList1.FNBList.SubItems>()
    internal var currency = ""
    internal lateinit var adapter: OptionAdapter

    init {
        this.arrayList = arrayList
        this.currency = currency
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomHolder {
        return CustomHolder(LayoutInflater.from(context).inflate(R.layout.dish_item, viewGroup, false))
    }

    override fun onBindViewHolder(customHolder: CustomHolder, i: Int) {
        customHolder.tvFood.text = arrayList[i].Name
        customHolder.tvPrice.text = currency + " " + arrayList[i].ItemPrice
        Picasso.with(context)
                .load(arrayList[i].ImageUrl)
                .resize(500, 500)
                .placeholder(R.drawable.reload_icon)
                .error(R.drawable.error)
                .into(customHolder.ivFood)
        subDishArrayList = ArrayList<MenuResponse.FoodList1.FNBList.SubItems>()
        if (arrayList[i].subitems != null) {
            for (j in arrayList[i].subitems?.indices!!)
                subDishArrayList.add(arrayList[i].subitems?.get(j)!!)
        }
        if (subDishArrayList.size > 0) {
            customHolder.rvOptions.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = OptionAdapter(context, subDishArrayList)
            customHolder.rvOptions.adapter = adapter
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var ivFood: ImageView
        internal var ivRemove: ImageView
        internal var ivAdd: ImageView
        internal var tvFood: TextView
        internal var tvPrice: TextView
        internal var tvQTY: TextView
        internal var rvOptions: RecyclerView

        init {
            ivFood = itemView.findViewById(R.id.ivDish)
            tvFood = itemView.findViewById(R.id.tvDishName)
            tvPrice = itemView.findViewById(R.id.tvDishPrice)
            ivRemove = itemView.findViewById(R.id.ivRemove)
            ivAdd = itemView.findViewById(R.id.ivAdd)
            tvQTY = itemView.findViewById(R.id.tvDishQTY)
            rvOptions = itemView.findViewById(R.id.rvSubDish)
        }
    }
}
