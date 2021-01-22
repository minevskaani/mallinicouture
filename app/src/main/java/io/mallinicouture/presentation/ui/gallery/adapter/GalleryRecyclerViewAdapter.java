package io.mallinicouture.presentation.ui.gallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.mallinicouture.R;
import io.mallinicouture.presentation.ui.gallery.model.GalleryCardItem;

public class GalleryRecyclerViewAdapter extends RecyclerView.Adapter<GalleryRecyclerViewAdapter.GalleryCardViewHolder> {

    private Context mContext;
    private List<GalleryCardItem> mData;

    public GalleryRecyclerViewAdapter(Context mContext, List<GalleryCardItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public GalleryCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_gallery, parent, false);

        return new GalleryCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryCardViewHolder holder, int position) {
        GalleryCardItem item = mData.get(position);

        Picasso.get().load(item.getImage().getUrl()).into(holder.mImageView);
        holder.mTitleTextView.setText(item.getTitle());
        holder.mPriceTextView.setText("$ " + item.getPrice());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class GalleryCardViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTitleTextView;
        private TextView mPriceTextView;

        public GalleryCardViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView     = (ImageView) itemView.findViewById(R.id.iv_gallery_card);
            mTitleTextView = (TextView)  itemView.findViewById(R.id.tv_gallery_card_title);
            mPriceTextView = (TextView)  itemView.findViewById(R.id.tv_gallery_card_price);
        }
    }
}
