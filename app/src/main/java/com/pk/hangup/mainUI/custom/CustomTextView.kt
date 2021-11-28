package com.pk.hangup.mainUI.custom

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.pk.hangup.R

class CustomTextView : AppCompatTextView {
    private var textFont: String? = ""
    constructor(context: Context) : super(context) {
        setFont()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
        textFont = ta.getString(R.styleable.CustomView_font)
        setFont()
        ta.recycle()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
        textFont = ta.getString(R.styleable.CustomView_font)
        setFont()
        ta.recycle()
    }

    fun setFont(font: String?) {
        textFont = font
        setFont()
    }

    private fun setFont() {
        if (textFont != null) {
            if (textFont.equals(context.getString(R.string.roboto_bold), ignoreCase = true)) {
                val normal =
                    Typeface.createFromAsset(context.assets, "fonts/roboto/robotoslab_bold.ttf")
                setTypeface(normal, Typeface.BOLD)
            } else if (textFont.equals(
                    context.getString(R.string.roboto_regular),
                    ignoreCase = true
                )
            ) {
                val normal =
                    Typeface.createFromAsset(context.assets, "fonts/roboto/robotoslab_regular.ttf")
                setTypeface(normal, Typeface.NORMAL)
            } else if (textFont.equals(
                    context.getString(R.string.roboto_italic),
                    ignoreCase = true
                )
            ) {
                val normal =
                    Typeface.createFromAsset(context.assets, "fonts/roboto/roboto_italic.ttf")
                setTypeface(normal, Typeface.ITALIC)
            } else if (textFont.equals(
                    context.getString(R.string.roboto_light),
                    ignoreCase = true
                )
            ) {
                val normal =
                    Typeface.createFromAsset(context.assets, "fonts/roboto/robotoslab_light.ttf")
                setTypeface(normal, Typeface.NORMAL)
            } else if (textFont.equals(
                    context.getString(R.string.roboto_medium),
                    ignoreCase = true
                )
            ) {
                val normal =
                    Typeface.createFromAsset(context.assets, "fonts/roboto/robotoslab_medium.ttf")
                setTypeface(normal, Typeface.NORMAL)
            } else {
                val normal =
                    Typeface.createFromAsset(context.assets, "fonts/roboto/robotoslab_regular.ttf")
                setTypeface(normal, Typeface.NORMAL)
            }
        } else {
            val normal = Typeface.createFromAsset(context.assets, "fonts/roboto/robotoslab_regular.ttf")
            setTypeface(normal, Typeface.NORMAL)
        }
    }
}