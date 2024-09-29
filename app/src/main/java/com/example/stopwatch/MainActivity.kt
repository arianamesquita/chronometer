package com.example.stopwatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var seconds = 0
    private var running = false
    private lateinit var handler : Handler
    private lateinit var timeView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeView = findViewById(R.id.textView)
        handler = Handler(Looper.getMainLooper())

        val btnStart: Button = findViewById(R.id.btn_start)
        btnStart.setOnClickListener{
            onClickStart()
        }

        val btnStop: Button = findViewById(R.id.btn_stop)
        btnStop.setOnClickListener{
            onClickStop()
        }

        val btnReset: Button = findViewById(R.id.btn_reset)
        btnReset.setOnClickListener{
            onClickReset()
        }

        runTimer()
    }

    private fun onClickStart(){
        running = true
    }

    private fun onClickStop(){
        running = false
    }

    private fun onClickReset(){
        running = false
        seconds = 0
        timeView.text = "0:00:00"
    }

    private fun runTimer(){
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds/3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60

                val time: String = String.format("%d:%02d:%02d", hours, minutes, secs)
                timeView.text = time

                if(running){
                    seconds++
                }

                handler.postDelayed(this, 1000)
            }
        })

    }
}