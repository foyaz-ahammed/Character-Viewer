package com.sample.simpsonsviewer.models

/**
 * Model class defining one character to show on UI
 */
data class Character(
    val title: String,
    val description: String,
    val htmlDescription: String,
    val url: String,
    val icon: String
)
