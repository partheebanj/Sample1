package com.ivy.sampleproject.ui.feed_activity.di

import com.ivy.sampleproject.ui.feed_activity.FeedActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FeedActivityModule::class])
interface FeedActivityComponent {
    fun inject(activity: FeedActivity)
}