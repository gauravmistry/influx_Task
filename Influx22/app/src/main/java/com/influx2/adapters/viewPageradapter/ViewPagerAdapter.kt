package com.influx2.adapters.viewPageradapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.influx2.R
import com.influx2.adapters.recyclerviewAdapter.ItemsAdapter
import com.influx2.models.MenuResponse
import java.util.*

class ViewPagerAdapter(internal var context: Context, private val arrayList: ArrayList<MenuResponse.FoodList1>, currency: String, private val viewPager2: ViewPager2) : RecyclerView.Adapter<ViewPagerAdapter.CustomViewHolder>() {
    private var menuArrayList: ArrayList<MenuResponse.FoodList1.FNBList>? = null
    private val mInflater: LayoutInflater? = null
    internal lateinit var adapter: ItemsAdapter
    internal var currency = ""

    init {
        this.currency = currency
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_drinks, parent, false))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        menuArrayList = ArrayList<MenuResponse.FoodList1.FNBList>()
        if (arrayList[position].fnblist != null) {
            for (i in arrayList[position].fnblist?.indices!!) {
                menuArrayList!!.add(arrayList[position].fnblist?.get(i)!!)
            }
            if (menuArrayList!!.size > 0) {
                holder.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = ItemsAdapter(context, menuArrayList!!, currency)
                holder.recyclerView.adapter = adapter
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class CustomViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var recyclerView: RecyclerView

        init {
            recyclerView = itemView.findViewById(R.id.rv)
        }
    }
}
