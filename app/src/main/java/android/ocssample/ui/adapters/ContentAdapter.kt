package android.ocssample.ui.adapters

import android.app.Activity
import android.ocssample.R
import android.ocssample.models.Content
import android.ocssample.ui.ContentActivity
import android.ocssample.utils.activityUtilities
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ContentAdapter(private val context: Activity) :
    RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {

    private val tag = "ContentAdapter"
    var contents: List<Content>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentViewHolder {
        return ContentViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_content,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = contents?.size ?: 0

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        var fullImageUrl: String? = null
        contents?.get(position)?.imageurl?.let {
            fullImageUrl = activityUtilities.absoluteImgSource(it)
        }
        holder.apply {
            fullImageUrl?.let {
                Glide.with(context)
                    .load(it)
                    .fallback(R.drawable.fallback_glide)
                    .into(contentImageView)
            }
            contents?.get(position)?.title?.get(0)?.let {
                contentTextView.text = it.value
            }
            itemContentContainer.setOnClickListener {
                ContentActivity.startActivityBundle(contents?.get(position), context)
            }
        }
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contentImageView: ImageView = this.itemView.findViewById(R.id.thumbnail_image_view)
        val contentTextView: TextView = this.itemView.findViewById(R.id.content_text_view)
        val itemContentContainer: View = this.itemView.findViewById(R.id.item_content_container)
    }
}