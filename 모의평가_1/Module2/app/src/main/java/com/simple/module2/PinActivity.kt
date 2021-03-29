package com.simple.module2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.simple.module2.databinding.ActivityPinBinding

class PinActivity : AppCompatActivity()  {

    private lateinit var pinBinding : ActivityPinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        pinBinding = ActivityPinBinding.inflate(layoutInflater)
        setContentView(pinBinding.root)


        updateWidget()
    }
    private fun updateWidget() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count >= 1) {
                    pinBinding.pin2.requestFocus()
                }else{
                    pinBinding.pin1.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        }

        val textWatcher2 = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count >= 1) {
                    pinBinding.pin3.requestFocus()
                }else{
                    pinBinding.pin1.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        }

        val textWatcher3 = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count >= 1) {
                    pinBinding.pin4.requestFocus()
                }else{
                    pinBinding.pin2.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        }

        val textWatcher4 = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count == 0) {
                    pinBinding.pin3.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        }



        pinBinding.pin1.addTextChangedListener(textWatcher)
        pinBinding.pin2.addTextChangedListener(textWatcher2)
        pinBinding.pin3.addTextChangedListener(textWatcher3)
        pinBinding.pin4.addTextChangedListener(textWatcher4)


    }
}