package com.example.sharedpreferences.playSong

import com.example.sharedpreferences.BaseView
import com.example.sharedpreferences.model.Song

interface PlaySongView : BaseView {
    fun updateSongState(song: Song, isPlaying: Boolean, progress: Int)

    fun showRepeat(isRepeat: Boolean)

    fun showRandom(isRandom: Boolean)
}