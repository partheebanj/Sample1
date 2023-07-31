package com.ivy.sampleproject.ui.feed_activity.adapter

import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ivy.sampleproject.bo.Post
import com.ivy.sampleproject.databinding.InstaFeedItemBinding
import com.squareup.picasso.Picasso
import java.io.File

class FeedAdapter(
    private val postList: ArrayList<Post>,
    private val listItemBinding: InstaFeedItemBinding
) : RecyclerView.Adapter<FeedAdapter.FeedAdapterBaseItem>() {

    class FeedAdapterBaseItem(v: InstaFeedItemBinding) : RecyclerView.ViewHolder(v.rootView) {
        var titleTextView: TextView
        var imageView: ImageView
        var imageLikeBtn: ImageButton
        var caption: TextView
        var date: TextView

        init {
            titleTextView = v.titleTv
            imageView = v.imageIV
            imageLikeBtn = v.imageViewLikeButton
            caption = v.captionTv
            date = v.dateTv
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapterBaseItem {
        return FeedAdapterBaseItem(listItemBinding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: FeedAdapterBaseItem, position: Int) {
        holder.caption.text = postList[position].caption
        holder.titleTextView.text = postList[position].title
        holder.date.text = postList[position].dateStr

        val imageFile = File(postList[position].imagePath)
        Picasso.get().load(imageFile).into(holder.imageView)

        if (postList[position].isFavorite == "1") {
            Picasso.get().load(imageFile).into(holder.imageLikeBtn)
        } else {
            Picasso.get().load(imageFile).into(holder.imageLikeBtn)
        }
    }
}