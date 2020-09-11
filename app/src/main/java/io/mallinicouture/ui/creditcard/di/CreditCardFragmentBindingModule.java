package io.mallinicouture.ui.creditcard.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.mallinicouture.ui.creditcard.fragment.CCNameFragment;
import io.mallinicouture.ui.creditcard.fragment.CCNumberFragment;
import io.mallinicouture.ui.creditcard.fragment.CCSecureCodeFragment;
import io.mallinicouture.ui.creditcard.fragment.CCValidityFragment;
import io.mallinicouture.ui.creditcard.fragment.CardBackFragment;
import io.mallinicouture.ui.creditcard.fragment.CardFrontFragment;

@Module
public abstract class CreditCardFragmentBindingModule {

    @ContributesAndroidInjector
    abstract CardFrontFragment contributeCardFrontFragment();

    @ContributesAndroidInjector
    abstract CardBackFragment contributeCardBackFragment();

    @ContributesAndroidInjector
    abstract CCNameFragment contributeCCNameFragment();

    @ContributesAndroidInjector
    abstract CCNumberFragment contributeCCNumberFragment();

    @ContributesAndroidInjector
    abstract CCSecureCodeFragment contributeCCSecureCodeFragment();

    @ContributesAndroidInjector
    abstract CCValidityFragment contributeCCValidityFragment();

}
