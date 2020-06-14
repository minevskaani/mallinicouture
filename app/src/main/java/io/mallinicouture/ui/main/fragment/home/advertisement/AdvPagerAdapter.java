package io.mallinicouture.ui.main.fragment.home.advertisement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import io.mallinicouture.R;

public class AdvPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<Advertisement> mData;

    public AdvPagerAdapter(Context mContext, List<Advertisement> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View slideLayout = inflater.inflate(R.layout.viewpager_home_advertisement_item, container, false);

        ImageView slideImage = slideLayout.findViewById(R.id.iv_adv_imageView);
        TextView slideTitle = slideLayout.findViewById(R.id.tv_adv_title);
        TextView slideInformation = slideLayout.findViewById(R.id.tv_adv_information);

        Advertisement adv = mData.get(position);

        slideImage.setImageResource(adv.getImageId());
        slideTitle.setText(adv.getTitle());
        slideInformation.setText(adv.getInformation());

        container.addView(slideLayout);

        return slideLayout;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
