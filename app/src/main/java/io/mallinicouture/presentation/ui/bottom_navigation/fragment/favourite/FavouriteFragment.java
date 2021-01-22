package io.mallinicouture.presentation.ui.bottom_navigation.fragment.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import io.mallinicouture.R;
import io.mallinicouture.presentation.base.BaseFragment;

public class FavouriteFragment extends BaseFragment {

    private FavouriteViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(FavouriteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favourite, container, false);
        final TextView textView = root.findViewById(R.id.text_favourite);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}
