package com.example.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var fabCreate : FloatingActionButton
    private lateinit var DataAdapter : DataAdapter
    private lateinit var ListData : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = "Dashboard"
        setupView()
        setupList()
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        getData()
    }

    private fun setupView(){
        ListData = findViewById(R.id.list_data)
        fabCreate = findViewById(R.id.fab_edit)
    }

    private fun setupList(){
        DataAdapter = DataAdapter(arrayListOf(), object : DataAdapter.OnAdapterListener{
            override fun onClick(Data: DataModel.Data) {
                startActivity(Intent(this@MainActivity, EditActivity::class.java)
                    .putExtra("nama", Data)
                )
            }

            override fun onDelete(data: DataModel.Data) {
                api.delete( data.id_hewan!!)
                    .enqueue(object : Callback<SimpanModel>{
                        override fun onResponse(
                            call: Call<SimpanModel>,
                            response: Response<SimpanModel>
                        ) {
                            if (response.isSuccessful){
                                if(response.isSuccessful){
                                    val result = response.body()
                                    Toast.makeText(
                                        applicationContext,
                                        result!!.message,
                                        Toast.LENGTH_SHORT)
                                        .show()
                                        getData()
                                }
                            }

                        }

                        override fun onFailure(call: Call<SimpanModel>, t: Throwable) {

                        }

                    })
            }

        })
        ListData.adapter = DataAdapter
    }

    private fun setupListener(){
        fabCreate.setOnClickListener {
            startActivity(Intent(this, CreateData::class.java))
        }
    }

    private fun getData(){
        api.data().enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {

                if (response.isSuccessful) {
                    val listData = response.body()!!.tb_data
                    DataAdapter.setData(listData)
//                    Log.e("MainActivity", response.toString()
//                    )
                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {

                Log.e("MainActivity", t.toString())
            }

        })
    }
}