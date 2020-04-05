package io.mallinicouture.ui.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.mallinicouture.R;

public class GalleryActivity extends AppCompatActivity {

    private RecyclerView mGalleryRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mGalleryRecyclerView = (RecyclerView) findViewById(R.id.rv_gallery);
        mGalleryRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this, 2);
        mGalleryRecyclerView.setLayoutManager(mLayoutManager);

        List<GalleryCardItem> galleryItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            galleryItems.add(new GalleryCardItem("Everyday Long Dress", R.drawable.cat_long, 49.99f));
            galleryItems.add(new GalleryCardItem("Printed Dress", R.drawable.cat_long2, 49.99f));
            galleryItems.add(new GalleryCardItem("Blouson Short Dress", R.drawable.cat_mini, 49.99f));
            galleryItems.add(new GalleryCardItem("Tencel Denim Dress", R.drawable.cat_official, 49.99f));
        }

        mAdapter = new GalleryRecyclerViewAdapter(this, galleryItems);

        mGalleryRecyclerView.setAdapter(mAdapter);

    }
}
