package com.geekspace.a3decommerce.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekspace.a3decommerce.Adapter.ProductAdapter;
import com.geekspace.a3decommerce.Model.Category;
import com.geekspace.a3decommerce.Model.Product;
import com.geekspace.a3decommerce.R;

import java.util.ArrayList;


public class Favourite extends Fragment {

    private View view;
    private Context context;
    private ArrayList<Product> products=new ArrayList<>();
    private RecyclerView rec;
    public Favourite() {
    }

    public static Favourite newInstance() {
        Favourite fragment = new Favourite();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favourite, container, false);
        context=getContext();
        initComponents();
        setFavourites();
        return view;
    }

    private void setFavourites() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(context, 2);
        ProductAdapter productAdapter=new ProductAdapter(products,context);
        rec.setLayoutManager(gridLayoutManager);
        rec.setAdapter(productAdapter);
    }

    private void initComponents() {
        rec=view.findViewById(R.id.rec);
    }
}