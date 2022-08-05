package com.geekspace.a3decommerce.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.geekspace.a3decommerce.Model.Basket;
import com.geekspace.a3decommerce.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {
    private ArrayList<Basket> arrayList=new ArrayList<>();
    private Context context;

    public BasketAdapter(ArrayList<Basket> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.order_design, null);
        return new BasketAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Basket basket=arrayList.get(position);
        Glide.with(context)
                .load(basket.getImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable @org.jetbrains.annotations.Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.image.setBackgroundResource(android.R.color.transparent);
                        return false;
                    }
                })
                .into(holder.image);
        holder.count.setText(basket.getCount()+"");
        holder.name.setText(basket.getName());
        holder.price.setText(basket.getPrice()+" TMT");
        holder.category.setText(basket.getCategoryName());
        double total=basket.getPrice()*basket.getCount();
        holder.total.setText(total+" TMT");

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=basket.getCount();
                count--;
                if(count>0){
                    basket.setCount(count);
                    notifyItemChanged(holder.getAdapterPosition());
                } else{
                    arrayList.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                }

            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=basket.getCount();
                count++;
                basket.setCount(count);
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,category,price,total,count,plus,minus;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            category=itemView.findViewById(R.id.category);
            price=itemView.findViewById(R.id.price);
            total=itemView.findViewById(R.id.total);
            count=itemView.findViewById(R.id.count);
            plus=itemView.findViewById(R.id.plus);
            minus=itemView.findViewById(R.id.minus);
        }
    }
}
