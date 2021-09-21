package com.example.firstcomposeapp.ui.screens.paging

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.firstcomposeapp.R
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


@Composable
fun EmployeeListScreen() {
    val vm: EmployeeViewModel = viewModel()
    val listState = rememberLazyListState()
    //listState.firstVisibleItemIndex
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {

        UserInfoList(listState, userList = vm.user, LocalContext.current)

        val scope = rememberCoroutineScope()
        Button(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .rotate(90f),
            onClick = {
                scope.launch {
                    // Animate scroll to the first item
                    listState.animateScrollToItem(index = 0)
                }
            }) {
            Image(painter = painterResource(R.drawable.abc_vector_test), contentDescription = "")
        }
    }
}

@Composable
fun UserInfoList(listState: LazyListState, userList: Flow<PagingData<Employee>>, context: Context) {
    val userListItems: LazyPagingItems<Employee> = userList.collectAsLazyPagingItems()

    LazyColumn(state = listState) {
        items(userListItems) { item ->
            item?.let {
                EmployeeItem(empData = it, onClick = {
                    Toast.makeText(context, item.id.toString(), Toast.LENGTH_SHORT).show()
                })
            }
        }
        userListItems.apply {
            when {
                loadState.refresh is LoadState.Loading ->
                    item { Text("Loading", color = Color.Blue, fontSize = 30.sp) }
                loadState.append is LoadState.Loading ->
                    item { Text("Loading", color = Color.Blue, fontSize = 30.sp) }

                loadState.append is LoadState.Error ->
                    item { Text("Error", color = Color.Red, fontSize = 30.sp) }
                loadState.refresh is LoadState.Error -> item {
                    Text("Error",
                        color = Color.Red,
                        fontSize = 30.sp)
                }
            }
        }
    }
}

@Composable
fun EmployeeItem(empData: Employee, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(bottom = 5.dp, top = 5.dp,
                start = 5.dp, end = 5.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(15.dp),
        elevation = 12.dp
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.surface)
        ) {
            Surface(
                modifier = Modifier.size(130.dp),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colors.surface.copy(
                    alpha = 0.2f)
            ) {
                val image = rememberCoilPainter(
                    request = empData.avatar,
                    fadeIn = true)
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .clip(shape = RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = empData.first_name,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 22.sp),
                    color = Color.Black
                )

                Text(
                    text = empData.email,
                    style = typography.body2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 25.dp)
                )
            }
        }
    }
}