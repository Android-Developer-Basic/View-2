package otus.gbp.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity.CENTER
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.withStyledAttributes
import androidx.core.util.TypedValueCompat.dpToPx
import androidx.core.util.TypedValueCompat.pxToDp
import com.google.android.material.button.MaterialButton
import otus.gbp.view.R
import otus.gbp.view.databinding.LikeDislikeBinding

class LikeDislike @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.like_dislike_panelStyle
) : LinearLayoutCompat(context, attrs, defStyleAttr) {
    private val binding: LikeDislikeBinding
    private var likes = 0

    init {
        binding = LikeDislikeBinding.inflate(LayoutInflater.from(context), this)
        initPanel(attrs, defStyleAttr)
        initLikes(attrs, defStyleAttr)
    }

    private fun initPanel(attrs: AttributeSet?, defStyleAttr: Int) {
        // for android limitation, before finding attrs, you need to sort all attrs you want to find in ascending order, otherwise some attrs cannot be found
        // https://developer.android.com/reference/android/content/res/Resources.Theme.html#obtainStyledAttributes(android.util.AttributeSet, int[], int, int)
        val toRetrieve = intArrayOf(
            android.R.attr.layout_width,
            android.R.attr.layout_height,
            android.R.attr.orientation,
            android.R.attr.gravity,
            android.R.attr.paddingTop,
            android.R.attr.paddingBottom,
            android.R.attr.background
        ).apply { sort() }

        context.withStyledAttributes(attrs, toRetrieve, defStyleAttr, R.style.like_dislike_panel) {
            layoutParams = LayoutParams(
                getInt(toRetrieve.indexOf(android.R.attr.layout_width), LayoutParams.WRAP_CONTENT),
                getInt(toRetrieve.indexOf(android.R.attr.layout_height), LayoutParams.WRAP_CONTENT),
            )
            orientation = getInt(toRetrieve.indexOf(android.R.attr.orientation), HORIZONTAL)
            gravity = getInt(toRetrieve.indexOf(android.R.attr.gravity), CENTER)
            setPadding(
                0,
                getDimensionPixelSize(toRetrieve.indexOf(android.R.attr.paddingTop), 0),
                0,
                getDimensionPixelSize(toRetrieve.indexOf(android.R.attr.paddingBottom), 0),
            )
            background = getDrawable(toRetrieve.indexOf(android.R.attr.background))
        }
    }

    private fun initLikes(attrs: AttributeSet?, defStyleAttr: Int) = with(binding) {
        context.withStyledAttributes(attrs, R.styleable.LikeDislike, defStyleAttr, R.style.like_dislike_panel) {
            likes = getInt(R.styleable.LikeDislike_like_dislike_count, 0)
            numLikes.text = likes.toString()
            likeButton.setOnClickListener {
                numLikes.text = (++likes).toString()
            }
            dislikeButton.setOnClickListener {
                numLikes.text = (--likes).coerceAtLeast(0).toString()
            }
        }
    }
}