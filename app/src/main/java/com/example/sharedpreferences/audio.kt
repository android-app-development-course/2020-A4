package com.example.sharedpreferences

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast

//class Audio : Service(){
//    //创建媒体播放对象
//    private var player = MediaPlayer.create(this,R.raw.music)
//
//    private val mBinder = Finder()
//    override fun onBind(intent: Intent?): IBinder? {
//        //可以实现在HomeActivity调用MediaPlay
//        return mBinder
//    }
//
//    class Finder:Binder(){
//        fun startDownload() {
//            Log.d("MyService", "startDownload executed")
//        }
//
//        fun getProgress(): Int {
//            Log.d("MyService", "getProgress executed")
//            return 0
//        }
////        //获取总进度条
////        val duration: Int
////            get() = player.getDuration()//获取总进度条
////
////        //获取当前进度条
////        val currentPosition: Int
////            get() = player.getCurrentPosition()//获取当前进度条
////
////        fun setProgress(s: Int) { //更改当前音乐进度
////            player.seekTo(s)
////        }
//    }
//
//
//    override fun onCreate() {
//        super.onCreate()
//        Toast.makeText(this,"创建后台服务..",Toast.LENGTH_SHORT).show();
//    }
//
//    //启动后台
//    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
//        var i = intent.getIntExtra("play",-1)
//        when(i){
//            1->{
//                player.start()
//                Toast.makeText(this,"启动后台服务，播放音乐..",Toast.LENGTH_SHORT).show();
//            }
//            2->{
//                if (player!=null&&player.isPlaying){
//                    player.pause()
//                    Toast.makeText(this,"暂停..",Toast.LENGTH_SHORT).show();
//
//                }
//                else {
//                    player.start()
//                    Toast.makeText(this,"继续播放音乐..",Toast.LENGTH_SHORT).show();
//                }
//            }
//            3->{
//                player.stop()
//                //释放内存
//                player.release()
//                Toast.makeText(this,"销毁后台服务..",Toast.LENGTH_SHORT).show();
//            }
//        }
//        return super.onStartCommand(intent, flags, startId)
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("MyService", "onDestroy executed")
//    }
//}