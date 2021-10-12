package com.example.firstcomposeapp.utils

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun showToast(error: String?) {
    error?.let {
        Toast.makeText(LocalContext.current, error, Toast.LENGTH_LONG).show()
    }
}



@Composable
fun <DATA_TYPE> dataOrEmpty(
    data: DATA_TYPE?,
    onData: @Composable (DATA_TYPE) -> Unit,
    onEmpty: @Composable () -> Unit,
) {
    if (data != null)
        onData(data)
    else onEmpty()
}
