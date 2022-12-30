package jkcb.dev.labs.yape.test.utils

import android.view.View

fun View.handleVisibility(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}


