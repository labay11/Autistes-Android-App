package com.alm.autistes.views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.alm.autistes.MainActivity
import com.alm.autistes.R
import com.alm.autistes.SecondaryActivity

class HomeFragment : Fragment() {

    private lateinit var editTextName: EditText
    private lateinit var buttonNext: Button
    private lateinit var preferences: SharedPreferences

    private var name: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = activity!!.getSharedPreferences(MainActivity::class.java.name, Context.MODE_PRIVATE)
        name = preferences.getString("name", "") ?: ""

        editTextName = view.findViewById(R.id.edittext_name)
        buttonNext = view.findViewById(R.id.button_say_hello)

        editTextName.setText(name)

        buttonNext.setOnClickListener {
            if (editTextName.text != null && editTextName.text.toString().isNotBlank()) {
                name = editTextName.text.toString()
                preferences.edit().putString("name", name).apply()
                val intent = SecondaryActivity.createIntent(activity!!, name)
                startActivity(intent)
            } else
                Toast.makeText(activity, R.string.alert_no_name, Toast.LENGTH_SHORT).show()
        }
    }
}