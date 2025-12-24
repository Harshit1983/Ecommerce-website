package com.example.ca2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ca2.ui.theme.Ca2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ca2Theme {
                appnav()
            }
        }
    }
}

@Composable
fun appnav() {
    val nav = rememberNavController()

    NavHost(
        navController = nav,
        startDestination = "home"
    ) {
        composable("home") { homepage(nav) }

        composable("detail/{item}/{quantity}") { backStack ->
            val item = backStack.arguments?.getString("item") ?: ""
            val quantity = backStack.arguments?.getString("quantity")?.toIntOrNull() ?: 0

            detailPage(nav, item, quantity)
        }
    }
}

@Composable
fun homepage(nav: NavController) {
    var item by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = item,
            onValueChange = { item = it },
            label = { Text("Enter item name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )

        TextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Enter Quantity") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val qty = quantity.toIntOrNull()

                if (qty != null && qty > 0) {
                    nav.navigate("detail/$item/$qty")
                } else {
                    Toast.makeText(
                        context,
                        "Enter quantity greater than zero",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        ) {
            Text("Send")
        }
    }
}

@Composable
fun detailPage(nav: NavController, item: String, quantity: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Received Item: $item")
        Text("Received Quantity: $quantity")
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ca2Theme {
        appnav()
    }
}






















