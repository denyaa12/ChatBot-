package com.innowise.chatbot.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.innowise.chatbot.R
import com.innowise.chatbot.ui.theme.AppTheme
import com.innowise.chatbot.ui.theme.ButtonGradientAngle
import com.innowise.chatbot.ui.theme.angledLinearGradient

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    @DrawableRes leadingIconRes: Int,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val dimens = AppTheme.dimens
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val gradient = remember { angledLinearGradient(ButtonGradientAngle) }
    val borderStroke = if (isFocused) {
        BorderStroke(
            width = dimens.dp1,
            brush = gradient,
        )
    } else {
        BorderStroke(
            width = dimens.dp1,
            color = colorScheme.outline,
        )
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        textStyle = typography.bodyMedium.copy(color = colorScheme.onBackground),
        cursorBrush = SolidColor(colorScheme.primary),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = dimens.dp52)
                    .border(
                        border = borderStroke,
                        shape = CircleShape,
                    )
                    .padding(horizontal = dimens.dp16),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(leadingIconRes),
                    contentDescription = null,
                    tint = colorScheme.onBackground,
                    modifier = Modifier.size(dimens.dp20),
                )

                Spacer(modifier = Modifier.width(dimens.dp12))

                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = typography.bodyMedium,
                            color = colorScheme.onSurfaceVariant,
                        )
                    }
                    innerTextField()
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun AppTextFieldPreview() {
    AppTheme {
        AppTextField(
            value = "sample@email.com",
            onValueChange = {},
            placeholder = stringResource(R.string.sign_in_email_placeholder),
            leadingIconRes = R.drawable.ic_mail,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppTextFieldEmptyPreview() {
    AppTheme {
        AppTextField(
            value = "",
            onValueChange = {},
            placeholder = stringResource(R.string.sign_in_email_placeholder),
            leadingIconRes = R.drawable.ic_mail,
        )
    }
}
