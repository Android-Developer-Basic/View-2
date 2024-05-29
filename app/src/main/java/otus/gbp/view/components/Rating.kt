package otus.gbp.view.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.res.getColorOrThrow
import androidx.core.content.res.getIntOrThrow
import androidx.core.content.withStyledAttributes
import otus.gbp.view.R
import kotlin.math.ceil

class Rating@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.ratingStyle
) : View(context, attrs, defStyleAttr) {
    private var count = DEFAULT_COUNT
    private var value = DEFAULT_VALUE
    private var stroke = DEFAULT_STROKE_WIDTH
    private lateinit var emptyPaint: Paint
    private lateinit var filledPaint: Paint

    init {
        initRating(attrs, defStyleAttr)
    }

    private fun initRating(attrs: AttributeSet?, defStyleAttr: Int) {
        context.withStyledAttributes(attrs, R.styleable.Rating, defStyleAttr, R.style.rating) {
            count = getIntOrThrow(R.styleable.Rating_rating_count)
            value = getIntOrThrow(R.styleable.Rating_rating_value)
            stroke = getDimensionPixelSize(R.styleable.Rating_rating_stroke_width, DEFAULT_STROKE_WIDTH)
            emptyPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                style = Paint.Style.STROKE
                strokeWidth = stroke.toFloat()
                color = getColorOrThrow(R.styleable.Rating_rating_empty_color)
            }
            filledPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                style = Paint.Style.FILL
                color = getColorOrThrow(R.styleable.Rating_rating_filled_color)
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        repeat(count) {
            canvas.drawCircle(
                dpToPx(SIZE + stroke).toFloat(),
                (height / 2).toFloat(),
                dpToPx(SIZE - stroke / 2).toFloat(),
                if (it < value) filledPaint else emptyPaint
            )
            canvas.translate(dpToPx(SIZE * 2 + DISTANCE).toFloat(), 0f)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.i(TAG, "onMeasure")
        Log.i(TAG, "Width.size = ${printMeasureSize(widthMeasureSpec)}")
        Log.i(TAG, "Width.mode = ${printMeasureMode(widthMeasureSpec)}")
        Log.i(TAG, "Height.size = ${printMeasureSize(heightMeasureSpec)}")
        Log.i(TAG, "Height.mode = ${printMeasureMode(heightMeasureSpec)}")

        val desiredWidth = count * (dpToPx(SIZE) * 2 + DISTANCE + dpToPx(DISTANCE)) - DISTANCE
        val desiredHeight = dpToPx(SIZE) * 2
        setMeasuredDimension(
            resolveSize(desiredWidth, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec)
        )
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event);

        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                val relativeTouch = ceil(event.x / (width/count)).toInt()
                Log.i(TAG, "Touched at: ${event.x}")
                Log.i(TAG, "Touched at relative: $relativeTouch")
                value = relativeTouch
                invalidate()
                return true
            }
        }
        return false
    }

    companion object {
        private const val DEFAULT_COUNT = 5
        private const val DEFAULT_VALUE = 0
        private const val DEFAULT_STROKE_WIDTH = 1
        private const val DISTANCE = 8
        private const val SIZE = 24
        private const val TAG = "Rating"
    }
}