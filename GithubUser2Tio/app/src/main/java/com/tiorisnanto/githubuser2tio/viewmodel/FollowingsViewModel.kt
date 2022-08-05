package com.tiorisnanto.githubuser2tio.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tiorisnanto.githubuser2tio.model.ResponseUser
import com.tiorisnanto.githubuser2tio.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingsViewModel : ViewModel() {
    val listFollowing = MutableLiveData<ArrayList<ResponseUser>>()

    fun setListFollowing(username: String) {
        ApiConfig.service().getUserFollowing(username)
            .enqueue(object : Callback<ArrayList<ResponseUser>> {
                override fun onResponse(
                    call: Call<ArrayList<ResponseUser>>,
                    response: Response<ArrayList<ResponseUser>>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        listFollowing.postValue(responseBody)
                    }
                }

                override fun onFailure(call: Call<ArrayList<ResponseUser>>, t: Throwable) {
                    t.message?.let { Log.d("onFailure", it) }
                }

            })
    }

    fun getListFollowing(): LiveData<ArrayList<ResponseUser>> {
        return listFollowing
    }
}