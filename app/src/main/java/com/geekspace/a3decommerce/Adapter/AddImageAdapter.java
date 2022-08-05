package com.geekspace.a3decommerce.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.geekspace.a3decommerce.Activity.MainActivity;
import com.geekspace.a3decommerce.Common.DoubleCLickListener;
import com.geekspace.a3decommerce.Common.RotateZoomImageView;
import com.geekspace.a3decommerce.Furniture.TypeClass;
import com.geekspace.a3decommerce.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class AddImageAdapter extends RecyclerView.Adapter<AddImageAdapter.ViewHolder> {
    private Context context;
    private ArrayList<TypeClass> images = new ArrayList<>();
    private RelativeLayout container;

    public AddImageAdapter(Context context, ArrayList<TypeClass> images, RelativeLayout container) {
        this.context = context;
        this.images = images;
        this.container = container;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_filter_design, parent, false);
        return new AddImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        TypeClass typeClass = images.get(position);
        Glide.with(context)
                .load(typeClass.getImageUrl())
                .into(holder.icon);

        holder.name.setText(typeClass.getName());

        holder.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idIv = new Random().nextInt(123456);
                int idTv = new Random().nextInt(123456);
                TextView removeText = new TextView(context);
                removeText.setText("remove");
                removeText.setTextColor(Color.RED);
                removeText.setTypeface(null, Typeface.BOLD);
                removeText.setId(idTv);
                RelativeLayout.LayoutParams tvlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tvlp.addRule(RelativeLayout.BELOW);
                removeText.setLayoutParams(tvlp);


                RotateZoomImageView iv;
                iv = new RotateZoomImageView(context);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(250, 250);
                lp.addRule(RelativeLayout.BELOW);
                iv.setLayoutParams(lp);
                iv.setId(idIv);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        container.removeView(iv);
                    }
                });
                Glide.with(context)
                        .load(typeClass.getImageUrl())
                        .into(iv);

                MainActivity activity = new MainActivity();


                iv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        activity.setFurnitureTypes(context, iv);
                        if (iv.getRotation() >= 360 || iv.getRotation() <= -360) {
                            iv.setRotation(0);
                        }
                        if ((iv.getRotation() >= 0 && iv.getRotation() <= 10) || (iv.getRotation() > -10 && iv.getRotation() <= 0)) {
                            iv.setRotation(0);
                        }
                        if ((iv.getRotation() <= 360 && iv.getRotation() >= 350) || (iv.getRotation() < -350 && iv.getRotation() <= -360)) {
                            iv.setRotation(0);
                        }

                        removeText.setX(view.getX());
                        removeText.setY(view.getY());

                        return iv.onTouch(view, motionEvent);
                    }
                });

                removeText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        container.removeView(iv);
                        container.removeView(removeText);
                    }
                });


                container.addView(iv);
                container.addView(removeText);

            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public abstract class DoubleCLickListener implements View.OnClickListener {

        // The time in which the second tap should be done in order to qualify as
        // a double click
        private static final long DEFAULT_QUALIFICATION_SPAN = 200;
        private long doubleClickQualificationSpanInMillis;
        private long timestampLastClick;

        public DoubleCLickListener() {
            doubleClickQualificationSpanInMillis = DEFAULT_QUALIFICATION_SPAN;
            timestampLastClick = 0;
        }

        public DoubleCLickListener(long doubleClickQualificationSpanInMillis) {
            this.doubleClickQualificationSpanInMillis = doubleClickQualificationSpanInMillis;
            timestampLastClick = 0;
        }

        @Override
        public void onClick(View v) {
            if ((SystemClock.elapsedRealtime() - timestampLastClick) < doubleClickQualificationSpanInMillis) {
                onDoubleClick();
            }
            timestampLastClick = SystemClock.elapsedRealtime();
        }

        public abstract void onDoubleClick();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout filter;
        ImageView icon;
        TextView name;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            filter = itemView.findViewById(R.id.filter);
            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
        }
    }


}
