package io.skrape.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private var title: TextView? = null
    private var items: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = findViewById(R.id.title)
        items = findViewById(R.id.items)

        start()
        observeChanges()
    }

    private fun start() {
        val htmlFile = resources.openRawResource(R.raw.sample)
        val fileContent = BufferedReader(htmlFile.reader()).readLines().joinToString()
        mainViewModel.parse(fileContent)
    }

    private fun observeChanges() {
        mainViewModel.title.observe(this, { htmlTitle ->
            title?.text = htmlTitle
        })
        mainViewModel.items.observe(this, { htmlItems ->
            htmlItems
                .map { getString(R.string.bullet_format, it) }
                .joinToString("\n")
                .let { allItemsFormatted ->
                    items?.text = allItemsFormatted
                }
        })
    }
}
