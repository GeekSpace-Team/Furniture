package com.geekspace.a3decommerce.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geekspace.a3decommerce.Activity.ProductPage;
import com.geekspace.a3decommerce.Common.Constant;
import com.geekspace.a3decommerce.DataBase.FavDB;
import com.geekspace.a3decommerce.Model.Product;
import com.geekspace.a3decommerce.R;
import com.varunest.sparkbutton.SparkButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<Product> products = new ArrayList<>();
    private Context context;
    private FavDB favDB;
    private Cursor favCursor;
    public ProductAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
        favDB = new FavDB(context);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_design, null);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.category.setText(product.getCategory());
        holder.name.setText(product.getName());
        holder.cost.setText(product.getCost() + " TMT");
        favCursor=favDB.getSelect(product.getProduct_id());
        if (favCursor.getCount()>0)
            holder.fav.setImageResource(R.drawable.ic_baseline_favorite_24);

        Glide.with(context)
                .load(product.getImage())
                .error(android.R.color.transparent)
                .placeholder(android.R.color.transparent)
                .into(holder.image);

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favCursor.getCount()>0) {
                    favDB.deleteData(product.getProduct_id());
                    holder.fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                } else {
                    favDB.insertData(product.getProduct_id());
                    holder.fav.setImageResource(R.drawable.ic_baseline_favorite_24);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductPage.class);
                intent.putExtra("uuid", product.getProduct_id());
                intent.putExtra("pos", holder.getAdapterPosition());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image, fav;
        TextView category, name, cost;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            fav = itemView.findViewById(R.id.fav);
            category = itemView.findViewById(R.id.category);
            name = itemView.findViewById(R.id.name);
            cost = itemView.findViewById(R.id.cost);
        }
    }
}
