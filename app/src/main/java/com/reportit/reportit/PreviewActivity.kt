package com.reportit.reportit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        backArrow.setOnClickListener(){
            var mainint = Intent(this,MainActivity::class.java)
            startActivity(mainint)
        }
    }

    /*override fun onBackPressed() {
        //super.onBackPressed()
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    */
}
