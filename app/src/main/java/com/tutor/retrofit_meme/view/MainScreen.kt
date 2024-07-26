package com.tutor.retrofit_meme.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.tutor.retrofit_meme.RScreen
import com.tutor.retrofit_meme.data.model.Meme

@Composable
fun MainScreen(
	navController: NavHostController,
	modifier: Modifier = Modifier,
	memesList: List<Meme>
) {
	val textState = remember { mutableStateOf(TextFieldValue("")) }
//	val searchedText = textState.value.text
	Column(modifier = modifier.fillMaxSize()) {
		SearchValue(state = textState, placeholder = "Search here ....")
		LazyVerticalStaggeredGrid(
			columns = StaggeredGridCells.Fixed(2),
			contentPadding = PaddingValues(10.dp)
		) {
			items(
				items = memesList.filter {
					it.name.contains(textState.value.text, ignoreCase = true)
				},
				key = { it.id }
			) { MemeItem(it, navController) }
		}
	}
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemeItem(item: Meme, navController: NavHostController, modifier: Modifier = Modifier) {
	Card(modifier = modifier
		.wrapContentSize()
		.padding(10.dp),
		colors = CardDefaults.cardColors(
			containerColor = Color.Green.copy(alpha = 0.2f),
		),
		onClick = {
			navController.navigate(RScreen.Detail.route+"?name=${item.name}&url=${item.url}")
		}) {
		Column(modifier = modifier.padding(10.dp)) {
			AsyncImage(
				model = item.url,
				contentDescription = item.name,
				modifier = modifier
					.fillMaxSize()
					.clip(RoundedCornerShape(10.dp))
			)
			Spacer(modifier = modifier.height(10.dp))
			Text(
				text = item.name,
				fontSize = 20.sp,
				textAlign = TextAlign.Center,
				modifier = modifier
					.fillMaxWidth()
					.basicMarquee()
			)
		}
	}
	Spacer(modifier = modifier.height(10.dp))

}

@Composable
fun SearchValue(
	modifier: Modifier = Modifier,
	placeholder: String,
	state: MutableState<TextFieldValue>
) {
	TextField(
		placeholder = { Text(text = placeholder) },
		modifier = modifier
			.fillMaxWidth()
			.padding(horizontal = 20.dp, vertical = 10.dp)
			.clip(RoundedCornerShape(10.dp))
			.border(2.dp, Color.DarkGray, RoundedCornerShape(10.dp)),
		maxLines = 1,
		singleLine = true,
		textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
		colors = TextFieldDefaults.colors(
			unfocusedContainerColor = Color.Green.copy(alpha = 0.2f),
			),
		value = state.value,
		onValueChange = { value -> state.value = value },
	)
}

@Preview(showBackground = true)
@Composable
private fun MemeItemPrev() {
	MemeItem(
		item = testMeme,
		navController = NavHostController(LocalContext.current)
	)
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPrev() {
	MainScreen(
		navController = NavHostController(LocalContext.current),
		memesList = listOf(testMeme, testMeme2)
	)

}

val testMeme = Meme(
	box_count = 0,
	captions = 0,
	height = 0,
	id = "0",
	name = "Long Zhu Sanjay Bano",
	url = "https://i.imgflip.com/30b1gx.jpg",
	width = 0,
)
val testMeme2 = Meme(
	box_count = 123,
	captions = 1231,
	height = 12312,
	id = "42141",
	name = "Long Zhu Sanjay B1212",
	url = "https://i.imgflip.com/30b1g4.jpg",
	width = 143,
)
