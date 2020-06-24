package android.ocssample.ui.adapters

import android.content.Context
import android.ocssample.R
import android.ocssample.models.Content
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ContentAdapter(private val context: Context) :
    RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {
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
        var absoluteImageUrl: String? = null
        contents?.get(position)?.imageurl.let {
            absoluteImageUrl = baseImgUrl + it
        }
        holder.apply {
            absoluteImageUrl?.let {
                Glide.with(context)
                    .load(it)
                    .fallback(R.drawable.fallback_glide)
                    .into(contentImageView)

            }
            contents?.get(position)?.title?.get(0)?.let {
                contentTextView.text = it.value
            }
        }

    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contentImageView: ImageView = this.itemView.findViewById(R.id.thumbnail_image_view)
        val contentTextView: TextView = this.itemView.findViewById(R.id.content_text_view)
    }

    companion object {
        const val baseImgUrl = "http://statics.ocs.fr/"
    }
}