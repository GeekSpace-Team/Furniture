package com.geekspace.a3decommerce.Furniture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geekspace.a3decommerce.Camera.ImageFilterClass;
import com.geekspace.a3decommerce.Common.RotateZoomImageView;
import com.geekspace.a3decommerce.R;
import com.otaliastudios.cameraview.CameraView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FurnitureTypeAdapter extends RecyclerView.Adapter<FurnitureTypeAdapter.ViewHolder> {

    Context context;
    ArrayList<TypeClass> imageFilterClassArrayList=new ArrayList<>();
    RotateZoomImageView imageView;
    public FurnitureTypeAdapter(Context context, ArrayList<TypeClass> imageFilterClassArrayList,RotateZoomImageView imageView) {
        this.context = context;
        this.imageFilterClassArrayList = imageFilterClassArrayList;
        this.imageView=imageView;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_filter_design, parent, false);
        return new FurnitureTypeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        TypeClass typeClass=imageFilterClassArrayList.get(position);
        Glide.with(context)
                .load(typeClass.getImageUrl())
                .into(holder.icon);

        holder.name.setText(typeClass.getName());

        holder.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(context)
                        .load(typeClass.getImageUrl())
                        .into(imageView);


            }
        });
    }

    @Override
    public int getItemCount() {
        return imageFilterClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout filter;
        ImageView icon;
        TextView name;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            filter=itemView.findViewById(R.id.filter);
            icon=itemView.findViewById(R.id.icon);
            name=itemView.findViewById(R.id.name);
        }
    }
}
