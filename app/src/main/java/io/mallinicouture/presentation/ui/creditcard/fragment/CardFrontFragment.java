package io.mallinicouture.presentation.ui.creditcard.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import io.mallinicouture.R;
import io.mallinicouture.databinding.FragmentCardFrontBinding;
import io.mallinicouture.presentation.ui.creditcard.utils.FontTypeChange;
import lombok.Getter;

import static io.mallinicouture.presentation.ui.creditcard.utils.CreditCardUtils.AMEX;
import static io.mallinicouture.presentation.ui.creditcard.utils.CreditCardUtils.DISCOVER;
import static io.mallinicouture.presentation.ui.creditcard.utils.CreditCardUtils.MASTERCARD;
import static io.mallinicouture.presentation.ui.creditcard.utils.CreditCardUtils.NONE;
import static io.mallinicouture.presentation.ui.creditcard.utils.CreditCardUtils.VISA;

public class CardFrontFragment extends Fragment {

    @Getter
    private TextView tvNumber;
    @Getter
    private TextView tvName;
    @Getter
    private TextView tvValidity;
    @Getter
    private ImageView ivType;

    private FontTypeChange fontTypeChange;

    @Inject
    public CardFrontFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentCardFrontBinding binding = FragmentCardFrontBinding.inflate(inflater, container, false);
        tvNumber       = binding.tvCardNumber;
        tvName         = binding.tvMemberName;
        tvValidity     = binding.tvValidity;
        ivType         = binding.ivType;

        fontTypeChange = new FontTypeChange(getActivity());
        tvNumber.setTypeface(fontTypeChange.getFontface(3));
        tvName.setTypeface(fontTypeChange.getFontface(3));

        return binding.getRoot();
    }

    public void setCardType(int type) {
        switch (type) {
            case VISA:
                ivType.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_visa));
                break;
            case MASTERCARD:
                ivType.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_mastercard));
                break;
            case AMEX:
                ivType.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_amex));
                break;
            case DISCOVER:
                ivType.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_discover));
                break;
            case NONE:
                ivType.setImageResource(android.R.color.transparent);
                break;
        }
    }
}
