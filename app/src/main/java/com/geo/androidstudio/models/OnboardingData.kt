package com.geo.androidstudio.models

import com.geo.androidstudio.R


data class OnboardingItem(
    val title: String,
    val description: String,
    val imageResId: Int
)
val onboardingItems = listOf(
    OnboardingItem(
        title = "Welcome",
        description = "Explore the Products with us",
        imageResId = R.drawable.tire
    ),
    OnboardingItem(
        title = "",
        description = ""
        , imageResId = R.drawable.tech
    )
)