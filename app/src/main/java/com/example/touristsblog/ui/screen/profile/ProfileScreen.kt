package com.example.touristsblog.ui.screen.profile

import android.graphics.PointF
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.touristsblog.R
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
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
        MapKitFactory.getInstance().onStart()
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 44.dp)) {
            Text(
                text = "Иван Путешественник",
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
            Icon(
                Icons.Default.AccountCircle,
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
                        .align(Alignment.CenterVertically)
                        .clickable {
                            viewModel.onExitClick()
                        },
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
            var mapViewRembered by rememberMapView()

            val point = Point(55.751574, 37.573856)
            val point2 = Point(53.751889, 35.573967)

            val icon = ImageProvider.fromResource(context, R.drawable.location_png)
            val iconStyle = IconStyle().apply {
                anchor = PointF(0.5f, 1.0f)
                scale = 0.05f
            }
            AndroidView(
                modifier = Modifier.height(356.dp),
                factory = { context ->
                    if (mapViewRembered == null) MapView(context).also {
                        mapViewRembered = it
                    } else mapViewRembered!!
                },
                update = { mapView ->
                    mapView.apply {
                        // Дополнительные настройки MapView, если необходимо
                        mapView.map?.move(
                            CameraPosition(Point(55.751574, 37.573856), 4.0f, 0.0f, 0.0f),
                            Animation(Animation.Type.LINEAR, 2f), null
                        )
                        val mapObjects = mapWindow.map.mapObjects
                        mapObjects.addPlacemark(point, icon, iconStyle)
                        mapObjects.addPlacemark(point2, icon, iconStyle)
                    }
                    mapViewRembered = mapView

                    // Обновление view, когда состояние Composable меняется.
                    // Например, изменение центра карты или масштаба.
                }
            )

        }
    }
}

@Composable
fun rememberMapView(): MutableState<MapView?> {
    val context = LocalContext.current
    return remember { mutableStateOf(null) }
}