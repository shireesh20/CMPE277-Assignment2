package com.example.assignment2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ImplicitIntentsUI(this)
                }
            }
        }
    }

    fun openWebPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    }

    fun closeApp() {
        finish()
    }
}

@Composable
fun ImplicitIntentsUI(activity: MainActivity) {
    // State for URL and phone number input
    var url by remember { mutableStateOf("https://") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = url,
            onValueChange = { url = it },
            label = { Text("URL") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { activity.openWebPage(url) }) {
            Text(text = "Launch")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { activity.dialPhoneNumber(phoneNumber) }) {
            Text(text = "Ring")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { activity.closeApp() }) {
            Text(text = "Close App")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Assignment2Theme {
        // Since we cannot pass the MainActivity instance in the preview,
        // We're just passing a placeholder to avoid compilation error.
        // The actual functionality won't work in preview anyway.
        ImplicitIntentsUI(activity = MainActivity())
    }
}
