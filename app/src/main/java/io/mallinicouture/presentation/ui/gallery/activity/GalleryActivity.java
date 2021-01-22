package io.mallinicouture.presentation.ui.gallery.activity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import io.mallinicouture.presentation.base.BaseActivity;
import io.mallinicouture.databinding.ActivityGalleryBinding;
import io.mallinicouture.presentation.ui.gallery.model.GalleryCardItem;
import io.mallinicouture.presentation.ui.gallery.adapter.GalleryRecyclerViewAdapter;

public class GalleryActivity extends BaseActivity {

    private ActivityGalleryBinding binding;

    private RecyclerView mGalleryRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGalleryBinding.inflate(LayoutInflater.from(this));

        List<GalleryCardItem> galleryItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // galleryItems.add(new GalleryCardItem("Everyday Long Dress", R.drawable.cat_long, 49.99f));
            // galleryItems.add(new GalleryCardItem("Printed Dress", R.drawable.cat_long2, 49.99f));
            // galleryItems.add(new GalleryCardItem("Blouson Short Dress", R.drawable.cat_mini, 49.99f));
            // galleryItems.add(new GalleryCardItem("Tencel Denim Dress", R.drawable.cat_official, 49.99f));
        }

        mAdapter = new GalleryRecyclerViewAdapter(this, galleryItems);

        mGalleryRecyclerView.setAdapter(mAdapter);
    }

    private void setupRecyclerView() {
        mGalleryRecyclerView = binding.rvGallery;
        mGalleryRecyclerView.setHasFixedSize(false);
        mLayoutManager = new GridLayoutManager(this, 2);
        mGalleryRecyclerView.setLayoutManager(mLayoutManager);
    }
}
