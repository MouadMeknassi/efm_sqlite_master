package com.example.efm_kotlin_sqlite_master

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val btnList = findViewById<Button>(R.id.list)

        btnList.setOnClickListener {

            val i = Intent(applicationContext, MainActivity_List::class.java)

            startActivity(i)
        }

        val btnAdd = findViewById<Button>(R.id.add)
        btnAdd.setOnClickListener {

            val id = findViewById<EditText>(R.id.id).text.toString().toInt()
            val name = findViewById<EditText>(R.id.nom).text.toString()
            val price = findViewById<EditText>(R.id.price).text.toString().toDouble()

            val phone = Phone(id,name,price)

            val db = BDPhone(applicationContext)

            val resultat = db.ajouterPhone(phone)

            if(resultat != (-1).toLong()) {
                Toast.makeText(applicationContext,"Succesfuly Added", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(applicationContext,"Failure", Toast.LENGTH_LONG).show()
            }
        }
    }
}