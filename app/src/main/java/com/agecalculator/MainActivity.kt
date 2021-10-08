package com.agecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.agecalculator.userinterface.captureuserinput.TAG_USER_INPUT_FRAGMENT
import com.agecalculator.userinterface.captureuserinput.UserInputFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, UserInputFragment())
                .commit()
        }
    }
}