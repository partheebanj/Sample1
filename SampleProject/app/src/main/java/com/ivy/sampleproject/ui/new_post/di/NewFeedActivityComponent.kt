package com.ivy.sampleproject.ui.new_post.di

import com.ivy.sampleproject.ui.new_post.NewPostActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NewFeedActivityModule::class])
interface NewFeedActivityComponent {
    fun inject(activity: NewPostActivity)
}