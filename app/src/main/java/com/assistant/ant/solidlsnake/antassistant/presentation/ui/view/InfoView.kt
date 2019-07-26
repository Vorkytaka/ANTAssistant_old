package com.assistant.ant.solidlsnake.antassistant.presentation.ui.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.v4.widget.ImageViewCompat
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.dpToPx


class InfoView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val linearLayout: LinearLayout
    private val innerLinearLayout: LinearLayout
    private val icon: ImageView
    private val title: TextView
    private val value: TextView

    /**
     * Инициализация внутренних View
     */
    init {
        linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.HORIZONTAL

        icon = ImageView(context)
        linearLayout.addView(icon)

        innerLinearLayout = LinearLayout(context)
        innerLinearLayout.orientation = LinearLayout.VERTICAL

        title = TextView(context)
        innerLinearLayout.addView(title)

        value = TextView(context)
        innerLinearLayout.addView(value)

        linearLayout.addView(innerLinearLayout)

        addView(linearLayout)
    }

    /**
     * Работа с аттрибутами
     */
    init {
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.InfoView, 0, 0)

        try {
            val iconSrc = attributes.getResourceId(R.styleable.InfoView_iconSrc, 0)
            val iconTint = attributes.getColor(R.styleable.InfoView_iconTint, Color.BLACK)
            val titleString = attributes.getString(R.styleable.InfoView_title)
            val valueString = attributes.getString(R.styleable.InfoView_value)

            setIcon(iconSrc)
            setIconTint(iconTint)
            setTitle(titleString!!)
            setValue(valueString)
        } finally {
            attributes.recycle()
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        linearLayout.apply {
            val padding = 10.dpToPx()
            setPadding(padding, padding, padding, padding)
        }

        icon.apply {
            val lp = layoutParams as LinearLayout.LayoutParams
            val size = 25.dpToPx()
            val marginStart = 5.dpToPx()
            lp.width = size
            lp.height = size
            lp.gravity = Gravity.CENTER_VERTICAL
            lp.leftMargin = marginStart
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                lp.marginStart = marginStart
            }
            layoutParams = lp
        }

        val textMarginStart = 20.dpToPx()

        title.apply {
            typeface = Typeface.create("sans-serif-light", Typeface.NORMAL)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)

            val lp = layoutParams as LinearLayout.LayoutParams
            lp.leftMargin = textMarginStart
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                lp.marginStart = textMarginStart
            }
            layoutParams = lp
        }

        value.apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)

            val lp = layoutParams as LinearLayout.LayoutParams
            lp.leftMargin = textMarginStart
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                lp.marginStart = textMarginStart
            }
            layoutParams = lp
        }

    }

    fun setTitle(title: String) {
        this.title.text = title
    }

    fun setValue(value: String?) {
        this.value.text = if (value.isNullOrBlank()) {
            "—"
        } else {
            value
        }
    }

    fun setIcon(@DrawableRes resId: Int) {
        if (resId == 0) {
            icon.visibility = View.GONE
        } else {
            icon.visibility = View.VISIBLE
            icon.setImageResource(resId)
        }
    }

    fun setIconTint(color: Int) {
        ImageViewCompat.setImageTintList(icon, ColorStateList.valueOf(color))
    }
}