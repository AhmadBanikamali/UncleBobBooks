package com.android.presenter

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ScarletTest(viewModel: ScarletViewModel = hiltViewModel()) {

    Button(onClick = viewModel::testSocket) {

        Text(text = "Aaa")
    }
}