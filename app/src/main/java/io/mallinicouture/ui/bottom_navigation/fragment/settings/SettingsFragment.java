package io.mallinicouture.ui.bottom_navigation.fragment.settings;

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
import io.mallinicouture.base.BaseFragment;
import io.mallinicouture.ui.bottom_navigation.fragment.favourite.FavouriteViewModel;

public class SettingsFragment extends BaseFragment {

    private FavouriteViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favourite, container, false);

        return root;
    }
}
