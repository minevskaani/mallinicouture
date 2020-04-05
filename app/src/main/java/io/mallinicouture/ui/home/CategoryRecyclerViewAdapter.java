package io.mallinicouture.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.mallinicouture.R;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder> {

    private Context mContext;
    private List<Category> mData;
    private View.OnClickListener onCategoryCardClickListener;

    public CategoryRecyclerViewAdapter(Context mContext, List<Category> mData) {
        this.mContext = mContext;
        this.mData = mData;
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
        holder.mCategoryImage.setImageResource(currCategory.getImage());

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
