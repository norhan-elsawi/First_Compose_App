package com.example.firstcomposeapp.ui.screens.form

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstcomposeapp.R


@Composable
fun FormValidationScreen() {
    val vm: FormViewModel = viewModel()
    val emailState by vm.emailAddress.observeAsState()
    val phoneState by vm.phone.observeAsState()
    val isFormValid by vm.valid.observeAsState() // Added new state to observe

    MyCoolForm(
        emailText = emailState ?: "",
        onEmailChanged = { vm.emailAddress.postValue(it) },
        phoneText = phoneState ?: "",
        onPhoneChange = {
            Log.d("Value", it)
            if (it.length <= 9) vm.phone.postValue(it)
        },
        isFormValid = isFormValid ?: false // Passing in the value to the composable
    )
}

@Composable
fun MyCoolForm(
    emailText: String = "",
    onEmailChanged: (String) -> Unit = {},
    phoneText: String = "",
    onPhoneChange: (String) -> Unit = {},
    isFormValid: Boolean = false, // added state
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Title()
        Email(emailText, onEmailChanged)
        Phone(phoneText, onPhoneChange)
        Spacer(modifier = Modifier.size(4.dp))
        Button(enabled = isFormValid, onClick = {}) { // utilize new state here
            Text(text = "Save")
        }
        Spacer(modifier = Modifier.size(20.dp))
        AnnotatedClickableText()
    }
}

@Composable
fun Title() {
    val circularFontFamily = FontFamily(
        Font(R.font.alexbrush, FontWeight.Normal),
    )
    Text(
        //"please fill the form"
        getAnnotatedString(),
        fontSize = 25.sp,
        fontStyle = FontStyle.Italic,
        fontFamily = circularFontFamily,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
        modifier = Modifier.width(100.dp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

private fun getAnnotatedString() = buildAnnotatedString {
    withStyle(style = SpanStyle(color = Color.Blue)) {
        append("Please")
    }
    append(" fill ")

    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
        append("T")
    }
    append("he Form")
}

@Composable
fun AnnotatedClickableText() {
    val annotatedText = buildAnnotatedString {
        append("Must agree On ")

        // We attach this *URL* annotation to the following content
        // until `pop()` is called
        pushStringAnnotation(tag = "URL",
            annotation = "https://developer.android.com")
        withStyle(style = SpanStyle(color = Color.Blue,
            fontWeight = FontWeight.Bold)) {
            append("Terms and Conditions")
        }
        pop()
        // append("987398789378934")
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            // We check if there is an *URL* annotation attached to the text
            // at the clicked position
            annotatedText.getStringAnnotations(tag = "URL", start = offset,
                end = offset)
                .firstOrNull()?.let { annotation ->
                    // If yes, we log its value
                    Log.d("Clicked URL", annotation.item)
                }
        }
    )
}

@Composable
fun Email(
    emailText: String = "",
    onEmailChanged: (String) -> Unit = {},
) {
    //outlined
    TextField(
        value = emailText,
        //  label = { Text(text = "Email") },
        onValueChange = onEmailChanged,
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(
            color = Color.Blue,
            fontWeight = FontWeight.Bold)
    )
}

@Composable
fun Phone(
    phoneText: String = "",
    onPhoneChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        value = phoneText,
        label = { Text(text = "Phone") },
        onValueChange = onPhoneChange,
        leadingIcon = { LeadingIcon() },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(
            color = Color.Blue,
            fontWeight = FontWeight.Bold),
        //   visualTransformation = PhoneVisualTransformation(),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun LeadingIcon() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier
                .size(width = 60.dp, height = 40.dp),
            painter = painterResource(id = R.drawable.ic_nation),
            contentDescription = ""
        )
        Text(text = "+966")
    }
}
