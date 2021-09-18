package com.reportit.reportit

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        var intent=getIntent();

        if (intent.hasExtra("image")){
            val byteArray = intent.getByteArrayExtra("image")
            val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            imageView.setImageBitmap(bmp)
        }
        else if(intent.hasExtra("imagePath")){
            var image_path= intent.getStringExtra("imagePath");
            var fileUri = Uri.parse(image_path)
            imageView.setImageURI(fileUri)
        }
    }
}
