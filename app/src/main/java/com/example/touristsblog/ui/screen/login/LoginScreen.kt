package com.example.touristsblog.ui.screen.login

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.touristsblog.ui.theme.TouristsBlogTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel
) {
    val error by loginViewModel.errorState.collectAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 44.dp)) {
            Text(
                text = "Авторизация",
                modifier = Modifier,
                style = MaterialTheme.typography.h1,
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 60.dp, top = 24.dp)
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            var email by remember { mutableStateOf("") }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-mail") },
                shape = RoundedCornerShape(16.dp),
                colors = textFieldColors(backgroundColor = MaterialTheme.colors.background)
            )
            var password by remember { mutableStateOf("") }
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Пароль") },
                shape = RoundedCornerShape(16.dp),
                colors = textFieldColors(backgroundColor = MaterialTheme.colors.background)
            )
            Text(
                text = error,
                modifier = Modifier,
                style = MaterialTheme.typography.body1,
                color = Color.Red
            )
            Button(
                onClick = { loginViewModel.onClickLogin(email, password) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Авторизоваться")
            }

            Button(
                onClick = { loginViewModel.onClickSignUp() },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Нет аккаунта? Зарегистрироваться")
            }
            Text(
                text = "Забыли пароль? Восстановить",
                modifier = Modifier.clickable {

                },
                style = MaterialTheme.typography.h2,
            )
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