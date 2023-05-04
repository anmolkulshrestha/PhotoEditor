package com.photoeditor.filters.collagemaker

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
import com.photoeditor.filters.collagemaker.feature_cropimages.demo.ImageCropDemo
// import com.photoeditor.filters.collagemaker.feature_pickimages.presentation.pickimagesscreen.PickImagesScreen
import com.photoeditor.filters.collagemaker.ui.theme.PhotoEditorCollageMakerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoEditorCollageMakerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    // Greeting("Android")
                    //PickImagesScreen()
                    ImageCropDemo()
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
    PhotoEditorCollageMakerTheme {
        Greeting("Android")
    }
}
