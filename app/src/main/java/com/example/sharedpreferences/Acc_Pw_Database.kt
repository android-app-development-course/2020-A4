package com.example.sharedpreferences

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class Acc_Pw_Database (val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context,name,null,version){
    private val createAcc_Pw="create table Acc_Pw (" +
            " account text primary key NOT NULL," +
            " password text NOT NULL,"+
            " nickname text,"+
            "signature text NOT NULL)"
    override fun onCreate(db: SQLiteDatabase) {
        //创建数据库的同时创建 账号密码表
        if(db!=null){
            db.execSQL(createAcc_Pw)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

}