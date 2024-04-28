package com.example.touristsblog.ui.screen.viewpost

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.touristsblog.ui.screen.myposts.createpost.ItemType
import com.example.touristsblog.ui.screen.myposts.createpost.PostItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ViewPostScreen(
    viewModel: ViewPostViewModel,
) {
    val showDialog = remember { mutableStateOf(false) }
    val state = viewModel.screenState.collectAsState()
    val openState = viewModel.isOpen.collectAsState()
    if (showDialog.value) {
        MyAlertDialog(showDialog = showDialog, viewModel)
    }
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(MaterialTheme.colors.primary)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .padding(vertical = 10.dp)
                        .clickable {
                            viewModel.goBack()
                            navController.navigateUp()
                        }
                )
                Text(
                    text = "Просмотр поста",
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .padding(vertical = 10.dp)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog.value = true
                },
                modifier = Modifier.padding(bottom = 56.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "edit button")
            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            state.value.forEach { item ->
                when (item.itemType) {
                    ItemType.GeoItem -> TextContent(item.value)
                    ItemType.ImageItem -> ImagePicker(item.value)
                    ItemType.TextItem -> TextContent(item.value)
                    ItemType.TitleItem -> Title(item.value)
                }
            }
            Button(
                onClick = {
                    viewModel.changePostVisibility()
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .height(50.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = if (!openState.value) {
                        "Сделать недоступным для всех"
                    } else {
                        "Сделать доступным для всех"
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
            Button(
                onClick = {
                    viewModel.deletePost()
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .height(50.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Удалить",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun Title(
    title: String,
) {
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun TextContent(
    content: String,
) {
    Text(
        text = content,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun GeoContent(
    viewModel: ViewPostViewModel,
    itemPosition: Int,
) {
    var content by remember { mutableStateOf("") }
    Text(
        text = content,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
fun ImagePicker(
    imageUrl: String,
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun MyAlertDialog(
    showDialog: MutableState<Boolean>,
    viewModel: ViewPostViewModel,
) {
    // Создаем список пунктов
    val items = listOf("Заголовок", "Текст", "Изображение", "Геометка")

    AlertDialog(
        backgroundColor = MaterialTheme.colors.background,
        onDismissRequest = {
            // Действие при попытке отмены (например, нажатие на фоне)
            showDialog.value = false
        },
        title = {
            Text(text = "Выберите поле для добавления:", modifier = Modifier.padding(bottom = 8.dp))
        },
        text = {
            Column {
            }
        },
        confirmButton = {},
        dismissButton = {
            Button(
                onClick = {
                    // Действие при отклонении, здесь также можно закрыть диалог
                    showDialog.value = false
                }) {
                Text("Отмена")
            }
        }
    )
}