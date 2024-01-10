package com.example.twacha

import App
import PhotoSelector.ImagePickerFactory
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.ktor.client.HttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(ImagePickerFactory().createPicker())
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(ImagePickerFactory().createPicker())
}