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

import io.mallinicouture.presentation.base.BaseFragment;
import io.mallinicouture.databinding.FragmentCcsecureCodeBinding;
import io.mallinicouture.presentation.ui.creditcard.activity.CreditCardActivity;
import io.mallinicouture.presentation.viewmodel.ViewModelFactory;

public class CCSecureCodeFragment extends BaseFragment {

    private FragmentCcsecureCodeBinding binding;

    private TextView tvCvv;

    private CreditCardActivity activity;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    CCSecureCodeFragment() {
    }

    public void setCvv(TextView tv) {
        this.tvCvv = tv;
    }

    public String getValue() {
        return binding.etCvv.getText().toString().trim();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout for this fragment
        binding = FragmentCcsecureCodeBinding.inflate(inflater, container, false);

        activity = (CreditCardActivity) getActivity();
        binding.etCvv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (tvCvv != null) {
                    String cvv = s.toString();

                    if (tvCvv != null) {
                        if (TextUtils.isEmpty(cvv.trim())) {
                            tvCvv.setText("XXX");
                        } else {
                            tvCvv.setText(cvv);
                        }
                    }
                }
            }
        });

        binding.etCvv.setOnEditorActionListener((textView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE && activity != null) {
                activity.nextClick();
                return true;
            }

            return false;
        });

        binding.etCvv.setOnBackButtonListener(() -> {
            if (activity != null) {
                activity.onBackPressed();
            }
        });

        return binding.getRoot();
    }
}
