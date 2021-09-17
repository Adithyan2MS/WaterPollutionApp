package com.reportit.reportit

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun takePhoto(view: View) {
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent,123)
        }
    }

    fun pickPhoto(view: View) {
        var intent=Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent,456)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val intent=Intent(this,PreviewActivity::class.java)
        intent.putExtra("reqcode",requestCode)

        var bmp: Bitmap =data?.extras?.get("data") as Bitmap
        var bstream=ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG,50,bstream)
        val byteArray=bstream.toByteArray()
        intent.putExtra("image",byteArray)

        var uri = data!!.data
        intent.putExtra("imagePath", uri.toString())

        startActivity(intent)


        //
        //intent.putExtra("rescode",resultCode)

        //setContentView(R.layout.add_details)
//        if(requestCode==123){
//            var bmp: Bitmap =data?.extras?.get("data") as Bitmap
//            imageView.setImageBitmap(bmp)
//        }else if(requestCode==456){
//            imageView.setImageURI(data?.data)
//        }

    }
}
