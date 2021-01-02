package com.example.sharedpreferences

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.mine.*
import java.util.*


class MineActivity : AppCompatActivity() {
    private val fromAlbum = 2
    //现在登录的用户
    var account_now2=MainActivity.account_now
    private val diaryList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>
    override fun onStart() {
        super.onStart()
        val dbHelper = DiaryDatabase(this,"register.db",1)
        val db = dbHelper.writableDatabase

        val cursor = db.rawQuery("select * from Acc_Pw where account=?", arrayOf("$account_now2"))
        diaryList.clear()
        if(cursor.moveToFirst()){
            do {
                //遍历Cursor对象，取出数据
                val Nickname = cursor.getString(cursor.getColumnIndex("nickname"))
                val Signature = cursor.getString(cursor.getColumnIndex("signature"))
                user_nickname.setText(Nickname)
                user_signature.setText(Signature)
            }while (cursor.moveToNext())
        }
        cursor.close()
        //查询结束
    }
    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.mine)

        val intent_login = Intent(this,MainActivity::class.java)
        val intent_home = Intent(this, HomeAcvitity::class.java)
        val intent_friends = Intent(this, FriendsActivity::class.java)
        val intent_data = Intent(this, DataActivity::class.java)
        val intent_setting = Intent(this, com.example.sharedpreferences.setting::class.java)
        //我的资料
        mine_data.setOnClickListener {
            startActivity(intent_data)
        }

        //设置
        mine_setting.setOnClickListener {
            startActivity(intent_setting)
        }

        //更换头像
        headpic.setOnClickListener{
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            //指定只显示图片
            intent.type = "image/*"
            startActivityForResult(intent,fromAlbum)
        }

        //登出
        btn_logout.setOnClickListener {
            startActivity(intent_login)
            finish()
        }

        mine_home.setOnClickListener {
            startActivity(intent_home)
        }
        mine_friends.setOnClickListener {
            startActivity(intent_friends)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            fromAlbum ->{
                if(resultCode == Activity.RESULT_OK && data!= null){
                    data.data?.let{uri->
                        val bitmap=getBitmapFromUri(uri)
                        headpic.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri) = contentResolver.openFileDescriptor(uri,"r")?.use{
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }

}