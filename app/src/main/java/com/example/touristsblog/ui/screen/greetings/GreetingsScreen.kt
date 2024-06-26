package com.example.touristsblog.ui.screen.greetings

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.touristsblog.R
import com.example.touristsblog.ui.theme.TouristsBlogTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GreetingsScreen(
    greetingsViewModel: GreetingsViewModel,
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Image(painter = painterResource(id = R.drawable.bg_img), contentDescription = "")
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .padding(top = 44.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Делитесь путешествиями",
                    modifier = Modifier.align(CenterHorizontally),
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "И храните впечатления!",
                    modifier = Modifier,
                    style = MaterialTheme.typography.h2,
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
                Button(
                    onClick = { greetingsViewModel.onClickLogin() },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = "Авторизоваться",
                        style = MaterialTheme.typography.button,
                    )
                }
                Button(
                    onClick = { greetingsViewModel.onClickSignUp() },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(size = 16.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
                ) {
                    Text(
                        text = "Зарегистрироваться",
                        style = MaterialTheme.typography.button,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    TouristsBlogTheme {
        TextField(value = "", onValueChange = {})
    }
}