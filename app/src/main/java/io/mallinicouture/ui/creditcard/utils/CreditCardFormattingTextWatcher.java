package io.mallinicouture.ui.creditcard.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CreditCardFormattingTextWatcher implements TextWatcher {

    private EditText etCard;
    private TextView tvCard;
    private ImageView ivType;
    private Boolean isDelete;
    private CreditCardType creditCardType;

    public CreditCardFormattingTextWatcher(EditText etCard) {
        this.etCard = etCard;
    }

    public CreditCardFormattingTextWatcher(EditText etCard, TextView tvCard) {
        this.etCard = etCard;
        this.tvCard = tvCard;
    }

    public CreditCardFormattingTextWatcher(EditText etCard, TextView tvCard, CreditCardType creditCardType) {
        this.etCard = etCard;
        this.tvCard = tvCard;
        this.creditCardType = creditCardType;
    }

    public CreditCardFormattingTextWatcher(EditText etCard, TextView tvCard, ImageView ivType, CreditCardType creditCardType) {
        this.etCard = etCard;
        this.tvCard = tvCard;
        this.ivType = ivType;
        this.creditCardType = creditCardType;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        isDelete = before != 0;
    }

    @Override
    public void afterTextChanged(Editable s) {
        String src = s.toString();
        int length = src.length();

        StringBuilder sb = new StringBuilder()
                .append(src);

        if (length > 0 && length % 5 == 0) {
            if (isDelete) {
                sb.deleteCharAt(length - 1);
            } else {
                sb.append(" ");
            }

            etCard.setText(sb);
            etCard.setSelection(etCard.getText().length());
        }

        if (length >= 4 && creditCardType != null) {
            creditCardType.setCardType(CreditCardUtils.getCardType(src.trim()));
        }

        if (tvCard != null) {
            if (length == 0) {
                tvCard.setText("XXXX XXXX XXXX XXXX");
            } else {
                tvCard.setText(sb);
            }
        }

        if (ivType != null && length == 0) {
            ivType.setImageResource(android.R.color.transparent);
        }
    }

    public interface CreditCardType {
        void setCardType(int type);
    }
}
