package com.reportit.reportit

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_preview.*
import java.io.FileInputStream
import java.lang.Exception

class PreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        var intent=getIntent();

        if (intent.hasExtra("image")){
            //convert to bitmap
            val byteArray = intent.getByteArrayExtra("image")
            val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

            imageView.setImageBitmap(bmp)

        }


        var image_path= intent.getStringExtra("imagePath");
        var fileUri = Uri.parse(image_path)
        if(fileUri!=null){
            imageView.setImageURI(fileUri)

        }

    }
}
