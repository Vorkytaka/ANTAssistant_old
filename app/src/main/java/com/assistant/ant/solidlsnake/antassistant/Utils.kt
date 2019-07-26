package com.assistant.ant.solidlsnake.antassistant

import android.content.res.Resources
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

fun CharSequence?.orEmpty(): String = this?.toString() ?: ""

/**
 * Возвращает канал, который реагирует на изменения в TextView.
 */
fun TextView.textChanges(capacity: Int = 1): ReceiveChannel<CharSequence> {
    val channel = Channel<CharSequence>(capacity)

    val listener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (!channel.isClosedForSend) {
                channel.offer(s ?: "")
            }
        }
    }

    this.addTextChangedListener(listener)

    channel.invokeOnClose {
        this.removeTextChangedListener(listener)
    }

    return channel
}

inline fun <T : Number> T.dpToPx(): Int {
    val metrics = Resources.getSystem().displayMetrics
    val px = this.toFloat() * (metrics.densityDpi / 160f)

    return px.toInt()
}