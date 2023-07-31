package com.ivy.sampleproject.bo

data class Post(var id: String) {
    var title: String = ""
    var dateStr:String = ""
    var imagePath = ""
    var caption = ""
    var isFavorite: String = "0"
}