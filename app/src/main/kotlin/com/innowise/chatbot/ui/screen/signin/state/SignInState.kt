package com.innowise.chatbot.ui.screen.signin.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.innowise.chatbot.R

data class SignInState(
    val email: String = "",
    val isNextEnabled: Boolean = false,
    @StringRes val toolbarTitleRes: Int = R.string.sign_in_toolbar_title,
    @StringRes val headlineRes: Int = R.string.sign_in_headline,
    @StringRes val descriptionRes: Int = R.string.sign_in_description,
    @StringRes val emailPlaceholderRes: Int = R.string.sign_in_email_placeholder,
    @DrawableRes val emailIconRes: Int = R.drawable.ic_mail,
    @StringRes val nextButtonRes: Int = R.string.sign_in_next,
    @StringRes val termsRes: Int = R.string.sign_in_terms,
    @StringRes val termsHighlightFirstRes: Int = R.string.sign_in_terms_highlight_first,
    @StringRes val termsHighlightSecondRes: Int = R.string.sign_in_terms_highlight_second,
)
