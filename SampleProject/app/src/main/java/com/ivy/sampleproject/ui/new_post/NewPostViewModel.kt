package com.ivy.sampleproject.ui.new_post

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivy.sampleproject.bo.Post
import com.ivy.sampleproject.db.MyDatabaseHelper
import com.ivy.sampleproject.util.CoroutineUtil
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

class NewPostViewModel
@Inject constructor(): ViewModel() {

    var titleStr: MutableLiveData<String> = MutableLiveData()
    var dateStr: MutableLiveData<String> = MutableLiveData()
    var captionStr: MutableLiveData<String> = MutableLiveData()
    var imagePathStr: MutableLiveData<String> = MutableLiveData()

    var postError: MutableLiveData<Boolean> = MutableLiveData()
    var finishActivity: MutableLiveData<Boolean> = MutableLiveData()

    fun updatePost(context: Context) {
        if (!titleStr.value.isNullOrEmpty()
            && !dateStr.value.isNullOrEmpty()
            && !captionStr.value.isNullOrEmpty()) {
            CoroutineUtil.io {
                val databaseHelper = MyDatabaseHelper(context)
                val id = UUID.randomUUID().toString();
                val post = Post(id)
                post.title = titleStr.value ?: ""
                post.dateStr = dateStr.value ?: ""
                post.caption = captionStr.value ?: ""
                post.imagePath = "ImagePath"
                databaseHelper.insertData(post)
                CoroutineUtil.main {
                    finishActivity.postValue(true)
                }
            }
        } else {
            postError.value = true
        }
    }
}