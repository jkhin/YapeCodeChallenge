package jkcb.dev.labs.yape.test.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import jkcb.dev.labs.yape.test.R

class RecipeItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = parent.resources.getDimension(R.dimen.dp_80).toInt()
            }
            parent.adapter?.itemCount?.let {
                if (parent.getChildAdapterPosition(view) == it - 1) {
                    bottom = parent.resources.getDimension(R.dimen.dp_80).toInt()
                }
            }
        }
    }
}