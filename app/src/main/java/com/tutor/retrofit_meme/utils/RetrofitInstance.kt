package com.tutor.retrofit_meme.utils

import com.tutor.retrofit_meme.data.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
	val api: ApiInterface by lazy {
		Retrofit.Builder()
			.baseUrl(Urls.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(ApiInterface::class.java)
	}
}