package com.example.efm_kotlin_sqlite_master

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity_Update : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main_update)

        val b = intent.extras
        val id = findViewById<EditText>(R.id.id)
        val name = findViewById<EditText>(R.id.nom)
        val price = findViewById<EditText>(R.id.price)

        if (b != null) {
            id.setText(b.getInt("id").toString())
            id.isEnabled=false
            name.setText(b.getString("name"))
            price.setText(b.getDouble("price").toString())
        }

        val btnUpdate = findViewById<Button>(R.id.Update)

        btnUpdate.setOnClickListener {

            val id = findViewById<EditText>(R.id.id).text.toString().toInt()
            val name = findViewById<EditText>(R.id.nom).text.toString()
            val price = findViewById<EditText>(R.id.price).text.toString().toDouble()


            val phone = Phone(id, name, price)

            val db = BDPhone(applicationContext)

            val resultat = db.updatePhone(phone)

            if (resultat != (-1).toLong()) {
                Toast.makeText(applicationContext, "Successfully Updated", Toast.LENGTH_LONG).show()
                //finish() // Fermez l'activité de mise à jour
            } else {
                Toast.makeText(applicationContext, "Update Failed", Toast.LENGTH_LONG).show()
            }
        }
    }
}