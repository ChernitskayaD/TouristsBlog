package com.example.touristsblog.ui.screen.myposts.createpost

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreatePostScreen(
    viewModel: CreatePostViewModel
) {
    val showDialog = remember { mutableStateOf(false) }
    val state = viewModel.screenState.collectAsState()

    if (showDialog.value) {
        MyAlertDialog(showDialog = showDialog, viewModel)
    }
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
                            viewModel.navBack()
                        }
                )
                Text(
                    text = "Создание нового поста",
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
                Icon(imageVector = Icons.Default.Add, contentDescription = "add button")
            }
        }
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            state.value.forEach { item ->
                when (item.itemType) {
                    ItemType.TextItem -> item { TextContent(viewModel, item) }
                    ItemType.ImageItem -> item { ImagePicker(viewModel, item) }
                    ItemType.GeoItem -> item { GeoContent(viewModel, item) }
                    ItemType.TitleItem -> item { Title(viewModel, item) }
                }
            }
            item {
                val context = LocalContext.current
                Button(
                    onClick = {
                        viewModel.savePost(context.filesDir)
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .height(50.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Сохранить",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun Title(
    viewModel: CreatePostViewModel,
    item: PostItem,
) {
    var title by remember { mutableStateOf(item.value) }

    TextField(
        value = title,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                if (!it.isFocused) {
                    viewModel.updateItemValue(item.itemPosition, title)
                }
            },
        onValueChange = {
            title = it
        },
        label = { Text("") },
        shape = RoundedCornerShape(16.dp),
        colors = ExposedDropdownMenuDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        singleLine = true,
        trailingIcon = {
            if (item.itemPosition > 3) {
                Icon(Icons.Default.Delete, contentDescription = "", modifier = Modifier.clickable { viewModel.removeItem(item.itemPosition) })
            }
        }
    )
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun TextContent(
    viewModel: CreatePostViewModel,
    item: PostItem,
) {
    var content by remember { mutableStateOf(item.value) }
    TextField(
        value = content,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                if (!it.isFocused) {
                    viewModel.updateItemValue(item.itemPosition, content)
                }
            },
        onValueChange = {
            if (it.length <= 255) {
                content = it
            }
        },
        label = { Text("Текст") },
        shape = RoundedCornerShape(16.dp),
        colors = ExposedDropdownMenuDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Transparent,
        ), trailingIcon = {
            if (item.itemPosition > 3) {
                Icon(Icons.Default.Delete, contentDescription = "", modifier = Modifier.clickable { viewModel.removeItem(item.itemPosition) })
            }
        }
    )
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun GeoContent(
    viewModel: CreatePostViewModel,
    item: PostItem,
) {
    val geoSearchState by viewModel.addressState.collectAsState()

    var content by remember { mutableStateOf(item.value) }
    var focus by remember { mutableStateOf(false) }
    Row {
        Icon(Icons.Default.Place, contentDescription = "", modifier = Modifier.align(CenterVertically))
        TextField(
            value = content,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    focus = it.isFocused
                    if (!it.isFocused) {
                        viewModel.updateItemValue(item.itemPosition, content)
                    }
                },
            onValueChange = {
                content = it
                if (it.length > 3) {
                    viewModel.searchAddress(it)
                }
            },
            label = { Text("Введите адрес") },
            shape = RoundedCornerShape(16.dp),
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.background,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Transparent,
            ), trailingIcon = {
                if (item.itemPosition > 3) {
                    Icon(Icons.Default.Delete, contentDescription = "", modifier = Modifier.clickable { viewModel.removeItem(item.itemPosition) })
                }
            }
        )
    }
    if (content.isNotEmpty() && focus) {
        geoSearchState.forEach { suggestion ->
            ClickableText(
                text = AnnotatedString(suggestion),
                onClick = {
                    content = suggestion
                },
                style = TextStyle.Default.copy(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            Divider(color = Color.LightGray)
        }
    }
}

@Composable
fun ImagePicker(
    viewModel: CreatePostViewModel,
    item: PostItem,
) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    if (item.value.isNotBlank()) {
        val file = File(context.filesDir, item.value)
        imageUri = Uri.fromFile(file)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            imageUri = uri
            if (uri != null) {
                viewModel.updateItemValue(item.itemPosition, saveFileToLocalStorage(uri, context))
            }
        }
    )

    Row {
        if (imageUri == null) {
            Button(onClick = { launcher.launch("image/*") }) {
                Text(text = "Выберите изображение")
            }
        }

        imageUri?.let {
            AsyncImage(
                it,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }
        if (item.itemPosition > 3) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { viewModel.removeItem(item.itemPosition) }
            )
        }
    }
}

private fun saveFileToLocalStorage(uri: Uri, context: Context): String {
    val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
    var outputStream: OutputStream? = null
    var savedFilePath = ""

    try {
        val fileName = System.currentTimeMillis().toString() // Генерируем уникальное имя файла
        val file = File(context.filesDir, fileName)
        outputStream = FileOutputStream(file)
        savedFilePath = fileName

        inputStream?.copyTo(outputStream) ?: throw IOException("Не удалось сохранить файл")
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        inputStream?.close()
        outputStream?.close()
    }

    return savedFilePath
}

@Composable
fun MyAlertDialog(
    showDialog: MutableState<Boolean>,
    viewModel: CreatePostViewModel,
) {
    // Создаем список пунктов
    val items = listOf("Заголовок", "Текст", "Изображение")

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
                items.forEach { item ->
                    Button(
                        onClick = {
                            // Обработка выбора пункта
                            Log.d("Dialog", "$item выбран")
                            // Закрытие диалогового окна
                            val itemType = when (item) {
                                "Заголовок" -> ItemType.TitleItem
                                "Текст" -> ItemType.TextItem
                                "Изображение" -> ItemType.ImageItem
                                else -> ItemType.TextItem
                            }
                            val postItem = PostItem(
                                value = "",
                                itemPosition = viewModel.getNextItemPos(),
                                itemType = itemType
                            )
                            viewModel.addItem(postItem)
                            showDialog.value = false
                        },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .height(40.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
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