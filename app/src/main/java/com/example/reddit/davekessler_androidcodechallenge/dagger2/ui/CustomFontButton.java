package com.example.reddit.davekessler_androidcodechallenge.dagger2.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomFontButton extends Button {

    public CustomFontButton(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public CustomFontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public CustomFontButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/bebasneue.ttf");
        setTypeface(tf);
    }
}
