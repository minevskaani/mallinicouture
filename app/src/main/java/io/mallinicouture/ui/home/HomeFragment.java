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

import java.util.ArrayList;
import java.util.List;

import io.mallinicouture.R;
import io.mallinicouture.ui.gallery.GalleryActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView categoryRecyclerView;
    private CategoryRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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

        categoryRecyclerView = (RecyclerView) root.findViewById(R.id.rv_categories);
        categoryRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(root.getContext());
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

        return root;
    }
}
