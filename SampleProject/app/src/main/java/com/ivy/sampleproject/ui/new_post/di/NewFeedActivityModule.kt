package com.ivy.sampleproject.ui.new_post.di

import com.ivy.sampleproject.ui.new_post.NewPostViewModel
import dagger.Module
import dagger.Provides

@Module
class NewFeedActivityModule {

    @Provides
    fun providesNewPostViewModel(): NewPostViewModel {
        return NewPostViewModel()
    }
}