package com.example.flickerapp.Activity

import com.example.flickerapp.Models.ApiRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecentEndPoint {
    @GET("?method=flickr.photos.getRecent")
    fun getRecent(@Query("api_key") api_key :String,
                  @Query("format" )format : String,
                  @Query("page") page : Int,
                  @Query("per_page")per_page :Int = 20,
                  @Query("nojsoncallback") nojsoncallback :Int = 1 ) : Call<ApiRes>

}
