package com.example.sharedpreferences
import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.setting.*

class setting : AppCompatActivity() {

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.setting)
        //返回
        setting_return.setOnClickListener {
            val intent = Intent(this, MineActivity::class.java)
            startActivity(intent)
        }
        //修改密码
        changepw.setOnClickListener {
            val intent=Intent(this,ChangePassword::class.java)
            startActivity(intent)
        }
    }
}
