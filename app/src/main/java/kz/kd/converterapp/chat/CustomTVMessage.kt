package kz.kd.converterapp.chat

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import kz.kd.converterapp.R

class CustomTVMessage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var defaultText = ""
    private var radiusRound = 18f
    private var radiusSharp = 0f
    private var colorGreen =
        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.ui_05))

    private lateinit var shapeAppearanceModel: ShapeAppearanceModel
    private lateinit var shapeDrawable: MaterialShapeDrawable

    init {
        text = defaultText
        setBackgroundShape()
        setBackgroundFill()
    }

    private fun setBackgroundShape() {
        shapeAppearanceModel = ShapeAppearanceModel().toBuilder().apply {
            setBottomLeftCorner(CornerFamily.ROUNDED, radiusRound)
            setTopLeftCorner(CornerFamily.ROUNDED, radiusRound)
            setTopRightCorner(CornerFamily.ROUNDED, radiusRound)
            setBottomRightCorner(CornerFamily.CUT, radiusSharp)
        }.build()
    }

    private fun setBackgroundFill() {
        shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
            fillColor = colorGreen
        }
        background = shapeDrawable
    }
}