import android.util.Log
import androidx.lifecycle.ViewModel
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class HomeViewModel : ViewModel() {

    fun fetchImageFromDALL_E_3(searchQuery: String, onResponse: (String?) -> Unit) {
        val client = OkHttpClient()
        val url = "https://api.proxyapi.ru/openai/"
        val JSONbody = JSONObject()
        try{
            JSONbody.put("model", "dall-e-2")
            JSONbody.put("prompt", searchQuery)
            JSONbody.put("size", "256x256")
        }catch(e:Exception) {
            e.printStackTrace()
        }
        val requestBody: RequestBody = JSONbody.toString().toRequestBody("application/json".toMediaType())
        val request: Request = Request.Builder().url(url+"v1/images/generations").header("Authorization", "Bearer ApiKey").post(requestBody).build()



        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("HomeViewModel", "Request failed: ${e.message}")
                onResponse("conn_err")
            }

            override fun onResponse(call: Call, response: Response) {

                try {
                    val jsonObject= JSONObject(response.body!!.string())
                    println(jsonObject)
                    val imgUrl = jsonObject.getJSONArray("data").getJSONObject(0).getString("url")
                    onResponse(imgUrl)
                }
                catch(e: JSONException){
                    onResponse("serv_err")
                }

            }
        })
    }
}
