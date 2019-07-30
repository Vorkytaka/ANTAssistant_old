package com.assistant.ant.solidlsnake.antassistant.presentation.ui.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
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

    public var title: String = ""
        set(value) {
            this._title.text = value
            field = value
        }

    private val _linearLayout: LinearLayout
    private val _innerLinearLayout: LinearLayout
    private val _icon: ImageView
    private val _title: TextView
    private val _value: TextView

    /**
     * Инициализация внутренних View
     */
    init {
        _linearLayout = LinearLayout(context)
        _linearLayout.orientation = LinearLayout.HORIZONTAL

        _icon = ImageView(context)
        _linearLayout.addView(_icon)

        _innerLinearLayout = LinearLayout(context)
        _innerLinearLayout.orientation = LinearLayout.VERTICAL

        _title = TextView(context)
        _innerLinearLayout.addView(_title)

        _value = TextView(context)
        _innerLinearLayout.addView(_value)

        _linearLayout.addView(_innerLinearLayout)

        addView(_linearLayout)
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
            title = titleString!!
            setValue(valueString)
        } finally {
            attributes.recycle()
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        _linearLayout.apply {
            val padding = 10.dpToPx()
            setPadding(padding, padding, padding, padding)
        }

        _icon.apply {
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

        _title.apply {
            typeface = Typeface.create("sans-serif-light", Typeface.NORMAL)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)

            val lp = layoutParams as LinearLayout.LayoutParams
            lp.leftMargin = textMarginStart
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                lp.marginStart = textMarginStart
            }
            layoutParams = lp
        }

        _value.apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)

            val lp = layoutParams as LinearLayout.LayoutParams
            lp.leftMargin = textMarginStart
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                lp.marginStart = textMarginStart
            }
            layoutParams = lp
        }

    }

    fun setValue(value: String?) {
        this._value.text = if (value.isNullOrBlank()) {
            "—"
        } else {
            value
        }
    }

    fun setIcon(@DrawableRes resId: Int) {
        if (resId == 0) {
            _icon.visibility = View.GONE
        } else {
            _icon.visibility = View.VISIBLE
            _icon.setImageResource(resId)
        }
    }

    fun setIconTint(color: Int) {
        ImageViewCompat.setImageTintList(_icon, ColorStateList.valueOf(color))
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        super.setOnClickListener(listener)

        if (listener == null) {
            this.foreground = null
        } else {
            val outValue = TypedValue()
            context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
            this.foreground = ContextCompat.getDrawable(context, outValue.resourceId)
        }
    }
}