package com.reportit.reportit

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_details.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageButton5.setOnClickListener{
            var intent=Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")

            startActivityForResult(intent,456)
        }
    }
    fun takePhoto(view: View){
        val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(packageManager)!=null){
            startActivityForResult(intent,123)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        setContentView(R.layout.add_details)
        if(requestCode==123){
            var bmp: Bitmap =data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(bmp)
        }else if(requestCode==456){
            imageView.setImageURI(data?.data)
        }
    }

}
