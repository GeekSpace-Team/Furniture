package com.geekspace.a3decommerce.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.geekspace.a3decommerce.Common.Constant;
import com.geekspace.a3decommerce.Model.Banner;
import com.geekspace.a3decommerce.Model.Slider;
import com.geekspace.a3decommerce.R;

import java.util.ArrayList;
import java.util.Objects;

public class SliderAdapter extends PagerAdapter {

    // Context object
    Context context;

    // Array of images
    ArrayList<Banner> sliders=new ArrayList<>();

    // Layout Inflater
    LayoutInflater mLayoutInflater;


    // Viewpager Constructor
    public SliderAdapter(Context context, ArrayList<Banner> sliders) {
        this.context = context;
        this.sliders = sliders;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // return the number of images
        return sliders.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        Banner slider=sliders.get(position);
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.banner_slider, container, false);

        // referencing the image view from the item.xml file
        ImageView imageView = itemView.findViewById(R.id.imageViewMain);
        TextView title =  itemView.findViewById(R.id.title);

        // setting the image in the imageView
        Glide.with(context)
                .load(slider.getBanner_image())
                .placeholder(android.R.color.transparent)
                .error(android.R.color.transparent)
                .into(imageView);

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((RelativeLayout) object);
    }
}

