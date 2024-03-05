package com.example.messagecredits

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.messagecredits.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.add100.setOnClickListener {
            binding.amountAdder.setText("100")
        }
        binding.add500.setOnClickListener {
            binding.amountAdder.setText("500")
        }
        binding.add1000.setOnClickListener {
            binding.amountAdder.setText("1000")
        }
        binding.add10000.setOnClickListener {
            binding.amountAdder.setText("10000")
        }
        binding.addCreditsButton.setOnClickListener {
            showAlertDialog(binding.amountAdder.text.toString())
        }

    }
    fun showAlertDialog(amount:String){
        val dialogBuilder=AlertDialog.Builder(this)
        val inflater=this.layoutInflater
        val dialogView=inflater.inflate(R.layout.pay_now_dialog,null)
        dialogBuilder.setView(dialogView)
        val amountText=dialogView.findViewById<TextView>(R.id.amountText)
        val newAmountText=dialogView.findViewById<TextView>(R.id.newAmountText)
        val payNowButton=dialogView.findViewById<TextView>(R.id.payNowButton)
        val cancel=dialogView.findViewById<ImageView>(R.id.cancelIcon)
        amountText.setText("You have a one Pending Transaction for ₹$amount Created on 01 Mar,2014")
        newAmountText.setText("if you still want to create a new payment, Please click on a pay now. Your Pending Transaction will be automatically cancelled")
        val alertDialog=dialogBuilder.create()
        payNowButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://biz.vamosys.com/api/payment/zoho/createPaymentIntent"))
            startActivity(intent)
            alertDialog.dismiss()
        }
        cancel.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.show()
    }
}