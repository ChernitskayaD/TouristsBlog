package com.example.touristsblog.ui.screen.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel
) {
    val error by viewModel.errorState.collectAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 44.dp)) {
            Text(
                text = "Регистрация",
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
            var name by remember { mutableStateOf("") }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Имя") },
                shape = RoundedCornerShape(16.dp),
                colors = textFieldColors(backgroundColor = MaterialTheme.colors.background)
            )
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
                colors = ExposedDropdownMenuDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.background)
            )
            var passwordConfirmation by remember { mutableStateOf("") }

            OutlinedTextField(
                value = passwordConfirmation,
                onValueChange = { passwordConfirmation = it },
                label = { Text("Подтверждение пароля") },
                shape = RoundedCornerShape(16.dp),
                colors = ExposedDropdownMenuDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.background)
            )
            Button(
                onClick = {
                    if (password == passwordConfirmation) {
                        viewModel.registration(name, email, password)
                    }
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Зарегистрироваться")
            }
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Уже есть аккаунт? Войти")
            }
        }
    }
}