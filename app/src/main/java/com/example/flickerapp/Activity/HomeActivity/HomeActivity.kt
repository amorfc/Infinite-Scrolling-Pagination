package com.example.flickerapp.Activity.HomeActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickerapp.Activity.BaseActivity
import com.example.flickerapp.API.RecentEndPoint
import com.example.flickerapp.API.ServiceBuilder
import com.example.flickerapp.Models.ApiRes
import com.example.flickerapp.Models.Photo
import com.example.flickerapp.Models.Photos
import com.example.flickerapp.R
import com.example.flickerapp.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class HomeActivity : BaseActivity() {

    var totalItemCount : Int = 0
    var pastVisibleItemCount: Int =0
    var visibleItemCount: Int = 0
    var isLoading: Boolean = false
    var pageId =1

    var photos : MutableList<Photo> = mutableListOf()
    lateinit var mLinearLayoutManager: LinearLayoutManager
    lateinit var mAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initVariables()

        fetchPhotosList()
    }

    private fun initVariables(){
        progressBarForLoadMore.visibility = View.VISIBLE
        mLinearLayoutManager = GridLayoutManager(this@HomeActivity,3)
        photos_recycler_view.layoutManager = mLinearLayoutManager
        photos_recycler_view.setHasFixedSize(true)
    }

    private fun fetchPhotosList(pageId :Int = 1){
        ServiceBuilder
            .buildService(RecentEndPoint::class.java)
            .getRecent(Constants.api_key,pageId)
            .enqueue(object : Callback<ApiRes>
            {
            override fun onResponse(call: Call<ApiRes>, response: Response<ApiRes>) {

                progressBarForLoadMore.visibility = View.GONE

                if (response.isSuccessful) {

                    response.body()?.let {

                        isLoading = true
                        setUpAdapter(it.photos.photo)

                    } ?: run{
                        Log.i("ERRORR","ERROR")
                        Toast.makeText(baseContext, "Error fetching images list", Toast.LENGTH_LONG).show()
                    }
                }
            }
            override fun onFailure(call: Call<ApiRes>, t: Throwable) {
                Log.i("ERRORR","ERROR")
                progressBarForLoadMore.visibility = View.GONE
            }
        })
    }

    private fun setUpAdapter(photosList: ArrayList<Photo>) {
        if(photos.size == 0){

                photos = photosList
                mAdapter = PhotoAdapter(photos as ArrayList<Photo>)
                photos_recycler_view.adapter = mAdapter

        }else{

            val currentPosition = (photos_recycler_view.layoutManager as GridLayoutManager).findLastVisibleItemPosition()

            photos.addAll(photosList)
            mAdapter.notifyDataSetChanged()
            photos_recycler_view.scrollToPosition(currentPosition)

        }
        photos_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                visibleItemCount = mLinearLayoutManager.childCount
                totalItemCount = mLinearLayoutManager.itemCount
                pastVisibleItemCount =(photos_recycler_view.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()

                if (dy > 0) {
                    if (isLoading && visibleItemCount + pastVisibleItemCount >= totalItemCount) {

                        progressBarForLoadMore.visibility = View.VISIBLE

                        isLoading = false
                        pageId++

                        fetchPhotosList(pageId)

                    }
                }
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        //swipe to refresh
        swipeToRefresh.setOnRefreshListener {

            photos.clear()

            fetchPhotosList()

            swipeToRefresh.isRefreshing = false

        }
    }
}