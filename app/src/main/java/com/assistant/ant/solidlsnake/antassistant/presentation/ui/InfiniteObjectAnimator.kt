package com.assistant.ant.solidlsnake.antassistant.presentation.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator

class InfiniteObjectAnimator(
        private val animator: Animator
) {
    private var stopAnimation: Boolean = false

    init {
        if (animator is ValueAnimator) {
            animator.repeatCount = ValueAnimator.INFINITE
        }

        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationRepeat(animation: Animator?) {
                if (stopAnimation) {
                    animation?.end()
                    stopAnimation = false
                }
            }
        })
    }

    fun start() {
        animator.start()
    }

    fun endOnNext() {
        stopAnimation = true
    }

    fun addListener(listener: Animator.AnimatorListener?): InfiniteObjectAnimator {
        animator.addListener(listener)
        return this
    }
}

fun Animator.toInfinite(): InfiniteObjectAnimator = InfiniteObjectAnimator(this)