 package com.example.sharedpreferences

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.sharedpreferences.songList.SongListActivity
import kotlinx.android.synthetic.main.activity_home.*

 class HomeAcvitity : AppCompatActivity() {
     //现在登录的用户
     var account_now2=MainActivity.account_now
     var check_list = ArrayList<String>()
     private val diaryList = ArrayList<String>()
     private lateinit var adapter: ArrayAdapter<String>
     override fun onStart() {
         super.onStart()
         val dbHelper = DiaryDatabase(this,"Diary.db",1)
         val db = dbHelper.writableDatabase

         val cursor = db.rawQuery("select * from Book where account=?", arrayOf("$account_now2"))
         check_list.clear()
         diaryList.clear()
         if(cursor.moveToFirst()){
             do {
                 //遍历Cursor对象，取出数据
                 val title = cursor.getString(cursor.getColumnIndex("title"))
                 val content = cursor.getString(cursor.getColumnIndex("content"))
                 val time = cursor.getString(cursor.getColumnIndex("time"))
//                send_title = title
//                send_content = content
//                send_time = time
                 if(content!=""&&title!=null&&time!=null) {
                     check_list.add("$title\n$content\n$time")
                     diaryList.add("$title\n$time")
                 }

             }while (cursor.moveToNext())
         }
         //刷新ListView
         adapter.notifyDataSetChanged()
         cursor.close()
         //查询结束
     }

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_home)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, diaryList)
        diary_view.adapter = adapter

        //定义Intent
//        val intent_home = Intent(this, HomeAcvitity::class.java)
        val intent_friends = Intent(this, FriendsActivity::class.java)
        val intent_mine = Intent(this, MineActivity::class.java)
        val intent_write = Intent(this, WriteDiaryActivity::class.java)
        val intent_music = Intent(this, SongListActivity::class.java)

//        val dbHelper = DiaryDatabase(this,"Diary.db",1)


        home_friends.setOnClickListener {
            startActivity(intent_friends)
        }
        home_mine.setOnClickListener {
            startActivity(intent_mine)
        }

        play.setOnClickListener {
           startActivity(intent_music)
        }

//        class mClick : View.OnClickListener {
//            fun onClick(view: View) {
//                if (view === btn) { //开始
//                    intent.putExtra("play", 1)
//                    this@MainActivity.startService(intent) //开启服务
//                    myConnection = MyConnection() //建立新连接对象
//                    bindService(
//                        intent,
//                        myConnection,
//                        Context.BIND_AUTO_CREATE
//                    ) //建立和service连接
//                } else if (view === btn2) { //暂停
//                    intent.putExtra("play", 2)
//                    this@MainActivity.startService(intent)
//                    println(2)
//                } else if (view === btn3) { //结束
//                    intent.putExtra("play", 3)
//                    this@MainActivity.startService(intent)
//                }
//            }
//        }

        write_diary.setOnClickListener {
            startActivity(intent_write)
//            dbHelper.writableDatabase
        }

        diary_view.setOnItemClickListener{ _, _, position, _ ->
            val diaryAll = check_list[position]
            var list = diaryAll.split("\n")
            val intent_check = Intent(this,CheckDiaryActivity::class.java)
            intent_check.putExtra("send_title",list[0])
            intent_check.putExtra("send_content",list[1])
            intent_check.putExtra("send_time",list[2])
            //进入查看页面
            startActivity(intent_check)
        }
    }

     private val MyConnection = object : ServiceConnection {

         override fun onServiceConnected(name: ComponentName, service: IBinder) {

         }

         override fun onServiceDisconnected(name: ComponentName) {
             Log.d("MyService", "onServiceDisconnected")
         }

     }

    override fun onDestroy() {
        super.onDestroy()
    }

}