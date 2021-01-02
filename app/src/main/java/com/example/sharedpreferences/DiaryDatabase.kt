package com.example.sharedpreferences

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.LocalDateTime

class DiaryDatabase(val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context,name,null,version){

//    private val createCategory = "create table Category (" +
//            "id integer primary key autoincrement," +
//            "category_name text," +
//            "category_code integer)"

    //表中有id（主键）、标题、内容、时间
    private val createBook = "create table Book (" +
            " id integer primary key autoincrement," +
            "title text," +
            "content text," +
            "account text," +
            "time text)"

    override fun onCreate(db: SQLiteDatabase) {
        //创建数据库的同时创建Book表
        if (db != null) {
            db.execSQL(createBook)
//            Toast.makeText(context, "Create succeeded",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        if (db != null) {
//            db.execSQL("drop table if exists Book")
//        }
//        onCreate(db)
    }

}

