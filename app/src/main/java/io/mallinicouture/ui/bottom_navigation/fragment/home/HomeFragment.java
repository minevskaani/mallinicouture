package io.mallinicouture.ui.bottom_navigation.fragment.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import io.mallinicouture.base.BaseFragment;
import io.mallinicouture.databinding.FragmentHomeBinding;
import io.mallinicouture.ui.gallery.activity.GalleryActivity;
import io.mallinicouture.ui.bottom_navigation.fragment.home.advertisement.AdvPagerAdapter;
import io.mallinicouture.ui.bottom_navigation.fragment.home.category.CategoryRecyclerViewAdapter;
import io.mallinicouture.viewmodel.ViewModelFactory;

public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding binding;

    // Advertisements
    private ViewPager mAdvertisementViewPager;
    private AdvPagerAdapter mAdvPagerAdapter;
    private int currVPPossition;

    // Advertisement indicator
    private TabLayout mIndicatorTabLayout;

    // Categories
    private RecyclerView categoryRecyclerView;
    private CategoryRecyclerViewAdapter mAdapter;

    @Inject
    ViewModelFactory viewModelFactory;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
        //View root = inflater.inflate(R.layout.fragment_home, container, false);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        initAdv();
        initCategories();

        // setupTimer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new AdvTimer(), 4000, 6000);

        // setup SwipeRefreshLayout
        binding.srlRefreshContent.setOnRefreshListener(() -> {
            homeViewModel.fetchLatestData();
            binding.srlRefreshContent.setRefreshing(false);
        });

        return binding.getRoot();
    }

    private void initAdv() {
        mAdvertisementViewPager = binding.vpHomeAdvertisement;

        mAdvPagerAdapter = new AdvPagerAdapter(getContext(), getViewLifecycleOwner(), homeViewModel);
        mAdvertisementViewPager.setAdapter(mAdvPagerAdapter);

        // Make viewPager circular
        mAdvertisementViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (currVPPossition == -1) {
                    mAdvertisementViewPager.setCurrentItem(mAdvPagerAdapter.getCount() - 1, false);
                }

                if (currVPPossition == mAdvPagerAdapter.getCount()) {
                    mAdvertisementViewPager.setCurrentItem(0, false);
                }
            }

            @Override
            public void onPageSelected(int position) {
                currVPPossition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initVPIndicator();
    }

    private void initVPIndicator() {
        mIndicatorTabLayout = binding.tlAdvIndicator;
        mIndicatorTabLayout.setupWithViewPager(mAdvertisementViewPager, true);
    }

    private void initCategories() {
        categoryRecyclerView = binding.rvCategories;
        categoryRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        categoryRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CategoryRecyclerViewAdapter(getContext(), getViewLifecycleOwner(), homeViewModel);
        mAdapter.setOnCategoryCardClickListener((view) -> {
            Intent intent = new Intent(getContext(), GalleryActivity.class);
            startActivity(intent);
        });

        categoryRecyclerView.setAdapter(mAdapter);
    }

    private void observableViewModel() {
        // TODO: add loading and error handling
    }

    class AdvTimer extends TimerTask {

        @Override
        public void run() {
            Activity act = getBaseActivity();
            if (act != null) {
                act.runOnUiThread(() -> {
                    int curr = mAdvertisementViewPager.getCurrentItem();

                    if (curr < mAdvPagerAdapter.getCount()) {
                        mAdvertisementViewPager.setCurrentItem(curr + 1);
                    } else {
                        mAdvertisementViewPager.setCurrentItem(0);
                    }
                });
            }
        }
    }
}
