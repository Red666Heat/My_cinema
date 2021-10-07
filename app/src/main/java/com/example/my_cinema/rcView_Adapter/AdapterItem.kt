package com.example.my_cinema.rcView_Adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.example.my_cinema.*

import com.example.my_cinema.model.ContentXX
import com.example.my_cinema.model.LanguageX
import com.example.my_cinema.retrofit.ApiRetrofitImage
import com.example.my_cinema.retrofit.RetrofitCommonImage
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.nio.charset.Charset



class AdapterItem (list: List<ContentXX>, context_: Context): RecyclerView.Adapter<AdapterItem.ViewHolder>(){
    var listFilm = list
    var context = context_


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var film_image: ImageView = view.findViewById(R.id.content_film_list_image)
        var film_name: TextView = view.findViewById(R.id.content_film_list_name)
        var checkBox: CheckBox = view.findViewById(R.id.content_film_list_checkBox)




        var pref: SharedPreferences? = null


        fun bind(film: ContentXX, context: Context, )
        {
            film_name.text = film.title

            var image: Bitmap
            var myInterface: ApiRetrofitImage = RetrofitCommonImage.retrofitService
            var myContent = myInterface.getImage(film.cover.id)
            var favoriteList = ArrayList<String>()


            pref = context.getSharedPreferences("FAVORITES", Context.MODE_PRIVATE)

            var ch: Boolean? = pref?.getBoolean(film.id.toString(), false)
            if (ch != null)
                checkBox.isChecked = ch





            myContent.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("my app", t.message.toString())
                }
                override fun onResponse(call: Call<ResponseBody>,
                                        response: Response<ResponseBody>) {

                    val bytes: ByteArray = response.body()!!.bytes()
                    image = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                    film_image.setImageBitmap(image)

                }

            })

            var str_data:String = "created at: "
            var str_full_data:String = film.created_at
            str_data += str_full_data.substring(8,10)
            str_data += '.'
            str_data += str_full_data.substring(5,7)
            str_data += '.'
            str_data += str_full_data.substring(0, 4)

            var languageList = ArrayList<String>()
            var lang: List<LanguageX> = film.languages
            for (i in 0..lang.size-1) {
                languageList.add(lang[i].title)
            }

            itemView.setOnClickListener(){
                var intent = Intent(context, FilmDescriptionActivity::class.java).apply {
                    putExtra("created_at", str_data)
                    putExtra("language", languageList)
                    putExtra("title", film.title)
                    putExtra("image", film.cover.id)


                }
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }


            checkBox.setOnClickListener(){
                val editor = pref?.edit()
                editor?.putBoolean(film.id.toString(), checkBox.isChecked)
                editor?.apply()
            }







        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(context)
        return ViewHolder(inflater.inflate(R.layout.content_film_list, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var film_image: ImageView = holder.itemView.findViewById(R.id.content_film_list_image)

        var myHeight: Int = -10
        var myWidth: Int = -10
        if (listFilm.isNotEmpty())
        {
            myHeight = listFilm[0].cover.height
            myWidth = listFilm[0].cover.width
        }
        film_image.minimumHeight = myHeight
        film_image.maxHeight = (myHeight*1.5).toInt()
        film_image.minimumWidth = myWidth
        film_image.maxWidth= (myWidth*1.5).toInt()

        var listItem = listFilm[position]
        holder.bind(listItem,  context)
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }

}

