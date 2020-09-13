package com.xxx.xxx

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * Type Writer
 *
 * Based on Antarix's project
 * on 'https://gist.github.com/Antarix/6388606'
 *
 * @author Shabby
 */
class TypeWriter(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    AppCompatTextView(context, attrs, defStyleAttr) {
    private var mText: CharSequence = text ?: "" // Text to be displayed
    private var mIndex = 0
    private var mDelay: Long = 150 // Default 150ms delay
    private var mTypeArray = context.obtainStyledAttributes(attrs, R.styleable.TypeWriter)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?)
            : this(context, attrs, android.R.attr.textViewStyle) {
        text = ""
        mDelay = mTypeArray.getInt(R.styleable.TypeWriter_delay, 150).toLong()
        if (mTypeArray.getBoolean(R.styleable.TypeWriter_autoStart, false)) startAnimation()
    }

    private val mHandler = Handler()
    private val characterAdder: Runnable = object : Runnable {
        override fun run() {
            text = mText.subSequence(0, mIndex++)
            if (mIndex <= mText.length) {
                mHandler.postDelayed(this, mDelay)
            }
        }
    }

    fun startAnimation() {
        mIndex = 0
        mHandler.removeCallbacks(characterAdder)
        mHandler.postDelayed(characterAdder, mDelay)
    }

    fun startAnimation(text: CharSequence) {
        mIndex = 0
        mText = text
        mHandler.removeCallbacks(characterAdder)
        mHandler.postDelayed(characterAdder, mDelay)
    }

    // The time-consuming of animation
    fun needTime(): Long {
        return (mText.length + 1) * mDelay
    }
}
