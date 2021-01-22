package io.mallinicouture.presentation.ui.creditcard.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;

import io.mallinicouture.R;
import io.mallinicouture.presentation.base.BaseFragment;
import io.mallinicouture.databinding.FragmentCcnameBinding;
import io.mallinicouture.presentation.ui.creditcard.activity.CreditCardActivity;
import io.mallinicouture.presentation.viewmodel.ViewModelFactory;

public class CCNameFragment extends BaseFragment {

    FragmentCcnameBinding binding;

    private TextView tvName;

    private CreditCardActivity activity;
    private CardFrontFragment cardFrontFragment;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    CCNameFragment() {
    }

    public String getName() {
        return binding.etName.getText().toString().trim();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout for this fragment
        binding = FragmentCcnameBinding.inflate(inflater, container, false);

        activity = (CreditCardActivity) getActivity();
        cardFrontFragment = activity.getCardFrontFragment();

        tvName = cardFrontFragment.getTvName();
        binding.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (tvName != null) {
                    if (TextUtils.isEmpty(s.toString().trim())) {
                        tvName.setText(R.string.card_name_sample);
                    } else {
                        tvName.setText(s.toString());
                    }
                }
            }
        });

        binding.etName.setOnEditorActionListener((textView, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE && activity != null) {
                    activity.nextClick();
                    return true;
                }

                return false;
        });


        binding.etName.setOnBackButtonListener(() -> {
            if (activity != null) {
                activity.onBackPressed();
            }
        });

        return binding.getRoot();
    }
}
