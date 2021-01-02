package com.example.sharedpreferences

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.friends.*

class FriendsActivity : AppCompatActivity() {

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.friends)
        val intent_home = Intent(this, HomeAcvitity::class.java)
        val intent_friends = Intent(this, FriendsActivity::class.java)
        val intent_mine = Intent(this, MineActivity::class.java)


        friends_home.setOnClickListener {
            startActivity(intent_home)
        }
        friends_mine.setOnClickListener {
            startActivity(intent_mine)
        }
    }
}