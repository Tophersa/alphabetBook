package com.cscmdd.alphabetbook

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageSwitcher
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

//import android.widget.ViewSwitcher

class LetterActivity : AppCompatActivity() {

    //private val images = intArrayOf(R.drawable.ic_launcher_background,
    //R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background)
    private val images = intArrayOf(
        R.drawable.slide01, R.drawable.slide02, R.drawable.slide03,
        R.drawable.slide04, R.drawable.slide05, R.drawable.slide06, R.drawable.slide07,
        R.drawable.slide08, R.drawable.slide09, R.drawable.slide10, R.drawable.slide11,
        R.drawable.slide12, R.drawable.slide13, R.drawable.slide14, R.drawable.slide15,
        R.drawable.slide16, R.drawable.slide17, R.drawable.slide18, R.drawable.slide19,
        R.drawable.slide20, R.drawable.slide21, R.drawable.slide22, R.drawable.slide23,
        R.drawable.slide24, R.drawable.slide25, R.drawable.slide26
    )
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter_page)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Letter"

        val next = findViewById<ImageButton>(R.id.buttonNext)
        val prev = findViewById<ImageButton>(R.id.buttonPrev)
        val firstPage = findViewById<Button>(R.id.buttonFirst)
        val lastPage = findViewById<Button>(R.id.buttonLast)
        val overviewPage = findViewById<Button>(R.id.buttonOverview)
        val imageSwitcher = findViewById<ImageSwitcher>(R.id.imageSwitcher)

        val clickedPosition = intent.extras?.get("POSITION") as Int
        position = clickedPosition
        imageSwitcher?.setFactory {
            val imageView = ImageView(applicationContext)
            imageView
        }

        // set the method and pass array as a parameter
        imageSwitcher?.setImageResource(images[position])

        // previous button functionality
        prev.setOnClickListener {
            position -= 1
            if (position == 0) {
                imageSwitcher?.setImageResource(images[position])

                prev.visibility = View.INVISIBLE
                firstPage.isEnabled = false

                next.visibility = View.VISIBLE
                lastPage.isEnabled = true
            } else if (position in 0..25) {
                imageSwitcher?.setImageResource(images[position])

                prev.visibility = View.VISIBLE
                firstPage.isEnabled = true

                next.visibility = View.VISIBLE
                lastPage.isEnabled = true
            }
        }
        // next button functionality
        next.setOnClickListener {
            position += 1
            if (position == images.size - 1) {
                imageSwitcher?.setImageResource(images[position])

                next.visibility = View.INVISIBLE
                lastPage.isEnabled = false

                prev.visibility = View.VISIBLE
                firstPage.isEnabled = true
            } else if (position in 0..25) {
                imageSwitcher?.setImageResource(images[position])

                prev.visibility = View.VISIBLE
                firstPage.isEnabled = true

                next.visibility = View.VISIBLE
                lastPage.isEnabled = true
            }
        }

        // first Page button functionality
        firstPage.setOnClickListener {
            position = 0
            imageSwitcher?.setImageResource(images[position])

            prev.visibility = View.INVISIBLE
            firstPage.isEnabled = false

            next.visibility = View.VISIBLE
            lastPage.isEnabled = true
        }

        // last Page button functionality
        lastPage.setOnClickListener {
            position = images.size - 1
            imageSwitcher?.setImageResource(images[position])

            next.visibility = View.INVISIBLE
            lastPage.isEnabled = false

            prev.visibility = View.VISIBLE
            firstPage.isEnabled = true
        }

        // overview Page button functionality
        overviewPage.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        if (position == 0) {
            prev.visibility = View.INVISIBLE
        } else {
            prev.visibility = View.VISIBLE
        }
        if (position == images.size - 1) {
            next.visibility = View.INVISIBLE
        } else {
            next.visibility = View.VISIBLE
        }

        firstPage.isEnabled = position != 0
        lastPage.isEnabled = position != images.size - 1
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("POSITION", position)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        position=  savedInstanceState.getInt("POSITION", 0)
        val next = findViewById<ImageButton>(R.id.buttonNext)
        val prev = findViewById<ImageButton>(R.id.buttonPrev)
        val firstPage = findViewById<Button>(R.id.buttonFirst)
        val lastPage = findViewById<Button>(R.id.buttonLast)
        val overviewPage = findViewById<Button>(R.id.buttonOverview)
        val imageSwitcher = findViewById<ImageSwitcher>(R.id.imageSwitcher)
        imageSwitcher?.setImageResource(images[position])
        if (position == 0) {
            prev.visibility = View.INVISIBLE
        } else {
            prev.visibility = View.VISIBLE
        }
        if (position == images.size - 1) {
            next.visibility = View.INVISIBLE
        } else {
            next.visibility = View.VISIBLE
        }

        firstPage.isEnabled = position != 0
        lastPage.isEnabled = position != images.size - 1
    }
}