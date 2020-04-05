package io.mallinicouture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import javax.inject.Inject;

import io.mallinicouture.databinding.ActivityMainBinding;
import io.mallinicouture.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //((BaseApplication) getApplication().getApp
    }
}
