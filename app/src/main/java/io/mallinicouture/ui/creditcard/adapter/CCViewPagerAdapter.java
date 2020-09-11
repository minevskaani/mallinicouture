package io.mallinicouture.ui.creditcard.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.mallinicouture.ui.creditcard.activity.CreditCardActivity;

public class CCViewPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();

    @Inject
    public CCViewPagerAdapter(@NonNull CreditCardActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentList.get(position); // TODO: create new
    }

    @Override
    public int getItemCount() {
        return mFragmentList.size();
    }
}
