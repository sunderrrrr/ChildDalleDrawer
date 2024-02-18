import android.util.Log
import androidx.lifecycle.ViewModel
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

class HomeViewModel : ViewModel() {

    fun fetchImageFromDALL_E_3(searchQuery: String, onResponse: (String?) -> Unit) {
        val client = OkHttpClient()
        val url = "https://api.openai.com/v1/engines/davinci/images"
        val jsonBody = JSONObject().apply {
            put("search_query", searchQuery)
        }.toString()

        val request = Request.Builder()
            .url(url)
            .post(RequestBody.create("application/json".toMediaTypeOrNull(), jsonBody))
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
