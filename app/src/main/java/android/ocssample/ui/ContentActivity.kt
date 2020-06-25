package android.ocssample.ui

import android.app.Activity
import android.content.Intent
import android.ocssample.R
import android.ocssample.models.Content
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

class ContentActivity : AppCompatActivity() {
    lateinit var content: Content
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            content = it.getSerializable(CONTENT_BUNDLE) as Content
        }
        setContentView(R.layout.content_activity)
    }

    companion object {
        fun startActivityBundle(content: Content?, context: Activity) {
            val intent = Intent(context, ContentActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val contentBundle = Bundle()
            contentBundle.putSerializable(CONTENT_BUNDLE, content)
            startActivity(context, intent, contentBundle)
        }

        private const val CONTENT_BUNDLE = "CONTENT_BUNDLE"
    }
}
