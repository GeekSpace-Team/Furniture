package com.geekspace.a3decommerce.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekspace.a3decommerce.Adapter.BasketAdapter;
import com.geekspace.a3decommerce.Adapter.ProductAdapter;
import com.geekspace.a3decommerce.Model.Product;
import com.geekspace.a3decommerce.R;
import com.geekspace.a3decommerce.View.TouchDetectableScrollView;

import java.util.ArrayList;

import io.alterac.blurkit.BlurLayout;

public class Basket extends AppCompatActivity {
    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";
    private LinearLayout bottom,costs;
    private View rootLayout;
    private RecyclerView productRec;
    private int revealX;
    private int revealY;
    private Context context = this;
    private ArrayList<com.geekspace.a3decommerce.Model.Basket> products = new ArrayList<>();
    private TextView clearBasket,backTv;
    private ImageView backIcon,icon;
    private BlurLayout blurLayout;
    private Animation start,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        initComponents();
        final Intent intent = getIntent();
        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
            rootLayout.setVisibility(View.INVISIBLE);

            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);
            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        revealActivity(revealX, revealY);
                        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        } else {
            rootLayout.setVisibility(View.VISIBLE);
        }

        setOrders();
        setListener();
    }

    private void setListener() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        clearBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                products.clear();
                productRec.getAdapter().notifyDataSetChanged();
            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCostDialog();
            }
        });

        blurLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCostDialog();
            }
        });

        costs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCostDialog();
            }
        });


    }

    private void showCostDialog() {
        if(costs.getVisibility()==View.GONE){
            blur();
            costs.setVisibility(View.VISIBLE);
            icon.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
            costs.startAnimation(start);
        } else{
            unBlur();
            costs.setVisibility(View.GONE);
            icon.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
            costs.startAnimation(exit);
        }
    }

    private void blur() {
        blurLayout.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(blurLayout.getLayoutParams());
        lp.width= 10000;
        lp.height=10000;
        blurLayout.setLayoutParams(lp);
    }

    private void unBlur() {
        blurLayout.setVisibility(View.GONE);
    }

    private void initComponents() {
        productRec = findViewById(R.id.productRec);
        rootLayout = findViewById(R.id.root_layout);
        bottom = findViewById(R.id.bottom);
        backIcon = findViewById(R.id.backIcon);
        clearBasket = findViewById(R.id.clearBasket);
        backTv = findViewById(R.id.backTv);
        costs = findViewById(R.id.costs);
        blurLayout = findViewById(R.id.blurLayout);
        icon = findViewById(R.id.icon);


        start=AnimationUtils.loadAnimation(context, R.anim.slide_up);
        exit= AnimationUtils.loadAnimation(context, R.anim.slide_down);
    }

    protected void revealActivity(int x, int y) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);

            // create the animator for this view (the start radius is zero)
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
            circularReveal.setDuration(600);
            circularReveal.setInterpolator(new AccelerateInterpolator());

            // make the view visible and start the animation
            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
            circularReveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    rootLayout.setBackgroundColor(context.getResources().getColor(R.color.second));
                    bottom.setVisibility(View.VISIBLE);
                    productRec.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    protected void unRevealActivity() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            finish();
        } else {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                    rootLayout, revealX, revealY, finalRadius, 0);

            circularReveal.setDuration(600);
            circularReveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    rootLayout.setVisibility(View.INVISIBLE);
                    finish();
                }
            });


            circularReveal.start();
        }
    }

    @Override
    public void onBackPressed() {
        rootLayout.setBackgroundColor(getResources().getColor(R.color.first));
        bottom.setVisibility(View.GONE);
        productRec.setVisibility(View.GONE);
        unRevealActivity();
    }

    private void setOrders() {
        products.clear();
        products.add(new com.geekspace.a3decommerce.Model.Basket(1,"Roswell chair","https://ii1.pepperfry.com/media/catalog/product/r/o/800x880/royal-wing-chair-in-blue-color-by-dreamzz-furniture-royal-wing-chair-in-blue-color-by-dreamzz-furnit-6hcjya.jpg","Chair",850.0,1));
        products.add(new com.geekspace.a3decommerce.Model.Basket(1,"Roswell chair","https://ii1.pepperfry.com/media/catalog/product/r/o/800x880/royal-wing-chair-in-blue-color-by-dreamzz-furniture-royal-wing-chair-in-blue-color-by-dreamzz-furnit-6hcjya.jpg","Chair",850.0,1));
        products.add(new com.geekspace.a3decommerce.Model.Basket(1,"Roswell chair","https://ii1.pepperfry.com/media/catalog/product/r/o/800x880/royal-wing-chair-in-blue-color-by-dreamzz-furniture-royal-wing-chair-in-blue-color-by-dreamzz-furnit-6hcjya.jpg","Chair",850.0,1));
        products.add(new com.geekspace.a3decommerce.Model.Basket(1,"Roswell chair","https://ii1.pepperfry.com/media/catalog/product/r/o/800x880/royal-wing-chair-in-blue-color-by-dreamzz-furniture-royal-wing-chair-in-blue-color-by-dreamzz-furnit-6hcjya.jpg","Chair",850.0,1));
        products.add(new com.geekspace.a3decommerce.Model.Basket(1,"Roswell chair","https://ii1.pepperfry.com/media/catalog/product/r/o/800x880/royal-wing-chair-in-blue-color-by-dreamzz-furniture-royal-wing-chair-in-blue-color-by-dreamzz-furnit-6hcjya.jpg","Chair",850.0,1));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        BasketAdapter adapter=new BasketAdapter(products,context);
        productRec.setAdapter(adapter);
        productRec.setLayoutManager(linearLayoutManager);
    }

    private void pagination(){

    }
}