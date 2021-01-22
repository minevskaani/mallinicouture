package io.mallinicouture.presentation.ui.creditcard.utils;

import android.content.Context;
import android.graphics.Typeface;

public class FontTypeChange {
    private Context c;

    public FontTypeChange(Context c) {
        this.c = c;
    }

    public Typeface getFontface(int n) {
        switch (n) {
            case 1:
                return Typeface.createFromAsset(c.getAssets(), "fonts/kreditback.ttf");
            case 2:
                return Typeface.createFromAsset(c.getAssets(), "fonts/kreditfront.ttf");
            case 3:
                return Typeface.createFromAsset(c.getAssets(), "fonts/ocramedium.ttf");
            default:
                return Typeface.createFromAsset(c.getAssets(), "fonts/kreditfront.ttf");

        }
    }
}
