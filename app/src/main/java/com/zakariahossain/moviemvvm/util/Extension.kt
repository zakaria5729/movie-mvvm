package com.zakariahossain.moviemvvm.util

import android.content.Context
import android.widget.Toast

fun Context.toastShort(message: String): Unit =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toastLong(message: String): Unit =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()