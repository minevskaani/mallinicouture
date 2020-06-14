package io.mallinicouture.ui.creditcard.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.mallinicouture.R;
import io.mallinicouture.databinding.FragmentCcvalidityBinding;
import io.mallinicouture.ui.creditcard.activity.CreditCardActivity;
import io.mallinicouture.ui.creditcard.utils.CreditCardExpiryTextWatcher;

public class CCValidityFragment extends Fragment {

    private FragmentCcvalidityBinding binding;
    private TextView tvValidity;

    private CreditCardActivity activity;
    private CardFrontFragment cardFrontFragment;

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
