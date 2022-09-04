package com.abcdandroid.presenter.allbooks

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AllBooksScreen(viewModel: AllBooksViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = 1, block = {
        viewModel.getBooksFromRemote()
    })

    Box {

        if(viewModel.state.refreshed) {
            val context: Context = LocalContext.current
            Toast.makeText(context,"data refreshed",Toast.LENGTH_LONG).show()
        }

        LazyColumn {
            val list = viewModel.state.data ?: listOf()
            items(list.size) {
                Text(text = list[it].name.orEmpty())
            }
        }

        if(viewModel.state.isLoading)
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))



        if (viewModel.state.hasError.isNullOrBlank().not())
            Text(
                text = viewModel.state.hasError!!,
                modifier = Modifier
                    .align(Alignment.Center),
                fontSize = 30.sp,
                color = Color.Red
            )

    }
}