package io.mallinicouture.presentation.ui.creditcard.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;

import io.mallinicouture.presentation.base.BaseFragment;
import io.mallinicouture.databinding.FragmentCcvalidityBinding;
import io.mallinicouture.presentation.ui.creditcard.activity.CreditCardActivity;
import io.mallinicouture.presentation.ui.creditcard.utils.CreditCardExpiryTextWatcher;
import io.mallinicouture.presentation.viewmodel.ViewModelFactory;

public class CCValidityFragment extends BaseFragment {

    private FragmentCcvalidityBinding binding;
    private TextView tvValidity;

    private CreditCardActivity activity;
    private CardFrontFragment cardFrontFragment;

    @Inject
    CCValidityFragment() {
    }

    @Inject
    ViewModelFactory viewModelFactory;

    public String getValidity() {
        return binding.etValidity.getText().toString().trim();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCcvalidityBinding.inflate(inflater, container, false);

        activity = (CreditCardActivity) getActivity();
        cardFrontFragment = activity.getCardFrontFragment();

        tvValidity = cardFrontFragment.getTvValidity();
        binding.etValidity.addTextChangedListener(new CreditCardExpiryTextWatcher(binding.etValidity, tvValidity));

        binding.etValidity.setOnEditorActionListener((editText, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE && activity != null) {
                activity.nextClick();
                return true;
            }

            return false;
        });

        binding.etValidity.setOnBackButtonListener(() -> {
            if (activity != null) {
                activity.onBackPressed();
            }
        });

        return binding.getRoot();
    }
}
