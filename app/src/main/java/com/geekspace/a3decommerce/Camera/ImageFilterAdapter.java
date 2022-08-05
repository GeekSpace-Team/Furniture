package com.geekspace.a3decommerce.Camera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekspace.a3decommerce.R;
import com.otaliastudios.cameraview.CameraView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ImageFilterAdapter extends RecyclerView.Adapter<ImageFilterAdapter.ViewHolder> {

    Context context;
    ArrayList<ImageFilterClass> imageFilterClassArrayList=new ArrayList<>();
    CameraView camera;

    public ImageFilterAdapter(Context context, ArrayList<ImageFilterClass> imageFilterClassArrayList, CameraView camera) {
        this.context = context;
        this.imageFilterClassArrayList = imageFilterClassArrayList;
        this.camera = camera;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_filter_design, parent, false);
        return new ImageFilterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        ImageFilterClass filterClass=imageFilterClassArrayList.get(position);
        holder.icon.setImageResource(filterClass.getFilterImage());
        holder.name.setText(filterClass.getFilterName());

        holder.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera.setFilter(filterClass.getFilter());
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
