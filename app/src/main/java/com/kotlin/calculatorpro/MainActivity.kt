package com.kotlin.calculatorpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import kotlin.Result

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        One.setOnClickListener { appendExp("1", true) }
        Two.setOnClickListener { appendExp("2", true) }
        Three.setOnClickListener { appendExp("3", true) }
        Four.setOnClickListener { appendExp("4", true) }
        Five.setOnClickListener { appendExp("5", true) }
        Six.setOnClickListener { appendExp("6", true) }
        Seven.setOnClickListener { appendExp("7", true) }
        Eight.setOnClickListener { appendExp("8", true) }
        Nine.setOnClickListener { appendExp("9", true) }
        Zero.setOnClickListener { appendExp("0", true) }
        Dot.setOnClickListener { appendExp(".", true) }

        Plus.setOnClickListener { appendExp("+", false) }
        Minus.setOnClickListener { appendExp("-", false) }
        Multi.setOnClickListener { appendExp("*", false) }
        Divide.setOnClickListener { appendExp("/", false) }
        Open.setOnClickListener { appendExp("[", false) }
        Close.setOnClickListener { appendExp("]", false) }

        Clear.setOnClickListener {
            Expression.text=""
            Result.text=""
        }

        Back.setOnClickListener {
            val string=Expression.text.toString()
            if(string.isNotEmpty()){
                Expression.text=string.substring(0, string.length-1)
            }
            Result.text=""
        }

        Equals.setOnClickListener {
            try {
                val expression=ExpressionBuilder(Expression.text.toString()).build()
                val result=expression.evaluate()
                val longResult=result.toLong()
                if(result==longResult.toDouble())
                    Result.text=longResult.toString()
                else
                    Result.text=result.toString()
            }
            catch (e:Exception){
                Toast.makeText(this, e.message, LENGTH_SHORT).show();
            }
        }
    }

    fun appendExp(string: String, canClear: Boolean){
        if(canClear){
            Result.text=""
            Expression.append(string)
        }else{
            if(Result.text.isEmpty()){
                Expression.append(string)
                Result.text=""
            }else
            {
                Expression.text="";
                Expression.append(Result.text.toString())
                Expression.append(string)
                Result.text=""
            }
        }
    }
}
