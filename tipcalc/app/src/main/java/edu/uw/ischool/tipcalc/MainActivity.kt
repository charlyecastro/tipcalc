package edu.uw.ischool.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.*
import android.util.*
import android.view.View
import android.widget.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var percent = .15

        var button = findViewById(R.id.button) as Button
        button.isEnabled = false

        var text = findViewById(R.id.editText) as EditText

        var spin = findViewById(R.id.spin) as Spinner
        var percentages = arrayOf("10%", "15%", "18%", "20%" )
        spin.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,percentages)
        spin.setSelection(1)

        spin.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var selected = percentages[p2]
                when (selected) {
                    "10%" -> percent = .10
                    "15%" -> percent = .15
                    "18%" -> percent = .18
                    else -> { // Note the block
                        percent = .20
                    }
                }
            }

        }

        button.setOnClickListener{
            val input = text.text.toString()
            if(input.contains("$")) {
                text.setText(input.substring(1,input.length));
            }

            val num = text.text.toString().toDouble() * percent
            val toast = Toast.makeText(applicationContext, "$" + "%.2f".format(num), Toast.LENGTH_LONG)
            toast.show()
            text.setText("$" + text.text)

        }

        fun isTextEmpty() {
            if (text.getText().length < 1) {
                button.isEnabled = false
            } else {
                button.isEnabled = true
            }
        }


        text.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                isTextEmpty()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isTextEmpty()
            }
            override fun afterTextChanged(s: Editable?) {
                isTextEmpty()

            }
        })

    }
}