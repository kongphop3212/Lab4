package com.example.student

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.student.ui.theme.StudentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentTheme {
                StudentApp()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentApp(){
    var data = remember { mutableStateListOf<String>() }
    var isShowDialog = remember {
        mutableStateOf(false)
    }
    var newStudentName by remember {
        mutableStateOf("")
    }
    if (isShowDialog.value) {
        InputDialog(
                itemName = newStudentName,
                onCancel = {
                    isShowDialog.value = false
                },
                onAddButtonClick = { newItem ->
                    data.add(newItem)
                    isShowDialog.value = false
                }
        )
    }
    Scaffold(
            topBar = {
                TopAppBar(title = { Text("Student App")},
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = Color.DarkGray,
                                titleContentColor = Color.White
                        )
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { isShowDialog.value = true },
                        containerColor = Color.DarkGray,
                        contentColor = Color.White) {
                    Icon(Icons.Filled.Add, "Add New Student")
                }
            }
    ){
        LazyColumn(
                modifier = Modifier.padding(it)){
            items(data){
                item->Text(item)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputDialog(
        itemName: String,
        onCancel: () -> Unit,
        onAddButtonClick: (String) -> Unit
) {
    Dialog(
            onDismissRequest = onCancel,
    ) {
        var textValue by remember {
            mutableStateOf(itemName)
        }
        Card(
                shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                    modifier = Modifier.padding(10.dp)
            ) {
                TextField(
                        value = textValue,
                        onValueChange = { textValue = it },
                        label = { Text("Student Name") }
                )
                TextButton(onClick = { onAddButtonClick(textValue) }) {
                    Text("Add")
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StudentTheme {
        StudentApp()
    }
}