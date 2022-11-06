package com.maksmesh.implicitintents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import com.maksmesh.implicitintents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openWebSite()
        openLocation()
        shareText()
    }

    private fun openWebSite() = with(binding) {
        openWebsiteBTN.setOnClickListener {
            val url = webSiteEditText.text.toString()
            val webpage = Uri.parse("https://$url")
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(intent)
        }
    }

    private fun openLocation() = with(binding) {
        locationBTN.setOnClickListener {
            val location = locationEditText.text.toString()
            val addressUri = Uri.parse("geo:0,0?q=$location")
            val intent = Intent(Intent.ACTION_VIEW, addressUri)
            startActivity(intent)
        }
    }

    private fun shareText() = with(binding) {
        shareBTN.setOnClickListener {
            val txt = shareEditText.text.toString()
            val mimeType = "text/plain"
            ShareCompat
                .IntentBuilder(this@MainActivity)
                .setType(mimeType)
                .setChooserTitle(R.string.share_text_header)
                .setText(txt)
                .startChooser()
        }
    }
}