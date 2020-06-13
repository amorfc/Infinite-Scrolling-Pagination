package com.example.flickerapp.Activity.HomeActivity

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickerapp.Activity.BaseActivity
import com.example.flickerapp.Activity.RecentEndPoint
import com.example.flickerapp.Activity.ServiceBuilder
import com.example.flickerapp.Models.ApiRes
import com.example.flickerapp.Models.Photo
import com.example.flickerapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : BaseActivity() {
    private var loadTime: Long = 500 * 1L
    private var isLoading: Boolean = false
    private var itemPosition: Int = 0 //Position inf

    private var arrayList : ArrayList<Photo> = ArrayList()

    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var mAdapter: Adapter
    lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val request = ServiceBuilder.buildService(RecentEndPoint::class.java)
        val call = request.getRecent(getString(R.string.api_key),"json",1)
        call.enqueue(object : Callback<ApiRes> {
            override fun onResponse(call: Call<ApiRes>, response: Response<ApiRes>) {
                if (response.isSuccessful) {
                    Log.i("ResponseDone", "ResponseDone")
                    mLayoutManager = LinearLayoutManager(this@HomeActivity)
                    mAdapter = Adapter(response.body()?.photos?.photo!!)
                    mRecyclerView = findViewById(R.id.photos_recycler_view)
                    mRecyclerView.layoutManager = mLayoutManager
                    mRecyclerView.adapter = mAdapter

                }
            }
            override fun onFailure(call: Call<ApiRes>, t: Throwable) {
                Log.i("ERRORR","ERROR")
            }
        })

    }
}