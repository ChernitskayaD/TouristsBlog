package com.example.touristsblog.ui.screen.feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.touristsblog.ui.screen.login.LoginViewModel
import com.example.touristsblog.ui.screen.myposts.postslist.PostListViewModel
import com.example.touristsblog.ui.screen.myposts.postslist.PostPreview
import com.example.touristsblog.ui.theme.TouristsBlogTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeedScreen(
    viewModel: FeedViewModel
) {
    val screenState by viewModel.postsSate.collectAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 44.dp)) {
            Text(
                text = "Лента постов",
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
            Button(
                onClick = { viewModel.onClickCreateNewPost() },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Создать новый пост")
            }
            Button(
                onClick = { viewModel.onClickCreateNewPost() },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Открыть для всех свой пост")
            }

            screenState.forEach {
                FeedPostCard(it, viewModel)
            }
        }
    }
}

@Composable
fun FeedPostCard(item: FeedPostPreview, viewModel: FeedViewModel) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 5.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = item.postImage,
                contentDescription = "",
            )
            item.postTitle?.let {
                Text(
                    text = it,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            item.postGeo?.let {
                Text(
                    text = it,
                )
            }
            item.author?.let {
                Row {
                    Icon(
                        imageVector = Icons.Default.Person,
                        ""
                    )
                    Text(
                        text = it,
                    )
                }
            }
            item.creationDate?.let {
                Row {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        ""
                    )
                    Text(
                        text = it,
                    )
                }
            }
        }
    }
}