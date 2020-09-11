package io.mallinicouture.ui.bottom_navigation.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.mallinicouture.ui.bottom_navigation.fragment.favourite.FavouriteFragment;
import io.mallinicouture.ui.bottom_navigation.fragment.home.HomeFragment;
import io.mallinicouture.ui.bottom_navigation.fragment.settings.SettingsFragment;
import io.mallinicouture.ui.bottom_navigation.fragment.shoppingbasket.ShoppingBasketFragment;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector
    abstract FavouriteFragment contributeFavouriteFragment();

    @ContributesAndroidInjector
    abstract ShoppingBasketFragment contributeShoppingBasketFragment();

    @ContributesAndroidInjector
    abstract SettingsFragment contributeSettingsFragment();

}
