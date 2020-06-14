package io.mallinicouture.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.mallinicouture.ui.creditcard.activity.CreditCardActivity;
import io.mallinicouture.ui.main.activity.MainActivity;

@Module
public abstract class ActivityBindingModule {

    // TODO: @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {})
    abstract CreditCardActivity bindCreditCardActivity();
}
