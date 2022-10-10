package com.example.samolemusicplayer

class UpdateSeeks :Runnable {
    override fun run() {
        seek.setProgress(Mp.currentPosition)
     }
}