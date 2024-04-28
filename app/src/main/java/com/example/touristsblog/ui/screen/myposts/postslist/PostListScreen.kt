package com.example.touristsblog.ui.screen.myposts.postslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.touristsblog.navigation.Routes
import com.example.touristsblog.navigation.routing.generatePath
import com.example.touristsblog.ui.theme.TouristsBlogTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostListScreen(
    viewModel: PostListViewModel
) {
    val screenState by viewModel.postsSate.collectAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 44.dp)) {
            Text(
                text = "Ваши посты",
                modifier = Modifier,
                style = MaterialTheme.typography.h1,
            )
        }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 60.dp, top = 24.dp)
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Button(
                    onClick = { viewModel.onClickCreateNewPost() },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Создать новый пост")
                }
            }
            if (screenState.isEmpty()) {
                item { Text(text = "У вас пока нет постов") }
            }
            screenState.forEach {
                item { PostCard(it, viewModel) }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                ) {

                }
            }
        }
    }
}

@Composable
fun PostCard(item: PostPreview, viewModel: PostListViewModel) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.clickable {
            viewModel.openPost(item.postId)
        },
        elevation = 5.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = item.postImage,
                contentDescription = "",
                modifier = Modifier.height(200.dp),
                contentScale = ContentScale.FillWidth
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
        }
    }
}
