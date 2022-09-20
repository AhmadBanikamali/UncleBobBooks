package com.android.presenter

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun ScarletTest(viewModel: ScarletViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = 1, block = {
        viewModel.receiveData().receiveAsFlow()
            .catch {
                println(it)
            }
            .collect {
                println(it)
            }
    })

    Button(onClick = viewModel::testSocket) {

        Text(text = "Aaa")
    }
}