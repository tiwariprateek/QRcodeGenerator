package com.example.prototype1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.util.Log
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidmads.library.qrgenearator.QRGSaver
import com.google.zxing.Dimension
import com.google.zxing.WriterException
import com.google.zxing.qrcode.encoder.QRCode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val code = qr.text.toString()
            val qrencoder = QRGEncoder(code, null, QRGContents.Type.TEXT, 25 * 25)
            val bitmap = qrencoder.encodeAsBitmap()
            try {

                imageView.setImageBitmap(bitmap)
            } catch (e: WriterException) {
                Log.v("Exeception", "${e.toString()}")

            }
            val directory = Environment.getExternalStorageDirectory().name
            QRGSaver.save(directory, "QRCODE", bitmap, QRGContents.ImageType.IMAGE_JPEG)
        }
    }
}
