package io.mallinicouture.ui.creditcard.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.mallinicouture.databinding.FragmentCcnumberBinding;
import io.mallinicouture.ui.creditcard.activity.CreditCardActivity;
import io.mallinicouture.ui.creditcard.utils.CreditCardFormattingTextWatcher;

import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;

public class CCNumberFragment extends Fragment {

    FragmentCcnumberBinding binding;

    private CreditCardActivity activity;
    private CardFrontFragment cardFrontFragment;
    private TextView tvNumber;

    public String getCardNumber() {
        return binding.etNumber.toString().trim();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCcnumberBinding.inflate(inflater, container, false);

        activity = (CreditCardActivity) getActivity();
        cardFrontFragment = activity.getCardFrontFragment();

        tvNumber = cardFrontFragment.getTvNumber();

        binding.etNumber.addTextChangedListener(
                new CreditCardFormattingTextWatcher(
                        binding.etNumber, tvNumber, cardFrontFragment.getIvType(),
                        type -> cardFrontFragment.setCardType(type))
        );

        binding.etNumber.setOnEditorActionListener((textView, actionId, event) -> {
            if (actionId == IME_ACTION_DONE && activity != null) {
                activity.nextClick();
                return true;
            }

            return false;
        });

        binding.etNumber.setOnBackButtonListener(() -> {
            if (activity != null) {
                activity.onBackPressed();
            }
        });

        return binding.getRoot();
    }
}