package com.example.proyecto02_danp

import android.view.View

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}