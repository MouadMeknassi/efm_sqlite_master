package com.example.efm_kotlin_sqlite_master

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity_List : AppCompatActivity() {
    lateinit var listView: ListView
    override fun onResume() {
        super.onResume()
        loadData()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main_list)
        listView = findViewById(R.id.listview)

        val db = BDPhone(applicationContext)

        val phoneList = db.getAll()

        val stringList = ArrayList<String>()

        for(phone in phoneList){

            stringList.add(phone.toString())

        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stringList)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->

            val phone = phoneList[position]
            val intent = Intent(applicationContext, MainActivity_Update::class.java)
            val bundle = Bundle()
            bundle.putInt("id", phone.id)
            bundle.putString("name", phone.name)
            bundle.putDouble("price", phone.price)
            intent.putExtras(bundle)
            startActivity(intent)
            //finish()
        }

    }
    fun loadData() {
        listView = findViewById(R.id.listview)
        val db = BDPhone(applicationContext)
        val phoneList = db.getAll()
        val stringList = ArrayList<String>()
        for(app in phoneList){
            stringList.add(app.toString())
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stringList)
        listView.adapter = adapter
    }
}