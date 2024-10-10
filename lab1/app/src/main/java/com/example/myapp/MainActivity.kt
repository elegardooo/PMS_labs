package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.ui.theme.MyAppTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    myFirstBoxesWithText();
                }
            }
        }
    }
}

@Composable
fun myFirstBoxesWithText() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier
                .fillMaxSize(0.5f)
                .background(Color(0xFFEADDFF))
                .align(Alignment.TopStart)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = "Text composable",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 19.sp,
                modifier = Modifier.padding(bottom = 16.dp))
            Text(
                text = "Display text and follows the recommended Material Design guidelines.",
                textAlign = TextAlign.Justify,
                fontSize = 19.sp)
        }

        Column(modifier = Modifier
            .fillMaxSize(0.5f)
            .background(Color(0xFFD0BCFF))
            .align(Alignment.TopEnd)
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = "Image composable",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                modifier = Modifier.padding(bottom = 16.dp))
            Text(
                text = "Create a composable that lays out and draws a given Painter class object.",
                textAlign = TextAlign.Justify,
                fontSize = 19.sp)
        }

        Column(modifier = Modifier
            .fillMaxSize(0.5f)
            .background(Color(0xFFB69DF8))
            .align(Alignment.BottomStart)
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = "Row composable",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                modifier = Modifier.padding(bottom = 16.dp))
            Text(
                text = "A layout composable that playces its children in a horizontal sequence.",
                textAlign = TextAlign.Justify,
                fontSize = 19.sp)
        }

        Column(modifier = Modifier
            .fillMaxSize(0.5f)
            .background(Color(0xFFF6EDFF))
            .align(Alignment.BottomEnd)
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = "Column composable",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                modifier = Modifier.padding(bottom = 16.dp))
            Text(
                text = "A lauout composable that playces its children in a vertical sequence.",
                textAlign = TextAlign.Justify,
                fontSize = 19.sp)
        }
    }
}

@Composable
fun greetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.Center, modifier = modifier.padding(8.dp)) {
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center,
        )
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier.padding(16.dp).align(alignment = Alignment.End)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun birthdayCardPreview() {
    MyAppTheme {
        myFirstBoxesWithText()
    }
}