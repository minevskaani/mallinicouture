package io.mallinicouture.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {

    private AppCompatActivity activity;

    // @LayoutRes
    // protected abstract int layoutRes();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // View view = inflater.inflate(layoutRes(), container, false);
        // TODO: bind this

        // return view;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public AppCompatActivity getBaseActivity() {
        return activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // TODO: unbinder
    }
}
