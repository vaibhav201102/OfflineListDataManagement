package com.example.jsonparsing

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonparsing.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding ?= null
    private val binding get() = _binding!!
    private var listingAdapter : ListingAdapter ?= ListingAdapter()
    private var subCategory : List<ApiResponse.SubCategory> = mutableListOf()
    private val sharedPrefFile = "JSONPARSING"

    private var gson : Gson = Gson()
    private lateinit var prefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)

        initMyList()

        if (checkForInternet(this)){
            initGetCategoryList()
        }else{
            if (prefs.getString("SavedList","") != null){
                val type = object : TypeToken<List<ApiResponse.SubCategory>>() {}.type
                val retrivedlist : List<ApiResponse.SubCategory> = gson.fromJson(prefs.getString("SavedList",""),type)
                println(retrivedlist)
                listingAdapter?.updateist(retrivedlist)
            }
        }


    }

    private fun initGetCategoryList(){
        val call = ApiClient.apiService.getCategoryList()

        call.enqueue(object : Callback<ApiResponse>{
            @SuppressLint("CommitPrefEdits")
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    println(response.body())
                    response.body()?.appCenter?.forEach {
                        if (it.name.toString().equals("POPULAR APPS",true)){
                            val categorylist = it.subCategory.filter {
                                it.bannerImage.toString().isNotBlank() && it.bannerImage.toString().isNotEmpty()
                            }
                            subCategory = categorylist
//                            listingAdapter?.updateist(categorylist)

                            val savedData = gson.toJson(categorylist)
                            val editor = prefs.edit()
                            editor.putString("SavedList",savedData)
                            editor.apply()

                            val type = object : TypeToken<List<ApiResponse.SubCategory>>() {}.type
                            val retrivedlist : List<ApiResponse.SubCategory> = gson.fromJson(prefs.getString("SavedList",""),type)
                            println(retrivedlist)
                            listingAdapter?.updateist(retrivedlist)

                            println("subcategory")
                            println(categorylist)
                        }
                    }

                }else{
                    println(response)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                println(call)
                println(t)
            }

        })
    }

    private fun initMyList(){
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this@MainActivity)
        val recyclerViewItemList = binding.recyclerview
        recyclerViewItemList.layoutManager = layoutManager
        recyclerViewItemList.itemAnimator = DefaultItemAnimator()

        listingAdapter = ListingAdapter()

        listingAdapter?.onItemClick = {

            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${it.appLink}")))
            }
            catch (e : ActivityNotFoundException){
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=${it.appLink}")))
            }
        }

        recyclerViewItemList.adapter = listingAdapter

    }

    @SuppressLint("ObsoleteSdkInt", "MissingPermission")
    private fun checkForInternet(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }

}