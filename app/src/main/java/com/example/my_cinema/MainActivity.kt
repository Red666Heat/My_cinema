package com.example.my_cinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_cinema.model.AllContent
import com.example.my_cinema.model.ContentX
import com.example.my_cinema.rcView_Adapter.AdapterMain
import com.example.my_cinema.retrofit.ApiRetrofitInterface
import com.example.my_cinema.retrofit.RetrofitClient
import com.example.my_cinema.retrofit.RetrofitCommon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File

class MainActivity : AppCompatActivity() {

    //lateinit var myService: ApiRetrofitInterface
    lateinit var myAdapter: AdapterMain
    lateinit var myContent: Call<AllContent>

    private var myAllContent: AllContent? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //RecyclerView
        val rcView: RecyclerView = findViewById(R.id.main_rcView)
        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(
            baseContext,
            LinearLayoutManager.VERTICAL,
            false
        )

        //inti API
        var myInterface: ApiRetrofitInterface = RetrofitCommon.retrofitService
        myContent = myInterface.getAllContent()

        getAllFilm()

    }

    private fun getAllFilm()
    {
        myContent.enqueue(object : Callback<AllContent>{
            override fun onFailure(call: Call<AllContent>, t: Throwable) {
                Log.e("my app", t.message.toString())
            }

            override fun onResponse(call: Call<AllContent>, response: Response<AllContent>) {

                myAllContent = response.body()


                var myListFilm: List<ContentX>? = myAllContent?.content
                if (myListFilm != null)
                {
                    myAdapter = AdapterMain(myListFilm, baseContext)
                }
                else
                {
                    Log.e("my app", "adapter failed")
                }
                myAdapter.notifyDataSetChanged()
                val rcView: RecyclerView = findViewById(R.id.main_rcView)
                rcView.adapter = myAdapter
            }
        })
    }


}