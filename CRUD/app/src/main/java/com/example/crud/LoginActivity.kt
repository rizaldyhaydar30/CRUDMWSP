package com.example.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var Login : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.title = "Login"

        Login = findViewById(R.id.button_login)


        Login.setOnClickListener(this)

        }

    override fun onClick(v: View) {
        when(v.id){
            R.id.button_login -> {
                val pindah = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(pindah)
            }
        }
    }
}