package io.mallinicouture.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.mallinicouture.R;
import io.mallinicouture.ui.gallery.GalleryActivity;
import io.mallinicouture.ui.home.advertisement.AdvPagerAdapter;
import io.mallinicouture.ui.home.advertisement.Advertisement;
import io.mallinicouture.ui.home.category.Category;
import io.mallinicouture.ui.home.category.CategoryRecyclerViewAdapter;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    // Advertisements
    private ViewPager mAdvertisementViewPager;
    private AdvPagerAdapter mAdvPagerAdapter;
    // Adv indicator
    private TabLayout mIndicatorTabLayout;

    // Categories
    private RecyclerView categoryRecyclerView;
    private CategoryRecyclerViewAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
         */

        initAdv(root);
        initCategories(root);

        // setupTimer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new AdvTimer(), 4000, 6000);


        return root;
    }

    private void initAdv(View root) {
        mAdvertisementViewPager = root.findViewById(R.id.vp_home_advertisement);

        List<Advertisement> advertisements = new ArrayList<>();

        advertisements.add(new Advertisement(R.drawable.cat_mini, "New Dresses", "30+ Designer Brands"));
        advertisements.add(new Advertisement(R.drawable.cat_mini, "New Dresses", "30+ Designer Brands"));
        advertisements.add(new Advertisement(R.drawable.cat_mini, "New Dresses", "30+ Designer Brands"));
        advertisements.add(new Advertisement(R.drawable.cat_mini, "New Dresses", "30+ Designer Brands"));

        mAdvPagerAdapter = new AdvPagerAdapter(getContext(), advertisements);
        mAdvertisementViewPager.setAdapter(mAdvPagerAdapter);

        initIndicator(root);
    }

    private void initIndicator(View root) {
        mIndicatorTabLayout = root.findViewById(R.id.tl_adv_indicator);
        mIndicatorTabLayout.setupWithViewPager(mAdvertisementViewPager, true);
    }

    private void initCategories(View root) {
        categoryRecyclerView = (RecyclerView) root.findViewById(R.id.rv_categories);
        categoryRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(root.getContext());
        categoryRecyclerView.setLayoutManager(mLayoutManager);

        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Длинные",     R.drawable.cat_long));
        categories.add(new Category("Длинные 2",   R.drawable.cat_long2));
        categories.add(new Category("Мини",        R.drawable.cat_mini));
        categories.add(new Category("Официальные", R.drawable.cat_official));

        mAdapter = new CategoryRecyclerViewAdapter(getContext(), categories);
        mAdapter.setOnCategoryCardClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GalleryActivity.class);
                startActivity(intent);
            }
        });

        categoryRecyclerView.setAdapter(mAdapter);
    }

    class AdvTimer extends TimerTask {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int curr = mAdvertisementViewPager.getCurrentItem();

                    if (curr < mAdvPagerAdapter.getCount()) {
                        mAdvertisementViewPager.setCurrentItem(curr + 1);
                    } else {
                        mAdvertisementViewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
