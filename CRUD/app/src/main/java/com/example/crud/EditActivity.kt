package com.example.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditActivity : AppCompatActivity() {

    private lateinit var EditData : EditText
    private lateinit var buttonupdate: MaterialButton
    private val api by lazy { ApiRetrofit().endpoint }
    private val data by lazy { intent.getSerializableExtra("nama") as DataModel.Data }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        supportActionBar!!.title = "Update Data"
        setupView()
        setupListener()
    }

    private fun setupView(){
        EditData = findViewById(R.id.edit_data)
        buttonupdate = findViewById(R.id.button_update)
        EditData.setText( data.nama_hewan )
    }

    private fun setupListener(){
        buttonupdate.setOnClickListener {
            api.update( data.id_hewan!!,EditData.text.toString())
                .enqueue(object : Callback<SimpanModel>{
                    override fun onResponse(
                        call: Call<SimpanModel>,
                        response: Response<SimpanModel>
                    ) {
                        if(response.isSuccessful){
                            val result = response.body()
                            Toast.makeText(
                                applicationContext,
                                result!!.message,
                                Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<SimpanModel>, t: Throwable) {

                    }

                })
        }
    }

}