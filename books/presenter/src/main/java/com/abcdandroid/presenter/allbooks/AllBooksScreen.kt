package com.abcdandroid.presenter.allbooks

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abcdandroid.presenter.allbooks.Events.OnRefreshDataClick
import com.abcdandroid.presenter.allbooks.Events.OnToggleFavoriteClick

@Composable
fun AllBooksScreen(viewModel: AllBooksViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = 1, block = {
        viewModel.getFromSSOT()
        viewModel.getFavorites()
    })

    val context: Context = LocalContext.current

    val state by derivedStateOf {
        viewModel.state
    }


    LaunchedEffect(key1 = state.refreshed, block = {
        if (state.refreshed)
            Toast.makeText(context, "data refreshed", Toast.LENGTH_LONG).show()

    })

    LazyColumn {
        val list = state.data ?: listOf()
        items(list.size) {
            Row {
                val uiBook = list[it]
                Text(text = uiBook.name.orEmpty())
                Checkbox(
                    checked = uiBook.isFavorite,
                    onCheckedChange = { isChecked ->
                        viewModel.onEvent(
                            OnToggleFavoriteClick(
                                uiBook = uiBook,
                                isSelected = isChecked
                            )
                        )
                    })
            }
        }
    }


    Box {


        if (viewModel.state.isLoading)
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

        if (viewModel.state.error.isNullOrBlank().not())
            Text(
                text = viewModel.state.error!!,
                modifier = Modifier
                    .align(Alignment.Center),
                fontSize = 30.sp,
                color = Color.Red
            )

        Button(
            onClick = { viewModel.onEvent(OnRefreshDataClick) },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "refresh")
        }

    }
}