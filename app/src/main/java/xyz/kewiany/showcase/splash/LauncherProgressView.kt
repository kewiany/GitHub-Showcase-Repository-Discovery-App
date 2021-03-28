package xyz.kewiany.showcase.splash

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import androidx.core.graphics.scale
import xyz.kewiany.showcase.R

class LauncherProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        isAntiAlias = true
        isFilterBitmap = true
    }
    private val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        .scale(BITMAP_SIZE, BITMAP_SIZE)
    private val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    private val leftRect: Float = ((displayMetrics.widthPixels - bitmap.width) / 2).toFloat()
    private val topRect: Float = ((displayMetrics.heightPixels - bitmap.height) / 2).toFloat()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap, leftRect, topRect, paint)
    }

    companion object {
        private const val BITMAP_SIZE = 300
    }
}