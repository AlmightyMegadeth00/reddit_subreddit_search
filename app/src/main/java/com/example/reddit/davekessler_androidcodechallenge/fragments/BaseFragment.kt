package com.example.reddit.davekessler_androidcodechallenge.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import com.example.reddit.davekessler_androidcodechallenge.MainApplication
import com.example.reddit.davekessler_androidcodechallenge.dagger2.RestClient
import javax.inject.Inject

open class BaseFragment : Fragment() {
    @Inject
    lateinit var mRestClient: RestClient

    override fun onCreate(onSavedInstanceState: Bundle?) {
        super.onCreate(onSavedInstanceState)

        (activity.application as MainApplication).redditComponent.inject(this)
    }
}