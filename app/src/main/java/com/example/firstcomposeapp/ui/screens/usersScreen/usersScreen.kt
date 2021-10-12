package com.example.firstcomposeapp.ui.screens.usersScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.sharp.AddCircle
import androidx.compose.material.icons.sharp.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstcomposeapp.base.StateComposable
import com.example.firstcomposeapp.ui.screens.emptyScreen.EmptyDataScreen
import com.example.firstcomposeapp.ui.screens.usersScreen.models.User
import com.example.firstcomposeapp.utils.dataOrEmpty
import com.example.firstcomposeapp.utils.produceUiState
import com.example.firstcomposeapp.utils.showToast


@Composable
fun UsersScreen(vm: UsersViewModel) {
    Column {
        UsersList(vm)
    }
}


@Preview
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String? = "Screen Title",
    onAdd: () -> Unit = {},
    onBack: () -> Unit = {},
    onRefresh: () -> Unit = {},
) {
    Surface(modifier = modifier) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                IconButton(onClick = { onBack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
                title?.let {
                    Text(text = it, style = TextStyle(fontWeight = FontWeight.Black))
                }
            }

            Row() {
                IconButton(onClick = { onAdd() }) {
                    Icon(imageVector = Icons.Sharp.AddCircle, contentDescription = null)
                }

                IconButton(onClick = { onAdd() }) {
                    Icon(imageVector = Icons.Sharp.Refresh, contentDescription = null)
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun UsersList(vm: UsersViewModel = viewModel()) {

    val (result, reCall, clearError) = produceUiState(producer = vm) {
        getUsersResult()
    }

    val isLoading = result.value.loading


    if (result.value.hasError) {
        showToast(result.value.error)
        clearError()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {

            AppBar(
                modifier = Modifier.background(color = Color.White),
                title = "Users Lists",
                onRefresh = {
                    reCall()
                },
            )


            dataOrEmpty(

                data = result.value.data,

                onData = { data ->
                    LazyColumn(state = rememberLazyListState()) {
                        items(data) {

                            UserItemDesign(it)

                        }
                    }
                },

                onEmpty = {
                    EmptyDataScreen()
                },
            )
        }


        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }

    }

}

@Composable
fun UserItemDesign(user: User) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color.White,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(width = 1.dp, color = Color.Cyan),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "name: ${user.name}")
            Text(text = "age: ${user.address}")
        }
    }
}




