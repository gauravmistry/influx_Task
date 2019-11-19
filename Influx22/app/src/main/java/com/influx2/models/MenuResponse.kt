package com.influx2.models

import android.os.Parcel
import android.os.Parcelable

class MenuResponse {
    var status: Status? = null
    var Currency: String? = null
    var FoodList: List<FoodList1>? = null

    inner class Status {
        var Id: String? = null
        var Description: String? = null
    }

    inner class FoodList1 protected constructor(`in`: Parcel) : Parcelable {
        var TabName: String? = null
        var fnblist: List<FNBList>? = null

        @JvmField
        val CREATOR: Parcelable.Creator<FoodList1> = object : Parcelable.Creator<FoodList1> {
            override fun createFromParcel(`in`: Parcel): FoodList1 {
                return FoodList1(`in`)
            }

            override fun newArray(size: Int): Array<FoodList1?> {
                return arrayOfNulls(size)
            }
        }

        init {
            TabName = `in`.readString()
        }

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(TabName)
        }

        inner class FNBList {
            var Cinemaid: String? = null
            var TabName: String? = null
            var VistaFoodItemId: String? = null
            var Name: String? = null
            var PriceInCents: String? = null
            var ItemPrice: String? = null
            var ImageUrl: String? = null
            var ImgUrl: String? = null
            var VegClass: String? = null
            var SubItemCount: Int? = null
            var SevenStarExperience: Boolean? = null
            var OtherExperience: Boolean? = null
            var subitems: List<SubItems>? = null

            inner class SubItems {
                var Name: String? = null
                var PriceInCents: String? = null
                var SubitemPrice: String? = null
                var VistaParentFoodItemId: String? = null
                var VistaSubFoodItemId: String? = null
            }
        }
    }
}
