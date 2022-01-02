package com.reportit.reportit

import com.reportit.reportit.Retrofit.MyService
import com.reportit.reportit.Retrofit.RetrofitClient
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : AppCompatActivity() {

    lateinit var myService:MyService
    internal var compositeDisposable:CompositeDisposable= CompositeDisposable()

    override fun onStop(){
        compositeDisposable.clear()
        super.onStop()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        val intent = getIntent()

        if (intent.hasExtra("image")) {
            val byteArray = intent.getByteArrayExtra("image")
            val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            imageView.setImageBitmap(bmp)
        } else if (intent.hasExtra("imagePath")) {
            val imgpath = intent.getStringExtra("imagePath")
            val fileUri = Uri.parse(imgpath)
            imageView.setImageURI(fileUri)
        }
        val retrofit=RetrofitClient.getInstance()
        myService=retrofit.create(MyService::class.java)

        nextButton.setOnClickListener {
            storedata(locationEdit.text.toString(),descriptionEdit.text.toString())
        }
    }

    private fun storedata(location: String, description: String) {

        if(TextUtils.isEmpty(location))
        {
            Toast.makeText ( this@PreviewActivity, "location cannot be null or empty", Toast.LENGTH_SHORT).show()
            return
        }

        if(TextUtils.isEmpty(description))
        {
            Toast.makeText ( this@PreviewActivity, "description cannot be null or empty", Toast.LENGTH_SHORT).show()
            return
        }
        compositeDisposable.add(myService.storedata(location,description)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                Toast.makeText(this@PreviewActivity, "" + result, Toast.LENGTH_SHORT).show()

            }
        )
    }

}

