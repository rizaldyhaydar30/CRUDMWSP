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

class CreateData : AppCompatActivity() {

    private lateinit var EditData: EditText
    private lateinit var buttonedit: MaterialButton
    private val api by lazy { ApiRetrofit().endpoint }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_data)
        supportActionBar!!.title = "Insert Data"
        setupView()
        setupListener()
    }

    private fun setupView(){
        EditData = findViewById(R.id.edit_data)
        buttonedit = findViewById(R.id.button_create)
    }

    private fun setupListener(){
        buttonedit.setOnClickListener {
            if(EditData.text.toString().isNotEmpty()) {
                Log.e("CreateData", EditData.text.toString())
                api.create(EditData.text.toString())
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
            }else {
                Toast.makeText(applicationContext,"Tidak boleh kosong!", Toast.LENGTH_SHORT)
                    .show()
            }
    }
    }
}