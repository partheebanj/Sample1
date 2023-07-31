package com.ivy.sampleproject.ui.new_post

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import com.ivy.sampleproject.R
import com.ivy.sampleproject.databinding.ActivityNewPostBinding
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NewPostActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityNewPostBinding

    /*@Inject
    lateinit var viewModel: NewPostViewModel*/

    private var viewModel = NewPostViewModel()
    private val calendar = Calendar.getInstance()
    private var imagePath = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.rootView)
        //binding.viewModel = viewModel

        binding.postBtn.setOnClickListener(this)
        initObservers()

        binding.selectDateBtn.setOnDateChangeListener { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            val selectedDate = calendar.time
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val formattedDate = sdf.format(selectedDate)
            binding.selectedDateTv.text = formattedDate
            viewModel.dateStr.postValue(formattedDate)
        }

        binding.titleET.doOnTextChanged { text, _, _, _ ->
            viewModel.titleStr.postValue(text.toString())
        }

        binding.captionET.doOnTextChanged { text, _, _, _ ->
            viewModel.captionStr.postValue(text.toString())
        }
    }

    override fun onClick(view: View) {
        when (view) {
            binding.postBtn -> viewModel.updatePost(this)
        }
    }

    private fun initObservers() {
        viewModel.postError.observe(this, Observer {
            Toast.makeText(this, "Please update all fields", Toast.LENGTH_LONG).show()
        })

        viewModel.finishActivity.observe(this) {
            if (it)
                finish()
        }
    }
}