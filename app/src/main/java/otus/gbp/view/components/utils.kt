package otus.gbp.view.components

import android.view.View

fun View.dpToPx(dp: Int) = (dp * resources.displayMetrics.density).toInt()