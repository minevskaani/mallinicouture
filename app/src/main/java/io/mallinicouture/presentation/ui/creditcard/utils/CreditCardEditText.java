package io.mallinicouture.presentation.ui.creditcard.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;

import androidx.appcompat.widget.AppCompatEditText;

public class CreditCardEditText extends AppCompatEditText {
    //private Context context;
    private BackButtonListener backButtonListener;

    public CreditCardEditText(Context context) {
        super(context);
    }

    public CreditCardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CreditCardEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnBackButtonListener(BackButtonListener listener) {
        this.backButtonListener = listener;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
            Log.d("ET", "onKeyPreIme: ");
            if (backButtonListener != null) {
                backButtonListener.onBackClick();
            }

            // TODO: hide view
            return true;
        }

        return false;
    }

    public interface BackButtonListener {
        void onBackClick();
    }
}
