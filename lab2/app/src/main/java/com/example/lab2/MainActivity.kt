package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab2.ui.theme.Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Lab2Layout()
                }
            }
        }
    }
}

@Composable
fun Lab2Layout() {
    val images = arrayOf(
        R.drawable.bridge,
        R.drawable.dice,
        R.drawable.field,
        R.drawable.house,
        R.drawable.lake
    )
    val TestStringValArray = arrayOf("Bridge image", "Dice image", "Field image", "House image", "Lake image")
    var i by remember { mutableStateOf(0) }
    Column (
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxHeight()
            .padding(horizontal = 0.dp, vertical = 0.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(width = 400.dp, height = 500.dp)
                .border(BorderStroke(1.dp, Color.Black), shape = MaterialTheme.shapes.medium)
                .background(Color.LightGray, shape = MaterialTheme.shapes.medium)
                .clip(shape = MaterialTheme.shapes.medium),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = images[i]),
                contentDescription = "Image",
                modifier = Modifier
                    .size(350.dp),
                alignment = Alignment.Center
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(width = 300.dp, height = 100.dp)
                .background(Color(0xFFB2FCFF))
                .padding(bottom = 0.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = TestStringValArray[i],
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if (i > 0) {
                        i--
                    }
                },
                shape = ButtonDefaults.shape,
                modifier = Modifier.width(150.dp),
                enabled = i != 0
            ) {
                Text(text = "Previous")
            }
            Button(
                onClick = {
                    if (i < 4) {
                        i++
                    }
                },
                shape = ButtonDefaults.shape,
                modifier = Modifier.width(150.dp),
                enabled = i != 4
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab2Theme {
        Lab2Layout()
    }
}