package com.example.my_cinema

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.my_cinema.retrofit.ApiRetrofitImage
import com.example.my_cinema.retrofit.RetrofitCommonImage
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmDescriptionActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_description)

        var textTitle = findViewById<TextView>(R.id.activity_film_description_title)
        val textCreatedAt = findViewById<TextView>(R.id.activity_film_description_created_at)
        val textLanguage = findViewById<TextView>(R.id.activity_film_description_language)
        val imageImage = findViewById<ImageView>(R.id.activity_film_description_image)

        textTitle.text = intent.getStringExtra("title")

        textCreatedAt.text = intent.getStringExtra("created_at")

        var str:String = ""
        var strList: ArrayList<String>? = intent.getStringArrayListExtra("language")
        if (strList != null) {
            for (i in 0..strList.size-1) {
                str += strList[i]
                str += "\n"
            }
        }
        textLanguage.text = str




        var myInterface: ApiRetrofitImage = RetrofitCommonImage.retrofitService
        var myContent = myInterface.getImage(intent.getStringExtra("image")!!)


        myContent.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("my app", t.message.toString())
            }
            override fun onResponse(call: Call<ResponseBody>,
                                    response: Response<ResponseBody>
            ) {

                val bytes: ByteArray = response.body()!!.bytes()
                var image = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                imageImage.setImageBitmap(image)

            }

        })






    }
}