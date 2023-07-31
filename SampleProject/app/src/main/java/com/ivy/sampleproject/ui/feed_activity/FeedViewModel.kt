package com.ivy.sampleproject.ui.feed_activity

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivy.sampleproject.bo.Post
import com.ivy.sampleproject.db.MyDatabaseHelper
import com.ivy.sampleproject.util.CoroutineUtil
import javax.inject.Inject

class FeedViewModel
@Inject constructor(): ViewModel() {

    var postList: MutableLiveData<ArrayList<Post>> = MutableLiveData()

    public fun getAllPost(context: Context, fetchFavorites: Boolean) {
        CoroutineUtil.io {
            //Fetch from db
            val dataBaseHelper = MyDatabaseHelper(context)
            val tempList = if (fetchFavorites) {
                dataBaseHelper.readAllFavorites()
            } else {
                dataBaseHelper.readAllData()
            }
            CoroutineUtil.main {
                postList.postValue(tempList)
            }
        }
    }
}