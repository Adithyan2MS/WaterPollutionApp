package com.reportit.reportit

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_preview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        val intent=getIntent()

        if (intent.hasExtra("image")){
            val byteArray = intent.getByteArrayExtra("image")
            val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            imageView.setImageBitmap(bmp)
        }
        else if(intent.hasExtra("imagePath")){
            val imgpath= intent.getStringExtra("imagePath")
            val fileUri = Uri.parse(imgpath)
            imageView.setImageURI(fileUri)
        }
        next_btn.setOnClickListener{
            val location=locationEdit.text.toString().trim()
            val description=descriptionEdit.text.toString().trim()

            if(location.isEmpty()){
                locationEdit.error="Location Required"
                locationEdit.requestFocus()
                return@setOnClickListener
            }
            if(description.isEmpty()){
                descriptionEdit.error="Description Required"
                descriptionEdit.requestFocus()
                return@setOnClickListener
            }
            ServiceBuilder.instance.addUser(location,description)
                .enqueue(object:Callback<DataInfo>{
                    override fun onResponse(
                        call: Call<DataInfo>,
                        response: Response<DataInfo>
                    ) {

                    }

                    override fun onFailure(call: Call<DataInfo>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                    }

                })

        }

    }
}
