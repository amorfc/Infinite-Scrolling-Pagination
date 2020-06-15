package com.example.flickerapp.Activity.HomeActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickerapp.Activity.BaseActivity
import com.example.flickerapp.Activity.RecentEndPoint
import com.example.flickerapp.Activity.ServiceBuilder
import com.example.flickerapp.Models.ApiRes
import com.example.flickerapp.Models.Photo
import com.example.flickerapp.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : BaseActivity() {
    var totalItemCount : Int = 0
    var pastVisibleItemCount: Int =0
    var visibleItemCount: Int = 0
    var isLoading: Boolean = false
    var pageId =1
    var photos : MutableList<Photo> = mutableListOf()
    lateinit var mLinearLayoutManager: LinearLayoutManager
    lateinit var mAdapter: PhotoAdapter
    lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.visibility = View.VISIBLE
        mLinearLayoutManager = GridLayoutManager(this@HomeActivity,3)
        mRecyclerView = findViewById(R.id.photos_recycler_view)
        mRecyclerView.layoutManager = mLinearLayoutManager
        mRecyclerView.setHasFixedSize(true)
        fetchPhotosList(pageId)
    }

    fun fetchPhotosList(pageId :Int){
        ServiceBuilder
            .buildService(RecentEndPoint::class.java)
            .getRecent(getString(R.string.api_key),"json",pageId)
            .enqueue(object : Callback<ApiRes> {
            override fun onResponse(call: Call<ApiRes>, response: Response<ApiRes>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    response.body()?.let {
                        isLoading = true
                        setUpAdapter(it.photos.photo)
                        Log.d("SizeOfApiCall", ""+ it.photos.photo.size )
                    } ?: run{
                        Log.i("ERRORR","ERROR")
                        Toast.makeText(baseContext, "Error fetching images list", Toast.LENGTH_LONG).show()
                    }
                }
            }
            override fun onFailure(call: Call<ApiRes>, t: Throwable) {
                Log.i("ERRORR","ERROR")
                progressBar.visibility = View.GONE
            }
        })
    }

    fun setUpAdapter(photosList: ArrayList<Photo>) {
        if(photos.size == 0){
                photos = photosList
                mAdapter = PhotoAdapter(photos as ArrayList<Photo>)
                mRecyclerView.adapter = mAdapter
        }else{
            var currentPosition = (mRecyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
            photos.addAll(photosList)
            mAdapter.notifyDataSetChanged()
            mRecyclerView.scrollToPosition(currentPosition)
        }
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy>0){
                    visibleItemCount = mLinearLayoutManager.childCount
                    totalItemCount =mLinearLayoutManager.itemCount
                    pastVisibleItemCount = (mRecyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()

                if(isLoading){
                        if(visibleItemCount + pastVisibleItemCount >= totalItemCount ){
                        isLoading=false
                        progressBar.visibility =View.VISIBLE
                        pageId++
                        fetchPhotosList(pageId)
                    }
                }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

    }
    companion object {
        val INTENT_PHOTO_KEY = "PHOTO"
    }

}