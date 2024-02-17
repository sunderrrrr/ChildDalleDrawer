package com.bkmzdev.childdrawer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONObject

class HomeViewModel : ViewModel() {
    fun fetchImageFromDALL_E_3(searchQuery: String, onResponse: (String?) -> Unit) {
        val client = OkHttpClient()
        val url = "https://api.openai.com/v1/engines/davinci/images"
        val jsonBody = JSONObject().apply {
            put("search_query", searchQuery)
        }.toString()

        val request = Request.Builder()
            .url(url)
            .post(RequestBody.create(MediaType.parse("application/json"), jsonBody))
            .addHeader("Authorization", "Bearer YOUR_API_KEY")  // Замените YOUR_API_KEY на ваш ключ API от OpenAI
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("HomeViewModel", "Request failed: ${e.message}")
                onResponse(null)
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonResponse = response.body?.string()
                val imageUrl = JSONObject(jsonResponse).optString("image_url")
                onResponse(imageUrl)
            }
        })
    }

}