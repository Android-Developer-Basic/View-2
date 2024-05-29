package otus.gbp.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity.CENTER
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.util.TypedValueCompat.dpToPx
import androidx.core.util.TypedValueCompat.pxToDp
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
        initPanel(attrs, defStyleAttr)
        initLikes()
    }

    private fun initPanel(attrs: AttributeSet?, defStyleAttr: Int) {
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        orientation = HORIZONTAL
        gravity = CENTER
        setPadding(
            0,
            dpToPx(8),
            0,
            dpToPx(8)
        )
        setBackgroundResource(R.drawable.like_bg)
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