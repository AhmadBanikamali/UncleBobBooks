package com.abcdandroid.unclebobbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.abcdandroid.presenter.allbooks.AllBooksScreen
import com.abcdandroid.unclebobbooks.ui.theme.UncleBobBooksTheme
import com.android.presenter.ScarletTest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UncleBobBooksTheme {
                // A surface container using the 'background' color from the theme
                
                // https://medium.com/swlh/clean-architecture-in-android-a-beginner-approach-be0ce00d806b
                // https://www.googleapis.com/books/v1/volumes?q=Robert%20C.%20Martin
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    AllBooksScreen()
                    ScarletTest()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UncleBobBooksTheme {
        Greeting("Android")
    }
}