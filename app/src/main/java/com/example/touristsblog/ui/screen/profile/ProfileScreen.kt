package com.example.touristsblog.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import com.example.touristsblog.R
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
) {
    //val username by viewModel.usernameState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(true) {
        //MapKitFactory.initialize(context)
        MapKitFactory.getInstance().onStart()
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 44.dp)) {
            Text(
                text = "Имя Фамилия",
                modifier = Modifier,
                style = MaterialTheme.typography.h1,
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 60.dp)
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AsyncImage(
                model = "https://w7.pngwing.com/pngs/846/897/png-transparent-user-male-avatar-account-profile-web-ui-color-icon.png",
                contentDescription = "",
                modifier = Modifier.size(56.dp),
            )
            Row(
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.Black), RectangleShape)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                ) {
                    Text(
                        "0", textAlign = TextAlign.Center,
                        modifier = Modifier.align(CenterHorizontally)
                    )
                    Text(
                        "Постов", textAlign = TextAlign.Center,
                        modifier = Modifier.align(CenterHorizontally)
                    )
                }
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier
                        .width(1.dp)
                        .height(80.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "",
                        modifier = Modifier.align(CenterHorizontally),
                    )
                    Text("Настройки профиля", textAlign = TextAlign.Center)
                }

                Divider(
                    thickness = 1.dp,
                    modifier = Modifier
                        .width(1.dp)
                        .height(80.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier.align(CenterHorizontally),
                    )
                    Text("Выйти из профиля", textAlign = TextAlign.Center)
                }
            }

            val context = LocalContext.current

            val point = Point(55.751574, 37.573856) // Замените на вашу координату
            val point2 = Point(55.751889, 37.573967) // Замените на вашу координату
            val icon = ImageProvider.fromResource(context, R.drawable.baseline_location_on_24)


            AndroidView(
                modifier = Modifier.height(256.dp),
                factory = { context ->
                    // Создание MapView
                    MapView(context).apply {
                        mapWindow.map.move(
                            CameraPosition(Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f)
                        )
                        val mapObjects = mapWindow.map.mapObjects
                        mapObjects.addPlacemark(point)
                        mapObjects.addPlacemark(point2)
                        onStart()
                        // Вы можете обращаться к карте здесь для настройки
                    }
                },
                update = { mapView ->
                    // Обновление view, когда состояние Composable меняется.
                    // Например, изменение центра карты или масштаба.
                }
            )

        }
    }
}