package com.reportit.reportit

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        var intent=getIntent();

        var requestCode=intent.getIntExtra("reqcode",0)

        if(requestCode==123){
            var bitmap : Bitmap? =null
            if (intent.hasExtra("image")){
                //convert to bitmap
                val byteArray = intent.getByteArrayExtra("image")
                bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                imageView.setImageBitmap(bitmap)
            }
        }
        else if(requestCode==456){
            var image_path= intent.getStringExtra("imagePath");

            var fileUri = Uri.parse(image_path)
            imageView.setImageURI(fileUri)
        }


    }
}
