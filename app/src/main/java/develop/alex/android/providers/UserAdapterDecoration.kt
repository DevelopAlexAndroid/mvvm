package develop.alex.android.providers

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class UserAdapterDecoration
constructor(
    private var divider: Drawable
) : RecyclerView.ItemDecoration() {

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params =
                child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount;

        val left = 0
        val right = 0

        /** all positions */
        var top = divider.intrinsicHeight * itemPosition
        var bottom: Int = divider.intrinsicHeight * 12
        /** first position */
        if (itemPosition == 0)
            top += 0
        /** last position */
        else if (itemCount > 0 && itemPosition == itemCount - 1)
            bottom += divider.intrinsicHeight * 10

        outRect.top
        outRect.set(left, top, right, bottom)
    }
}