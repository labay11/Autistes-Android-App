package com.alm.autistes

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var buttonNext: Button
    private lateinit var preferences: SharedPreferences

    private var name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = getSharedPreferences(MainActivity::class.java.name, Context.MODE_PRIVATE)
        name = preferences.getString("name", "") ?: ""

        editTextName = findViewById(R.id.edittext_name)
        buttonNext = findViewById(R.id.button_say_hello)

        editTextName.setText(name)

        buttonNext.setOnClickListener {
            if (editTextName.text != null && editTextName.text.toString().isNotBlank()) {
                name = editTextName.text.toString()
                preferences.edit().putString("name", name).apply()
                val intent = SecondaryActivity.createIntent(this, name)
                startActivity(intent)
            } else
                Toast.makeText(this, R.string.alert_no_name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            createAboutDialog()
            return true
        }

        return false
    }

    private fun createAboutDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.about)
            .setMessage("This app was created by ALM.")
            .setIcon(android.R.drawable.ic_menu_info_details)
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }
}