package com.ivy.sampleproject.ui.feed_activity.di

import com.ivy.sampleproject.ui.feed_activity.FeedViewModel
import dagger.Module
import dagger.Provides

@Module
class FeedActivityModule {

    @Provides
    fun providesFeedActivityViewModel(): FeedViewModel {
        return FeedViewModel()
    }
}