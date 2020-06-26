package android.ocssample.ui

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.ocssample.R
import android.ocssample.models.Content
import android.ocssample.utils.activityUtilities
import android.ocssample.viewModels.ContentViewModel
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.content_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ContentActivity : AppCompatActivity() {
    lateinit var content: Content
    lateinit var contentViewModel: ContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extractBundle()
        setContentView(R.layout.content_activity)
        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel::class.java)
        updateContentUI()
    }

    private fun extractBundle() {
        intent.extras?.let {
            content = it.getSerializable(CONTENT_BUNDLE) as Content
            Log.i("ContentActivity", "Extracting Bundle$content")
        }
    }

    private fun updateContentUI() {
        content?.fullscreenimageurl.let {
            Log.i("content activity", "$it")
            Log.i("content activity", "${activityUtilities.absoluteImgSource(it)}")
            Glide.with(this)
                .load(activityUtilities.absoluteImgSource(it))
                .fallback(R.drawable.fallback_glide)
                .listener(imageContentListener())
                .into(image_view_content)
        }
        content?.title?.let {
            content_title.text = it[0].value
        }
        content?.subtitle?.let {
            content_subtitle.text = it
        }
        content?.detaillink?.let {
            Log.i("ContentActivity", "detaillink $it")
            CoroutineScope(IO).launch {
                contentViewModel.getDetailLinkCoroutine(it)
            }
        }
        contentViewModel.pitchLiveData.observe(this, Observer {
            it?.let {
                content_pitch.text = it
            }
        })
    }

    private fun imageContentListener(): RequestListener<Drawable> {
        return object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                content_img_prog.visibility = View.GONE;
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                content_img_prog.visibility = View.GONE;
                content_play_icon.visibility = View.VISIBLE
                return false
            }
        }
    }

    companion object {
        fun startActivityBundle(content: Content?, context: Activity) {
            Log.i("contentActivity", "$content")
            val intent = Intent(context, ContentActivity::class.java)
            val contentBundle = Bundle()
            contentBundle.putSerializable(CONTENT_BUNDLE, content)
            intent.putExtra(CONTENT_BUNDLE, content)
            context.startActivity(intent)
        }

        private const val CONTENT_BUNDLE = "CONTENT_BUNDLE"
    }
}
