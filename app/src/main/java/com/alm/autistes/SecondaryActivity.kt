package com.alm.autistes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class SecondaryActivity : AppCompatActivity() {

    private lateinit var textViewName: TextView

    companion object {

        var EXTRA_INTENT_TEXT: String = "extra_intent_name"

        fun createIntent(context: Context, text: String): Intent {
            val intent = Intent(context, SecondaryActivity::class.java)
            intent.putExtra(EXTRA_INTENT_TEXT, text)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        textViewName = findViewById(R.id.textview_secondary)
        textViewName.text = intent.getStringExtra(EXTRA_INTENT_TEXT)
    }
}