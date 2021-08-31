package com.example.firstcomposeapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstcomposeapp.TestViewModel
import com.example.firstcomposeapp.User
import kotlinx.coroutines.launch

@Composable
fun TestScreen() {
    val vm: TestViewModel = viewModel()
    Surface(
        color = MaterialTheme.colors.background,
        elevation = 10.dp,
        modifier = Modifier.padding(10.dp)
    ) {
        val users by remember { vm.users }
        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            vm.getUsers()
        }
        UserList(group = users.groupBy { it.name.first() }, loading = vm.loading.value) {
            scope.launch { vm.getUsers() }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserList(group: Map<Char, List<User>>, loading: Boolean, onLoadMoreClicked: () -> Unit) {
    val vm: TestViewModel = viewModel()
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        group.forEach { gr ->
            stickyHeader {
                Text(
                    text = gr.key.toString(), modifier =
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(10.dp),
                    color = Color.Magenta
                )
            }
            items(gr.value) { user ->
                UserItem(user = user)
            }
        }

        if (loading)
            item {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Center)
                    )
                }
            }

        if (!loading)
            item {
                Button(
                    onClick = { onLoadMoreClicked.invoke() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    val page by remember { vm.page }
                    Text(text = "get page ${page + 1}")
                }
            }

    }
}

@Preview(showBackground = true)
@Composable
fun UserItem(
    user: User = User()
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .border(
                    border = BorderStroke(width = 1.dp, color = Color.Cyan),
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "name: ${user.name}")
                Text(text = "age: ${user.age}")
            }
        }
    }
}