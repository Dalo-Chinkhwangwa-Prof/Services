package com.dynamicdevz.dynamiccoroutines.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dynamicdevz.dynamiccoroutines.model.Result
import com.dynamicdevz.dynamiccoroutines.network.JikanRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JikanViewModel : ViewModel() {

    private val mutableResponse = MutableLiveData<List<Result>>()

    val responseData: LiveData<List<Result>>
        get() = mutableResponse

    //in future use DI
    private val jikanRetrofit = JikanRetrofit()

    fun getSearchResults(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TAG_X", "Thread : ${Thread.currentThread().name}")
            try {
                val value = jikanRetrofit.jikanService.getMediaItemsAsync(query)
                mutableResponse.postValue(value.await().results)
                //someFuncition(def1.await(), def2.await())

            } catch (e: Exception) {
                //
            }
        }
    }


}