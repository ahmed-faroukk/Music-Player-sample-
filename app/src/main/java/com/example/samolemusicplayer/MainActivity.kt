package com.example.samolemusicplayer

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

lateinit var LoveBtn: Button
lateinit var playBtn: Button
lateinit  var seek : SeekBar
var LoveFlag = false
var PlayFlag = false
var TotalTime = 0
val handler =  Handler()
lateinit var Mp: MediaPlayer

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LoveBtn = findViewById(R.id.LoveBtn)
        playBtn = findViewById(R.id.PlayBtn)
                 seek = findViewById(R.id.seekkBar)
        Mp = MediaPlayer.create(applicationContext, R.raw.tobeloved)

        seek.max = Mp.duration
        seek?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
                if (fromUser){
                    Mp.seekTo(progress)
                }

            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started

            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped

            }
        })

        LoveBtn.setOnClickListener(View.OnClickListener {
            if (LoveFlag) {
                Toast.makeText(
                    applicationContext,
                    "Removed from Favourite List",
                    Toast.LENGTH_SHORT
                ).show()
                LoveBtn.animate()
                LoveBtn.setBackgroundResource(R.drawable.ic_unfav)
                LoveFlag = false

            } else {
                Toast.makeText(applicationContext, "Added to Favourite List", Toast.LENGTH_SHORT)
                    .show()
                LoveBtn.animate()
                LoveBtn.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                LoveFlag = true

            }
        })
        playBtn.setOnClickListener(View.OnClickListener {
            if (PlayFlag) {
                Mp.pause()
                playBtn.animate()
                playBtn.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
                PlayFlag = false
            } else {
                Mp.start()
                playBtn.animate()
                playBtn.setBackgroundResource(R.drawable.ic_baseline_pause_24)
                PlayFlag = true
                // link seekbar with song
                val UpdateSeeks = UpdateSeeks()
                handler.post(UpdateSeeks)

            }
        })
    }
}