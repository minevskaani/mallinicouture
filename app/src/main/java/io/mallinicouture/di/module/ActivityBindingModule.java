package io.mallinicouture.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.mallinicouture.ui.creditcard.activity.CreditCardActivity;
import io.mallinicouture.ui.creditcard.di.CreditCardFragmentBindingModule;
import io.mallinicouture.ui.bottom_navigation.activity.BottomNaviagionActivity;
import io.mallinicouture.ui.bottom_navigation.di.MainFragmentBindingModule;

@Module
public abstract class ActivityBindingModule {

    // TODO: @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract BottomNaviagionActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = {CreditCardFragmentBindingModule.class})
    abstract CreditCardActivity contributeCreditCardActivity();
}
