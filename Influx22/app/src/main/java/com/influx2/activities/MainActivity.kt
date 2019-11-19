package com.influx2.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.influx2.R
import com.influx2.Utils.Constants
import com.influx2.adapters.recyclerviewAdapter.CartAdapter
import com.influx2.adapters.viewPageradapter.ViewPagerAdapter
import com.influx2.models.MenuResponse
import com.influx2.network.ApiService
import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class MainActivity : AppCompatActivity() {

    private val adapter: ViewPagerAdapter? = null
    private var cartAdapter: CartAdapter? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager2? = null
    internal lateinit var subscription: Subscription
    internal lateinit var tabArraylist: ArrayList<MenuResponse.FoodList1>
    internal lateinit var tabArraylist1: ArrayList<MenuResponse.FoodList1>
    internal lateinit var cartArrayList: ArrayList<String>
    internal lateinit var currency: String
    internal lateinit var progressBar: ProgressBar
    internal var recyclerView: RecyclerView? = null
    internal lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    internal lateinit var bottomSheetLayout: ConstraintLayout
    internal lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this
        bottomSheetLayout = findViewById(R.id.bottom_sheet)
        //        recyclerView = findViewById(R.id.rvCart);
        progressBar = findViewById(R.id.progressBar)

        setBottomSheet()
        fetchDataFromServer()
        //        setCartAdapter();
    }

    fun setCartAdapter() {
        //        CartModel model = new CartModel();
        cartArrayList = ArrayList()
        cartArrayList.add("Gaurav")
        cartArrayList.add("prakash")
        cartArrayList.add("Rudra")
        cartArrayList.add("Vamsi")
        cartArrayList.add("Raj")
        cartArrayList.add("Thilak")

        recyclerView!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        cartAdapter = CartAdapter(context, cartArrayList)
        recyclerView!!.adapter = adapter
    }

    private fun setBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> Log.d("", "STATE HIDDEN")
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        Log.d("", "STATE EXPANDED")
                        // update toggle button text
                        Log.d("", "Expand BottomSheet")
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        Log.d("", "STATE COLLAPSED")
                        // update collapsed button text
                        Log.d("", "Collapse BottomSheet")
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> Log.d("", "STATE DRAGGING")
                    BottomSheetBehavior.STATE_SETTLING -> Log.d("", "STATE SETTLING")
                }
                Log.d("", "onStateChanged: $newState")
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
    }

    private fun fetchDataFromServer() {
        progressBar.visibility = View.VISIBLE
        subscription = ApiService.apiService.menu
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<MenuResponse> {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        Log.d("", "gaurav = $e")
                        progressBar.visibility = View.GONE
                    }

                    override fun onNext(response: MenuResponse) {
                        progressBar.visibility = View.GONE
                        getData(response)
                    }
                })
    }

    private fun getData(response: MenuResponse) {
        if (response.status?.Id == Constants.SUCCESSCODE) {
            currency = response.Currency.toString()
            tabArraylist = ArrayList<MenuResponse.FoodList1>()
            tabArraylist1 = ArrayList<MenuResponse.FoodList1>()
            for (i in response.FoodList?.indices!!) {
                tabArraylist.add(response.FoodList!![i])

                viewPager = findViewById(R.id.viewPager)
                tabLayout = findViewById<View>(R.id.tabLayout) as TabLayout
                viewPager!!.adapter = ViewPagerAdapter(this, tabArraylist, currency, viewPager!!)
                TabLayoutMediator(tabLayout!!, viewPager!!,
                        TabLayoutMediator.TabConfigurationStrategy { tab, position -> tab.text = tabArraylist[position].TabName }).attach()
            }
        } else
            Toast.makeText(this, "Some Went Wrong!!", Toast.LENGTH_LONG).show()
    }
}
