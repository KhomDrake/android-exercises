package com.vinicius.androidexercises.ui

import androidx.annotation.StringRes
import com.vinicius.androidexercises.R
import com.vinicius.androidexercises.ui.widget.ErrorView

class UIException(
    @StringRes
    val errorTitle: Int = R.string.error_view_title_default,
    @StringRes
    val errorMessage: Int = R.string.error_view_body_default,
    val showButton: Boolean = true
) : Exception()

fun ErrorView.handleException(exception: Throwable) {
    when(exception) {
        is UIException -> {
            setTitle(exception.errorTitle)
            setBody(exception.errorMessage)
            showButton(exception.showButton)
        }
        else -> default()
    }
}