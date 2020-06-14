package io.mallinicouture.ui.creditcard.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class CreditCardExpiryTextWatcher implements TextWatcher {
    // TODO: fix bug when try to delete not last character with /

    private EditText etCard;
    private TextView tvCard;
    private boolean isDelete;

    public CreditCardExpiryTextWatcher(EditText etCard, TextView tvCard) {
        this.etCard = etCard;
        this.tvCard = tvCard;
    }

    public CreditCardExpiryTextWatcher(EditText etCard) {
        this.etCard = etCard;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (before == 0) {
            isDelete = false;
        } else {
            isDelete = true;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        String src = s.toString();
        int length = src.length();

        StringBuilder sb = new StringBuilder()
                .append(src);

        if (length == 3) {
            if (isDelete) {
                sb.deleteCharAt(length - 1);
            } else {
                sb.insert(length - 1, "/");
            }

            etCard.setText(sb);
            etCard.setSelection(etCard.getText().length()); // move after last character
        }

        if (tvCard != null) {
            if (length == 0) {
                tvCard.setText("MM/YY");
            } else {
                tvCard.setText(sb);
            }
        }

    }
}
