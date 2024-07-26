package com.tutor.retrofit_meme.data

import com.tutor.retrofit_meme.data.model.AllMemeDatas
import com.tutor.retrofit_meme.data.model.Meme
import com.tutor.retrofit_meme.utils.Urls
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
	@GET(Urls.GET_MEME)
	suspend fun getMemeLists(): Response<AllMemeDatas>
}
