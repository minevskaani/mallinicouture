package io.mallinicouture.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import io.mallinicouture.di.util.ViewModelKey;
import io.mallinicouture.viewmodel.UserViewModel;
import io.mallinicouture.viewmodel.ViewModelFactory;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindViewModel(UserViewModel userViewModel);

    // TODO: add another viewModels

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
