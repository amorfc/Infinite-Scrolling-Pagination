package com.example.flickerapp.API

import com.example.flickerapp.Models.ApiRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecentEndPoint {
    @GET("?method=flickr.photos.getRecent")//
    fun getRecent(@Query("api_key") api_key :String,
                  @Query("page") page : Int,//For paging
                  @Query("per_page")per_page :Int = 20,//How many photo information we want for each page
                  @Query("format" )format : String = "json",//Which format we want from https request response
                  @Query("nojsoncallback") nojsoncallback :Int = 1 ) : Call<ApiRes>//We just want to get raw JSON no  function wrapper

}
