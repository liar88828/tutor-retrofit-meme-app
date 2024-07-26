package com.tutor.retrofit_meme.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun DetailScreen(
	navController: NavHostController,
	modifier: Modifier = Modifier,
	item: DetailScreenData
) {
	if (item.name == null || item.url == null) {
		BackButton(navController)
	} else {
		Card(
			modifier = modifier
				.fillMaxSize()
				.padding(10.dp),
			colors = CardDefaults.cardColors(
				containerColor = Color.Green.copy(alpha = 0.2f),

			),
		) {
			Column(
				modifier = modifier
					.fillMaxSize()
					.padding(10.dp),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center,
			) {
				AsyncImage(
					modifier = modifier
						.fillMaxWidth()
						.clip(RoundedCornerShape(10.dp)),
					model = item.url,
					contentDescription = item.name,
				)
				Spacer(modifier = modifier.height(20.dp))
				Text(
					text = item.name,
					modifier = modifier
						.fillMaxWidth()
						.wrapContentSize(),
					fontSize = 30.sp,
					fontWeight = FontWeight.Bold,
					textAlign = TextAlign.Center,
					lineHeight = 45.sp
				)
				BackButton(navController)
			}
		}
	}
}

@Composable
private fun BackButton(navController: NavHostController) {
	Button(
		colors = ButtonDefaults.buttonColors(
			containerColor = Color.Red.copy(alpha = 0.3f)
		),
		onClick = {
			navController.navigateUp()
		}) { Text("Back") }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPrev() {
	DetailScreen(
		navController = NavHostController(LocalContext.current),
		item = DetailScreenData(
			name = "Long Zhu Sanjay Bano",
			url = "https://i.imgflip.com/30b1gx.jpg",
		)
	)
}

data class DetailScreenData(
	val name: String? = null,
	val url: String? = null,
)

