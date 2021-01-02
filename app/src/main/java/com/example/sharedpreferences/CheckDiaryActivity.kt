package com.example.sharedpreferences

import android.app.Activity
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.check_diary.*
import kotlinx.android.synthetic.main.write_diary.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class CheckDiaryActivity : AppCompatActivity()  {

    var title = ""
    var content = ""
    var time = ""
    var account_now=""
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.check_diary)

        val date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = date.format(formatter)

        val accept_title = intent.getStringExtra("send_title")
        val accept_content = intent.getStringExtra("send_content")
        val accept_time = intent.getStringExtra("send_time")
        account_now= intent.getStringExtra("account_now").toString()
        val dbHelper = DiaryDatabase(this,"Diary.db",1)


        check_diary_title.setText(accept_title)
        check_diary_content.setText(accept_content)

        //返回
        returnHome.setOnClickListener {
            val intent = Intent(this, HomeAcvitity::class.java)
            finish()
            //startActivity(intent)

            check_diary_title.setText("")
            check_diary_content.setText("")
        }

        //清空
        delete.setOnClickListener{
            val db = dbHelper.writableDatabase
            AlertDialog.Builder(this)
                .setMessage("你想要删除这篇日记吗？")
                .setPositiveButton("确定",DialogInterface.OnClickListener{dialogInterface,i->
                    db.delete("Book","title = ?", arrayOf("$accept_title"))
                    val intent = Intent(this, HomeAcvitity::class.java)
                    finish()
                    //startActivity(intent)

                    check_diary_title.setText("")
                    check_diary_content.setText("")
                })
                .setNegativeButton("取消",null)
                .create()
                .show()
//            db.delete("Book","title = ?", arrayOf("$accept_title"))
        }

        //确认编辑
        edit_diary.setOnClickListener{
            title = check_diary_title.text.toString()
            content = check_diary_content.text.toString()
            time = "$formatted"
            if(title.equals("")||content.equals(""))    //新加的检测标题或者内容是否空
            {
                AlertDialog.Builder(this)
                    .setMessage("请输入标题或内容！")
                    .create()
                    .show()
            }
            else {
                val db = dbHelper.writableDatabase
                val value1 = ContentValues()
                val value2 = ContentValues()
                val value3 = ContentValues()
                value1.put("title", title)
                value2.put("content", content)
                value3.put("time", time)

                AlertDialog.Builder(this)
                    .setMessage("你确定修改这篇日记吗？")
                    .setPositiveButton("确定",DialogInterface.OnClickListener{dialogInterface,i->
                        db.update("Book", value1, "title = ?", arrayOf(accept_title))
                        db.update("Book", value2, "content = ?", arrayOf(accept_content))
                        db.update("Book", value3, "time=?", arrayOf(accept_time))
                        val intent = Intent(this, HomeAcvitity::class.java)
                        finish()
                        //startActivity(intent)

                        check_diary_title.setText("")
                        check_diary_content.setText("")
                    })
                    .setNegativeButton("取消",null)
                    .create()
                    .show()
//                db.update("Book", value1, "title = ?", arrayOf(accept_title))
//                db.update("Book", value2, "content = ?", arrayOf(accept_content))
//                db.update("Book", value3, "time=?", arrayOf(accept_time))      //这里好像改了

//                AlertDialog.Builder(this)                                       //新加的
//                    .setMessage("修改成功！")
//                    .create()
//                    .show()
//
//                val intent = Intent(this, HomeAcvitity::class.java)   //新加的 编辑完返回主页
//                finish()
//                //startActivity(intent)
//
//                check_diary_title.setText("")
//                check_diary_content.setText("")
            }
        }

    }

//    override fun onClick(v: View?){
//        when(v?.id){
//            R.id.delete -> {
//                AlertDialog.Builder(this).apply{
//                    setTitle("提示")
//                    setMessage(("确认要删除这篇日记吗？"))
//                    setCancelable(false)
//                    setPositiveButton("确认"){dialog,which ->
//                        run {
//                            db.delete("Book", "title = ?", arrayOf("$accept_title"))
//                        }
//                    }
//                    setNegativeButton("取消"){dialog,which ->}
//                    show()
//                }
//            }
//        }
//    }
}