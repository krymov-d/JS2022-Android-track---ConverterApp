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

    private var isDefaultUser: Boolean = true
    private var radiusRound = 18f
    private var radiusSharp = 0f
    private var colorGreen =
        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.ui_05))
    private var colorWhite =
        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.ui_02))


    private lateinit var shapeAppearanceModel: ShapeAppearanceModel
    private lateinit var shapeDrawable: MaterialShapeDrawable

    init {
        setBackgroundType(attrs)
        setBackgroundShape()
        setBackgroundFill()
    }

    private fun setBackgroundType(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomTVMessage,
            0, 0
        ).apply {
            try {
                isDefaultUser = getBoolean(R.styleable.CustomTVMessage_defaultUser, true)
            } finally {
                recycle()
            }
        }
    }

    private fun setBackgroundShape() {
        shapeAppearanceModel = if (isDefaultUser) {
            ShapeAppearanceModel().toBuilder().apply {
                setBottomLeftCorner(CornerFamily.ROUNDED, radiusRound)
                setTopLeftCorner(CornerFamily.ROUNDED, radiusRound)
                setTopRightCorner(CornerFamily.ROUNDED, radiusRound)
                setBottomRightCorner(CornerFamily.CUT, radiusSharp)
            }.build()
        } else {
            ShapeAppearanceModel().toBuilder().apply {
                setBottomRightCorner(CornerFamily.ROUNDED, radiusRound)
                setTopLeftCorner(CornerFamily.ROUNDED, radiusRound)
                setTopRightCorner(CornerFamily.ROUNDED, radiusRound)
                setBottomLeftCorner(CornerFamily.CUT, radiusSharp)
            }.build()
        }
    }

    private fun setBackgroundFill() {
        if (isDefaultUser) {
            shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
                fillColor = colorGreen
            }
        } else {
            shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
                fillColor = colorWhite
            }
        }
        background = shapeDrawable
    }
}