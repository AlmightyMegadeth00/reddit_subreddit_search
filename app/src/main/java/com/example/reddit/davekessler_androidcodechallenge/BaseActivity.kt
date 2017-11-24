package com.example.reddit.davekessler_androidcodechallenge

import android.content.Context
import android.graphics.Rect
import android.support.v4.app.FragmentActivity
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class BaseActivity : FragmentActivity()
{
    override fun dispatchTouchEvent(event: MotionEvent): Boolean
    {
        if (event.action == MotionEvent.ACTION_DOWN)
        {
            var v = currentFocus
            if (v is EditText)
            {
                var outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt()))
                {
                    v.clearFocus()
                    var imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}