package edu.temple.browsr

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : AppCompatActivity(), ControlFragment.ControlFragmentListener {

    private lateinit var pageFragment: PageFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enable edge-to-edge display
        enableEdgeToEdge()

        // Dynamically add PageFragment
        if (savedInstanceState == null) {
            pageFragment = PageFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.page, pageFragment)
                .commit()
        }

        // Attach ControlFragment listener
        val controlFragment = supportFragmentManager.findFragmentById(R.id.control) as ControlFragment
        controlFragment.setControlFragmentListener(this)

        // Customize the system UI for edge-to-edge display
        setupEdgeToEdge()
    }

    override fun onGoClicked(url: String) {
        var formattedUrl = url
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            formattedUrl = if (url.contains(".")) {
                "http://$url"
            } else {
                "https://duckduckgo.com/?q=$url"
            }
        }
        pageFragment.getWebView().loadUrl(formattedUrl)
    }

    override fun onNextClicked() {
        if (pageFragment.getWebView().canGoForward()) {
            pageFragment.getWebView().goForward()
        }
    }

    override fun onBackClicked() {
        if (pageFragment.getWebView().canGoBack()) {
            pageFragment.getWebView().goBack()
        }
    }

    private fun setupEdgeToEdge() {
        // Hide the system bars (status bar, navigation bar) for an immersive experience
        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)
        windowInsetsController?.apply {
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            hide(WindowInsetsCompat.Type.systemBars())
        }
    }
}
