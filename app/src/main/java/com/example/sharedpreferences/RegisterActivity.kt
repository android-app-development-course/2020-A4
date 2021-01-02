package com.example.sharedpreferences

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_register.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class RegisterActivity : AppCompatActivity(){
    var time=""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(saveInstanceState: Bundle?){
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_register)

        val date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = date.format(formatter)


        val dbHelper=Acc_Pw_Database(this,"register.db",1)  //创建打开register数据库

        register_bt.setOnClickListener {
            val default_sig = "最喜欢每日一许啦！"
//            val prefs = getSharedPreferences("data",Context.MODE_PRIVATE)
//            val editor = prefs.edit()
//            editor.clear()
            val account = reg_account.text.toString()
            val password = reg_password.text.toString()
//            editor.putString("account",account)
//            editor.putString("password",password)
//            editor.apply()
            var judge=0
            if(account==""&&password=="")
            {
                Toast.makeText(this,"请输入您要注册的账号和密码",Toast.LENGTH_SHORT).show()
            }
            else if(account==""&&password!="")
            {
                Toast.makeText(this,"请输入您要注册的账号",Toast.LENGTH_SHORT).show()
            }
            else if(account!=""&&password=="")
            {
                Toast.makeText(this,"请输入您要注册的密码",Toast.LENGTH_SHORT).show()
            }
            else
            {
                val db=dbHelper.writableDatabase
                val values= ContentValues().apply {
                    put("account", account)
                    put("password", password)
                    put("nickname",account)
                    put("signature",default_sig)
                }
                db.insert("Acc_Pw",null,values)
                judge=1
            }
            if(judge==1) {
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()

                //每个新注册的用户有一篇默认日记，内容为“今天注册了《每天》“
                time = "$formatted"
                val dbHelper = DiaryDatabase(this,"Diary.db",1)
                val db = dbHelper.writableDatabase
                val acc = ContentValues().apply {
                    put("title","每天")
                    put("content","今天注册了《每天》。")
                    put("time",time)
                    put("account", account)
                }
                db.insert("Book",null,acc)


                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}