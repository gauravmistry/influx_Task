package com.influx2.network

import com.influx2.models.MenuResponse
import retrofit2.http.GET
import rx.Observable

interface ApiList {

    //Menu
    @get:GET("5b700cff2e00005c009365cf")
    val menu: Observable<MenuResponse>

}
