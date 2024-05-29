package otus.gbp.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import otus.gbp.view.R
import otus.gbp.view.databinding.LikeDislikeBinding

class LikeDislike @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding: LikeDislikeBinding
    private var likes = 0

    init {
        binding = LikeDislikeBinding.inflate(LayoutInflater.from(context), this)
        initLikes()
    }

    private fun initLikes() = with(binding) {
        numLikes.text = likes.toString()
        likeButton.setOnClickListener {
            numLikes.text = (++likes).toString()
        }
        dislikeButton.setOnClickListener {
            numLikes.text = (--likes).coerceAtLeast(0).toString()
        }
    }
}