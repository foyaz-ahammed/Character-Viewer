package com.sample.simpsonsviewer.utility

import android.content.res.Resources
import com.sample.simpsonsviewer.R

fun Int.getItemFoundDescription(resources: Resources): String {
    return if (this == 0) {
        resources.getString(R.string.no_item_found)
    } else {
        resources.getString(R.string.item_found_format, this)
    }
}