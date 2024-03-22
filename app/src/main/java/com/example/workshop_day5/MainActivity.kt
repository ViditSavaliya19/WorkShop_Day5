package com.example.workshop_day5

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Id Binding
        var btnSearch = findViewById<Button>(R.id.btnSearch)
        var edtSearch = findViewById<EditText>(R.id.edtSearch)
        var txtAnswer = findViewById<TextView>(R.id.txtAnswer)

        val generativeModel = GenerativeModel(
            modelName = "gemini-pro",
            apiKey = "AIzaSyD0-o8iJXdtiEQmwyaQfO5w3I_FWwSsngY"
        )

        btnSearch.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                var prompt = edtSearch.text.toString()
                try {
                    val response = generativeModel.generateContent(prompt)
                    txtAnswer.text = response.text
                } catch (e:Exception) {
                    Toast.makeText(this@MainActivity, "Sorry, I'm AI so I have no idea of this Question", Toast.LENGTH_SHORT).show()
                    txtAnswer.text = "Sorry, I'm AI so I have no idea of this Question"
                    Log.e("TAG", "onCreate: $e");
                }

            }
        }

    }
}