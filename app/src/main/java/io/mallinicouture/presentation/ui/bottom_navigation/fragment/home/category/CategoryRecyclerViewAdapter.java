package io.mallinicouture.presentation.ui.bottom_navigation.fragment.home.category;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.mallinicouture.R;
import io.mallinicouture.data.model.Category;
import io.mallinicouture.data.model.Image;
import io.mallinicouture.presentation.ui.bottom_navigation.fragment.home.HomeViewModel;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder> {

    private static final String TAG = "CategoryRecyclerViewAda";

    private Context mContext;
    private final List<Category> mData;
    private View.OnClickListener onCategoryCardClickListener;

    public CategoryRecyclerViewAdapter(Context mContext,
                                       LifecycleOwner lifecycleOwner,
                                       HomeViewModel homeViewModel) {
        this.mContext = mContext;
        this.mData = new ArrayList<>();

        homeViewModel.getCategories().observe(lifecycleOwner, categories -> {
            mData.clear();
            if (categories != null) {
                mData.addAll(categories);
                notifyDataSetChanged();
            }
        });

        // Also should override getItemId
        setHasStableIds(true);
    }

    public View.OnClickListener getOnCategoryCardClickListener() {
        return onCategoryCardClickListener;
    }

    public void setOnCategoryCardClickListener(View.OnClickListener onCategoryCardClickListener) {
        this.onCategoryCardClickListener = onCategoryCardClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.cardview_main_category, parent, false);

        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category currCategory = mData.get(position);

        holder.mCategoryTitle.setText(currCategory.getTitle());
        //holder.mCategoryImage.setImageResource(currCategory.getImage());
        Image image = currCategory.getImage();
        if (image != null) {
            Log.d(TAG, "onBindViewHolder: Image url: " + image.getUrl());
            Picasso.get().load(image.getUrl()).into(holder.mCategoryImage);
        } else {
            Log.e(TAG, "onBindViewHolder: No Image");
        }

        holder.mCategoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCategoryCardClickListener != null) {
                    onCategoryCardClickListener.onClick(v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).getId();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView mCategoryTitle;
        private ImageView mCategoryImage;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            mCategoryTitle = (TextView) itemView.findViewById(R.id.tv_category_title);
            mCategoryImage = (ImageView) itemView.findViewById(R.id.iv_category);
        }
    }
}
