package com.example.notarobot

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.notarobot.ui.theme.NotARobotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("Hello")
            NotARobotTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DogCatList()
                }
            }
        }
    }
}
@Composable
fun DogCatList(){
    Column {
        Text("Find the cat!")
        Image(
            painter = painterResource(R.drawable.cat1),
            contentDescription = "Contact profile picture",
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotARobotTheme {
        DogCatList()
    }
}