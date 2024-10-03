package com.example.customratingbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CustomRatingBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var rating = 0f
    private val starCount = 5
    private val starSize = 150f // Kích thước của sao
    private var selectedStarDrawable: Drawable? = null
    private var unselectedStarDrawable: Drawable? = null

    init {
        // Khởi tạo drawable từ tài nguyên
        selectedStarDrawable = context.getDrawable(R.drawable.star_selected)
        unselectedStarDrawable = context.getDrawable(R.drawable.star_unselected)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val desiredWidth = (starCount * starSize).toInt()
        val desiredHeight = starSize.toInt()
        setMeasuredDimension(desiredWidth, desiredHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (i in 1..starCount) {
            val x = (i - 1) * starSize
            val drawable = if (i <= rating) selectedStarDrawable else unselectedStarDrawable
            drawable?.setBounds(x.toInt(), 0, (x + starSize).toInt(), starSize.toInt())
            drawable?.draw(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val x = event.x
            val selectedStar = (x / starSize).toInt() + 1 // Tính ô được chọn
            setRating(selectedStar.toFloat()) // Cập nhật xếp hạng
            return true
        }
        return super.onTouchEvent(event)
    }

    fun setRating(rating: Float) {
        this.rating = rating.coerceIn(0f, starCount.toFloat())
        invalidate() // Gọi lại onDraw để cập nhật giao diện
    }

    fun getRating(): Float {
        return rating
    }
}
