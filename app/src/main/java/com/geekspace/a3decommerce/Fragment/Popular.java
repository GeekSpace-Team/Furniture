package com.geekspace.a3decommerce.Fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geekspace.a3decommerce.Activity.MainPage;
import com.geekspace.a3decommerce.Adapter.ProductAdapter;
import com.geekspace.a3decommerce.Api.APIClient;
import com.geekspace.a3decommerce.Api.ApiInterface;
import com.geekspace.a3decommerce.Common.Utils;
import com.geekspace.a3decommerce.Model.PopularData;
import com.geekspace.a3decommerce.Model.PopularProduct;
import com.geekspace.a3decommerce.Model.Product;
import com.geekspace.a3decommerce.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.alterac.blurkit.BlurKit;
import io.alterac.blurkit.BlurLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Popular extends Fragment {

    private View view;
    private Context context;
    private RecyclerView productRec;
    private ArrayList<Product> products = new ArrayList<>();
    private ViewGroup viewGroup;
    private BlurLayout blurLayout;
    private float movement = 150;
    private View blur;
    private LinearLayout sortBtn, sortBtn2;
    private TextView backTv;
    private ImageView backIcon;
    private LinearLayout secondTop, sortCon;
    private NestedScrollView scrollView;
    private ApiInterface apiInterface;
    private LinearLayout errorContainer;
    private ImageView errorImage;
    private TextView errorTitle,errorDesc;
    private Button errorButton;
    private ProgressBar progress;
    public Popular() {
    }

    public static Popular newInstance() {
        Popular fragment = new Popular();
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
        viewGroup = container;
        view = inflater.inflate(R.layout.fragment_popular, container, false);
        context = getContext();
        initComponents();
        requestProducts();



        return view;
    }

    private void requestProducts() {
        progress.setVisibility(View.VISIBLE);
        apiInterface= APIClient.getClient().create(ApiInterface.class);
        Call<PopularProduct> call=apiInterface.getPopularProducts();
        call.enqueue(new Callback<PopularProduct>() {
            @Override
            public void onResponse(Call<PopularProduct> call, Response<PopularProduct> response) {
                if(response.isSuccessful()){
                    PopularProduct popularProduct=response.body();
                    PopularData popularData=popularProduct.getData();
                    products=popularData.getPopular_products();
                    setPopular();
                    setListener();
                    hideError();
                    if(products.size()==0){
                        showError(R.drawable.shopping_bag,getResources().getString(R.string.emptyTitle),getResources().getString(R.string.emptyMessage),getResources().getString(R.string.tryAgain));
                    }
                    progress.setVisibility(View.GONE);


                }
            }

            @Override
            public void onFailure(Call<PopularProduct> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
                progress.setVisibility(View.GONE);
                showError(R.drawable.no_internet,getResources().getString(R.string.noInternet),getResources().getString(R.string.errorDesc),getResources().getString(R.string.tryAgain));

            }
        });
    }

    private void showError(int image,String errorMessage,String errorTitleStr,String btnStr) {
        scrollView.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
        errorImage.setImageResource(image);
        errorTitle.setText(errorTitleStr);
        errorDesc.setText(errorMessage);
        errorButton.setText(btnStr);
        errorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainPage.showSplash(getActivity());
                requestProducts();
            }
        });
    }

    private void hideError(){
        scrollView.setVisibility(View.VISIBLE);
        errorContainer.setVisibility(View.GONE);
    }

    private void blur() {
        blurLayout.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(blurLayout.getLayoutParams());
        lp.width = 10000;
        lp.height = 10000;
        blurLayout.setLayoutParams(lp);
    }

    private void setListener() {
        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blur();
                bottomSheet();
            }
        });

        sortBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blur();
                bottomSheet();
            }
        });

        blurLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unBlur();
            }
        });

        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollX = scrollView.getScrollX();
                int scrollY = scrollView.getScrollY();
                if (scrollY >= sortCon.getY()) {
                    secondTop.setVisibility(View.VISIBLE);
                    sortCon.setVisibility(View.INVISIBLE);
                } else {
                    sortCon.setVisibility(View.VISIBLE);
                    secondTop.setVisibility(View.GONE);
                }

            }
        });
    }

    private void goBack() {
        Utils.removeShow(new Home(), Home.class.getSimpleName(), getFragmentManager(), R.id.content);
        MainPage.firstFragment = new Home();
    }

    private void unBlur() {
        blurLayout.setVisibility(View.GONE);
    }

    private void initComponents() {
        productRec = view.findViewById(R.id.productRec);
        blurLayout = view.findViewById(R.id.blurLayout);
        sortBtn = view.findViewById(R.id.sortBtn);
        sortBtn2 = view.findViewById(R.id.sortBtn2);
        backTv = view.findViewById(R.id.backTv);
        backIcon = view.findViewById(R.id.backIcon);
        sortCon = view.findViewById(R.id.sortCon);
        secondTop = view.findViewById(R.id.secondTop);
        scrollView = view.findViewById(R.id.scrollView);
        errorContainer=view.findViewById(R.id.errorContainer);
        errorImage=view.findViewById(R.id.errorImage);
        errorTitle=view.findViewById(R.id.errorTitle);
        errorDesc=view.findViewById(R.id.errorDesc);
        errorButton=view.findViewById(R.id.errorButton);
        progress=view.findViewById(R.id.progress);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void setPopular() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        ProductAdapter productAdapter = new ProductAdapter(products, context);
        productRec.setLayoutManager(gridLayoutManager);
        productRec.setAdapter(productAdapter);
    }

    private void bottomSheet() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.sort_dialog);
        bottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

            }
        });
        bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                unBlur();
            }
        });
        bottomSheetDialog.show();
    }
}