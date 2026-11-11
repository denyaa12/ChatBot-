package com.innowise.chatbot.ui.screen.auth.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.innowise.chatbot.R

data class AuthState(
    @StringRes val titleRes: Int = R.string.auth_title,
    @StringRes val subtitleRes: Int = R.string.auth_subtitle,
    @StringRes val signInButtonRes: Int = R.string.auth_sign_in_sign_up,
    @StringRes val googleButtonRes: Int = R.string.auth_continue_with_google,
    @DrawableRes val googleIconRes: Int = R.drawable.ic_google,
)
