package com.usu.composecounter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

data class Message(val author: String, val message: String)

@Composable
fun MessageList() {
    val messages = ArrayList<Message>();

    for (i in 0..99) {
        messages.add(Message("Joseph", "I am a message"))
    }

    LazyColumn {
        items(messages) { message  ->
            Column {
                Text(message.author)
                Text(message.message)
            }
        }
    }
}