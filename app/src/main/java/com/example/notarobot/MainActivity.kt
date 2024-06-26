package com.example.notarobot

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notarobot.ui.theme.NotARobotTheme
import kotlinx.coroutines.delay

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
    // Use a LazyColumn() to display the images as a vertical list. The images are to be centered horizontally on the screen.
    val catImages = listOf("cat1","cat2","cat3")
    val dogImages = listOf("dog1","dog2","dog3","dog4","dog5","dog6")

    // The image list is comprised of all the dogs and one cat. The images are always displayed in random order when the app runs.
    // 6 dogs + 1 cat = 7 images in total
    val allImages = (dogImages + catImages.random()).shuffled()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // Add the text "Find the cat!" at the top
        item {
            Text(
                text = "Find the cat!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )
        }
        // Then add all the 7 images
        items(items = allImages) {
                imageName -> DogCatItem(imageName = imageName)
        }
    }
}

@Composable
fun DogCatItem(imageName: String) {
    val context = LocalContext.current
    val resourceId = painterResource(id = getDrawableResourceId(context, imageName))
    val selectedImage = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = resourceId,
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .clickable {
                    // Handle the click event
                    // Toast.makeText(context, "xxx", Toast.LENGTH_LONG).show()
                    // onImageClicked(imageName, selectedImage)
                    if (imageName.startsWith("cat")) {
                        selectedImage.value = "cat"
                        Toast.makeText(context, "Hurray, you are not a robot!", Toast.LENGTH_LONG).show()
                    } else if (imageName.startsWith("dog")) {
                        selectedImage.value = "dog"
                        Toast.makeText(context, "Opps, that’s not a cat!", Toast.LENGTH_LONG).show()
                    }
                },
            contentScale = ContentScale.Crop
        )
    }
}

fun getDrawableResourceId(context: Context, imageName: String): Int {
    return context.resources.getIdentifier(imageName, "drawable", context.packageName)
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotARobotTheme {
        DogCatList()
    }
}