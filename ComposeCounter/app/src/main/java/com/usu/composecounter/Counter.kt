package com.usu.composecounter

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usu.composecounter.ui.theme.ComposeCounterTheme

@Composable
fun Counter() {
    var count by remember { mutableStateOf(0) }
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
           Text(
               "$count",
               style = MaterialTheme.typography.h1
           )
        }
        Row {
            Button(onClick = { count -- }) {
                Text("Decrement")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { count ++ }) {
                Text("Increment")
            }
        }
    }
}

@Preview
@Composable
fun CounterPreview() {
    ComposeCounterTheme {
        Counter()
    }
}