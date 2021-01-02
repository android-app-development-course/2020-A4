package com.example.sharedpreferences

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.sharedpreferences.R
import com.example.sharedpreferences.RegisterActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val accountlist = ArrayList<String>()
    private val passwordlist = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this,RegisterActivity::class.java)
        val intent1 = Intent(this, HomeAcvitity::class.java)

        val dbHelper=Acc_Pw_Database(this,"register.db",1)
        val db=dbHelper.writableDatabase
        val cursor = db.query("Acc_Pw",null,null,null,null,null,null)
        if(cursor.moveToFirst()){
            do {
                //遍历Cursor对象，取出账号密码数据
                val acc = cursor.getString(cursor.getColumnIndex("account"))
                val pw = cursor.getString(cursor.getColumnIndex("password"))
                accountlist.add(acc)
                passwordlist.add(pw)
            }while (cursor.moveToNext())
        }

        login.setOnClickListener {
            //注册部分
//            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
//            val account = prefs.getString("account","")
//            val password = prefs.getString("password","")
            //输入部分
            val account2 = login_account.text.toString()
            val password2 = login_password.text.toString()
            var judge = 0

//            if (account == "" && password == ""){
//                Toast.makeText(this,"没有注册请先注册",Toast.LENGTH_SHORT).show()
//            }
//            else if (account == account2 && password == password2){
//                Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show()
//                login_account.setText("")
//                login_password.setText("")
//                judge = 1
//            }
//            else
//            {
//                Toast.makeText(this,"账号密码错误",Toast.LENGTH_SHORT).show()
//            }
//            if (judge==1){
//                startActivity(intent1)
//            }
            if(account2==""&&password2=="")
            {
                Toast.makeText(this,"请输入您的账号和密码",Toast.LENGTH_SHORT).show()
            }
            else if(account2==""&&password2!="")
            {
                Toast.makeText(this,"账号不能为空",Toast.LENGTH_SHORT).show()
            }
            else if(account2!=""&&password2=="")
            {
                Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show()
            }
            else{ //账号密码正确 登录成功、
                for (i in 0..accountlist.size-1)
                {
                    if(account2==accountlist[i])
                    {
                        if(password2==passwordlist[i])  //密码正确
                        {
                            judge=1
                        }
//                        else                            //密码失败
//                            Toast.makeText(this, "密码错误！", Toast.LENGTH_SHORT).show()
                    }
                }

                if (judge==1){
//               intent1.putExtra("account_now",account2)
                    account_now=account2
                    Toast.makeText(this,"登陆成功！",Toast.LENGTH_SHORT).show()
                    startActivity(intent1)
                    finish()
                }
                else
                    Toast.makeText(this,"用户名或密码错误！",Toast.LENGTH_SHORT).show()

            }
//            if (judge==1){
////               intent1.putExtra("account_now",account2)
//                account_now=account2
//                Toast.makeText(this,"登陆成功！",Toast.LENGTH_SHORT).show()
//                startActivity(intent1)
//                finish()
//            }
//            if (judge==0) {
//                Toast.makeText(this, "密码错误！", Toast.LENGTH_SHORT).show()
//            }
        }
        register.setOnClickListener{
            startActivity(intent)
            finish()
        }
    }
    companion object{
        var account_now:String=""
    }
}