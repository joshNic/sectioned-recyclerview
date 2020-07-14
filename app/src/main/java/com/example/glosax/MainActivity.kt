package com.example.glosax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listItems.layoutManager = LinearLayoutManager(this)
//        listItems.adapter = NoteRecyclerAdapter(this,DataManager.glosaries)
        var gyu = GlosaryListAdapter()
        gyu.addHeaderAndSumbitList(DataManager.glosaries)
        listItems.adapter = gyu

    }
}