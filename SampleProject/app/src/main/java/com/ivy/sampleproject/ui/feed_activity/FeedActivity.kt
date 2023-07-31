package com.ivy.sampleproject.ui.feed_activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivy.sampleproject.databinding.ActivityMainBinding
import com.ivy.sampleproject.databinding.InstaFeedItemBinding
import com.ivy.sampleproject.ui.feed_activity.adapter.FeedAdapter
import com.ivy.sampleproject.ui.new_post.NewPostActivity

class FeedActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var viewModel: FeedViewModel = FeedViewModel()
    private var adapter: FeedAdapter? = null

/*    @Inject
    lateinit var viewModel: FeedViewModel*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.rootView)

        binding.newPostBtn.setOnClickListener(this)
        viewModel.getAllPost(this, false)
        viewModel.postList.observe(this) {
            binding.recyclerViewFeed.layoutManager = LinearLayoutManager(this)
            adapter = FeedAdapter(it, InstaFeedItemBinding.inflate(layoutInflater))
            binding.recyclerViewFeed.adapter = adapter
        }

    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.newPostBtn -> navigateToNewPost()
        }
    }

    private fun navigateToNewPost() {
        val intent = Intent(this, NewPostActivity::class.java)
        startActivity(intent)
    }
}