package com.example.calci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var calctxt:TextView? = null
    var lastnumeric: Boolean = false
    var lastdot: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calctxt = findViewById(R.id.calctxt)
    }

    fun onDigit(view: View){
        calctxt?.append((view as Button).text)
        lastnumeric = true
        lastdot = false
    }



    fun onClear(view: View){
        calctxt?.text = ""
    }



    fun onBackspace(view: View){
        val lenght = calctxt?.length()
        if (lenght != null) {
            if (lenght > 0){
                calctxt?.text = calctxt?.text?.subSequence(0,lenght - 1)
            }
        }
    }




    fun onDecimal(view: View){
        if (lastnumeric && !lastdot){
            calctxt?.append(".")
            lastnumeric = false
            lastdot = true
        }
    }




    fun onOperator(view: View){
        calctxt?.text?.let {
            if (lastnumeric&& !isoperatoradded(it.toString())){
                calctxt?.append((view as Button).text)
                lastnumeric = false
                lastdot = false
            }
        }

    }



    fun onEqual(view: View){
        if(lastnumeric){
            var calcvalue = calctxt?.text.toString()
            var prefix = ""

            try {
                if (calcvalue.startsWith("-")){
                    prefix = "-"
                    calcvalue = calcvalue.substring(1)
                }
                if (calcvalue.contains("-")){
                    val splitvalue = calcvalue.split("-")
                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    calctxt?.text = removezeroafterdot((one.toDouble() - two.toDouble()).toString())
                }else if (calcvalue.contains("+")){
                        val splitvalue = calcvalue.split("+")
                        var one = splitvalue[0]
                        var two = splitvalue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        calctxt?.text = removezeroafterdot((one.toDouble() + two.toDouble()).toString())
                    }else if (calcvalue.contains("x")){
                    val splitvalue = calcvalue.split("x")
                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    calctxt?.text = removezeroafterdot((one.toDouble() * two.toDouble()).toString())
                }else if (calcvalue.contains("÷")){
                    val splitvalue = calcvalue.split("÷")
                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    calctxt?.text = removezeroafterdot((one.toDouble() / two.toDouble()).toString())
                }




            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }



    private fun removezeroafterdot(result:String) : String{
        var value = result
        if (result.endsWith(".0"))
            value = result.substring(0,result.length - 2)
        return value
    }


    private fun isoperatoradded(value:String) : Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("÷")
                    ||value.contains("x")
                    ||value.contains("+")
                    ||value.contains("-")
            }
        }
    }
