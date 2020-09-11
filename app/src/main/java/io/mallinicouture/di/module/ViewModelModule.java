package io.mallinicouture.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import io.mallinicouture.di.util.ViewModelKey;
import io.mallinicouture.ui.bottom_navigation.fragment.home.HomeViewModel;
import io.mallinicouture.viewmodel.UserViewModel;
import io.mallinicouture.viewmodel.ViewModelFactory;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindUserViewModel(UserViewModel userViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);

    // TODO: add another viewModels

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
