package com.example.sharedpreferences
import android.content.ContentValues
import android.content.DialogInterface
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.mine.*
import kotlinx.android.synthetic.main.mydata.*

class DataActivity : AppCompatActivity() {
    var account_now2=MainActivity.account_now
    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.mydata)

        val dbHelper=Acc_Pw_Database(this,"register.db",1)
        val db=dbHelper.writableDatabase

        data_return.setOnClickListener {
            val intent = Intent(this, MineActivity::class.java)
            startActivity(intent)
        }

        confirm.setOnClickListener{
            AlertDialog.Builder(this)
                .setMessage("你想要修改昵称和资料吗？")
                .setPositiveButton("确定", DialogInterface.OnClickListener{ dialogInterface, i->
                    var nickname=nickname.text.toString()
                    var signature=signature.text.toString()
                    val values= ContentValues()
                    values.put("nickname",nickname)
                    values.put("signature",signature)
                    db.update("Acc_Pw",values,"account=?", arrayOf("$account_now2"))
                    Toast.makeText(this,"修改昵称资料成功！", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                    finish()
                })
                .setNegativeButton("取消",null)
                .create()
                .show()

        }
    }
    override fun onStart()
    {
        super.onStart()
        val dbHelper = DiaryDatabase(this,"register.db",1)
        val db = dbHelper.writableDatabase
        val cursor = db.rawQuery("select * from Acc_Pw where account=?", arrayOf("$account_now2"))

        var Nickname=""
        var Signature =""
        if(cursor.moveToFirst()){
            do {
                //遍历Cursor对象，取出数据
                Nickname = cursor.getString(cursor.getColumnIndex("nickname"))
                Signature = cursor.getString(cursor.getColumnIndex("signature"))
            }while (cursor.moveToNext())
        }
        nickname.setText(Nickname)
        signature.setText(Signature)
        cursor.close()
    }
}
