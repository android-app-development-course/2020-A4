package com.example.sharedpreferences.songList

import com.example.sharedpreferences.BaseView
import com.example.sharedpreferences.model.Song

interface SongListView : BaseView{
    fun showLoading()

    fun stopLoading()

    fun updateSongState(song: Song, isPlaying: Boolean)

    fun onSongClick()
}