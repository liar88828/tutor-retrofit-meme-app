package com.tutor.retrofit_meme

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tutor.retrofit_meme.data.model.Meme
import com.tutor.retrofit_meme.ui.theme.RetrofitmemeTheme
import com.tutor.retrofit_meme.utils.RetrofitInstance
import com.tutor.retrofit_meme.view.DetailScreen
import com.tutor.retrofit_meme.view.DetailScreenData
import com.tutor.retrofit_meme.view.MainScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			RetrofitmemeTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					val navController = rememberNavController()
					var memesList by remember { mutableStateOf(listOf<Meme>()) }
					val scope = rememberCoroutineScope()

					LaunchedEffect(key1 = true) {
						scope.launch(Dispatchers.IO) {
							val response = try {
								RetrofitInstance.api.getMemeLists()

							} catch (e: Exception) {
								Toast.makeText(
									this@MainActivity,
									"App Error: ${e.message}",
									Toast.LENGTH_SHORT
								).show()
								return@launch
								//maybe from java
							} catch (e: IOException) {
								Toast.makeText(
									this@MainActivity,
									"Http Error: ${e.message}",
									Toast.LENGTH_SHORT
								).show()
								return@launch
							}
							if (response.isSuccessful && response.body() != null) {
								withContext(Dispatchers.Main) {
									memesList = response.body()!!.data.memes
								}
							}
						}
					}


					NavHost(
						navController = navController,
						startDestination = RScreen.Main.route,
						modifier = Modifier.padding(innerPadding)
					) {
						composable(RScreen.Main.route) {
							MainScreen(
								navController = navController,
								memesList = memesList
							)
						}
						composable(
							RScreen.Detail.route + "?name={name}&url={url}",
							arguments = listOf(
								navArgument("name") { type = NavType.StringType },
								navArgument("url") { type = NavType.StringType })
						) {
							fun getParam(key: String) = it.arguments?.getString(key)
							DetailScreen(
								navController = navController,
								item = DetailScreenData(
									name = getParam("name"),
									url = getParam("url"),
								)
							)
						}
					}
				}
			}
		}
	}
}

sealed class RScreen(val route: String) {
	data object Main : RScreen("main")
	data object Detail : RScreen("detail")

}