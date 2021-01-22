package io.mallinicouture.presentation.ui.bottom_navigation.fragment.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import io.mallinicouture.R;
import io.mallinicouture.presentation.base.BaseFragment;
import io.mallinicouture.presentation.ui.bottom_navigation.fragment.favourite.FavouriteViewModel;

public class SettingsFragment extends BaseFragment {

    private FavouriteViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favourite, container, false);

        return root;
    }
}
