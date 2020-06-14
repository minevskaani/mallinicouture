package io.mallinicouture.ui.creditcard.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.mallinicouture.databinding.FragmentCardBackBinding;
import io.mallinicouture.ui.creditcard.activity.CreditCardActivity;
import io.mallinicouture.ui.creditcard.utils.FontTypeChange;

public class CardBackFragment extends Fragment {

    private FragmentCardBackBinding binding;

    private FontTypeChange fontTypeChange;

    private CreditCardActivity activity;
    private CCSecureCodeFragment secureCodeFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding        = FragmentCardBackBinding.inflate(inflater, container, false);

        activity       = (CreditCardActivity) getActivity();
        fontTypeChange = new FontTypeChange(activity);
        binding.tvCvv.setTypeface(fontTypeChange.getFontface(1));

        secureCodeFragment = activity.getSecureCodeFragment();
        secureCodeFragment.setCvv(binding.tvCvv);

        if (!TextUtils.isEmpty(secureCodeFragment.getValue())) {
            binding.tvCvv.setText(secureCodeFragment.getValue());
        }

        return binding.getRoot();
    }
}
