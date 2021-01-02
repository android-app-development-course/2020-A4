package com.example.sharedpreferences

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.change_password.*

class ChangePassword : AppCompatActivity() {
    var account_now2=MainActivity.account_now
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_password)
        val intent = Intent(this,setting::class.java)

        data_return.setOnClickListener{
            startActivity(intent)
            finish()

        }

        confirm_change.setOnClickListener{
            var former_password=former_password.text.toString()
            var new_password=new_password.text.toString()
            var confirm_new_password=confirm_new_password.text.toString()

            var old_password=""
            val dbHelper=Acc_Pw_Database(this,"register.db",1)
            val db=dbHelper.writableDatabase
            val cursor = db.rawQuery("select * from Acc_Pw where account=?", arrayOf("$account_now2"))
            if(cursor.moveToFirst()){
                do {
                    //遍历Cursor对象，取出此账号的密码
                    old_password = cursor.getString(cursor.getColumnIndex("password"))
                }while (cursor.moveToNext())
            }
            if(former_password!=old_password)
            {
                Toast.makeText(this,"原密码错误！",Toast.LENGTH_SHORT).show()
            }
            else if(new_password!=confirm_new_password)
            {
                Toast.makeText(this,"请重新确认新密码！",Toast.LENGTH_SHORT).show()
            }
            else
            {
                val values= ContentValues()
                values.put("password",new_password)
                db.update("Acc_Pw",values,"account=?", arrayOf("$account_now2"))
                Toast.makeText(this,"修改密码成功！",Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }

        }
    }
}