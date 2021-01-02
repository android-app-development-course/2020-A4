package com.example.sharedpreferences

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.write_diary.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class WriteDiaryActivity() : AppCompatActivity() {

    var title = ""
    var content = ""
    var time = ""
    var account_now2=MainActivity.account_now

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.write_diary)
        
        val date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = date.format(formatter)

        //"$formatted" 是时间

//        var title = ""
//        var content = ""
//        var time = ""

        val dbHelper = DiaryDatabase(this,"Diary.db",1)

        //取消，返回主页
        cancel.setOnClickListener {
            diary_title.setText("")
            diary_content.setText("")
            val intent_home = Intent(this, HomeAcvitity::class.java)
            startActivity(intent_home)
        }

        //保存
        finish_writing.setOnClickListener {
            if(diary_title.text.toString().equals("")||diary_content.text.toString().equals(""))    //这里&&改成||
            {
                AlertDialog.Builder(this)
                    .setMessage("请输入标题或内容！")
                    .create()
                    .show()
            }
            else{
                title = diary_title.text.toString()
                content = diary_content.text.toString()
                time = "$formatted"


                //存数据进表
                val db = dbHelper.writableDatabase
                val values = ContentValues().apply {
                    put("title",title)
                    put("content",content)
                    put("time",time)
                    put("account",account_now2)
                }
                db.insert("Book",null,values)

                Toast.makeText(this, "Save succeeded", Toast.LENGTH_SHORT).show()
                diary_title.setText("")
                diary_content.setText("")

                val intent = Intent(this, HomeAcvitity::class.java)
                startActivity(intent)
            }
        }
    }

}