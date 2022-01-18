package com.demo.memer

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

import com.android.volley.toolbox.Volley
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*
import myglide.GlideApp


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main)
        nextMeme.toString()
        loadMeme()

    }
    var currentImageUrl:String? =null

    private fun loadMeme(){

// Instantiate the RequestQueue.
        pbar.visibility=View.VISIBLE

        val url = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.
        val JsonRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                // Display the first 500 characters of the response string.
                currentImageUrl =response.getString("url")

                GlideApp.with(this).load(currentImageUrl).listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                       pbar.visibility=View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                       pbar.visibility=View.GONE
                        return false
                    }

                }).into(imageView)

                              },
            Response.ErrorListener { "That didn't work!" })
// Add the request to the RequestQueue.
     Singleton.getInstance(this).addToRequestQueue(JsonRequest)
    }




    fun share(view: View) {

    val intent = Intent(Intent.ACTION_SEND)
    intent.type="text/plain"
    intent.putExtra(Intent.EXTRA_TEXT,"Hey, Checkout this cool Meme $currentImageUrl. App by Prabhjee Singh")
    val chooser = Intent.createChooser(intent,"Share this meme using...")
    startActivity(chooser)
    }


    fun next(view: View){
        loadMeme()
    }
}
