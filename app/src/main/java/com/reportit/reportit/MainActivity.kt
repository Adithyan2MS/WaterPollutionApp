package com.reportit.reportit

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageButton4.setOnClickListener {
            var imgCap = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (imgCap.resolveActivity(packageManager) != null) {
                startActivityForResult(imgCap,123)
            }
        }
        imageButton5.setOnClickListener{
            var imgPick=Intent(Intent.ACTION_PICK)
            imgPick.type="image/*"
            startActivityForResult(imgPick,456)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val intent=Intent(this,PreviewActivity::class.java)

        if(requestCode==123){
            var bmp: Bitmap =data?.extras?.get("data") as Bitmap
            val stream = ByteArrayOutputStream()
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()
            intent.putExtra("image", byteArray)
        }
        else if(requestCode==456){
            var Uri = data!!.data
            intent.putExtra("imagePath", Uri.toString())
        }
        startActivity(intent)
    }
}
