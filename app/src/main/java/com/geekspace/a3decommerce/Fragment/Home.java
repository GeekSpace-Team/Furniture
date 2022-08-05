package com.geekspace.a3decommerce.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geekspace.a3decommerce.Activity.MainPage;
import com.geekspace.a3decommerce.Adapter.CategoryAdapter;
import com.geekspace.a3decommerce.Adapter.ProductAdapter;
import com.geekspace.a3decommerce.Adapter.SliderAdapter;
import com.geekspace.a3decommerce.Api.APIClient;
import com.geekspace.a3decommerce.Api.ApiInterface;
import com.geekspace.a3decommerce.Common.Util;
import com.geekspace.a3decommerce.Common.Utils;
import com.geekspace.a3decommerce.Model.Banner;
import com.geekspace.a3decommerce.Model.GetHome;
import com.geekspace.a3decommerce.Model.HomeData;
import com.geekspace.a3decommerce.Model.Product;
import com.geekspace.a3decommerce.Model.Slider;
import com.geekspace.a3decommerce.R;
import com.geekspace.a3decommerce.Model.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home extends Fragment {


    private View view;
    private Context context;
    private ArrayList<Banner> sliders=new ArrayList<>();
    private ArrayList<Category> categories=new ArrayList<>();
    private ArrayList<Product> products=new ArrayList<>();
    private ViewPager sliderPager;
    private RecyclerView categoryRec,popularRec;
    private TextView see_all,see_all2;
    private ApiInterface apiInterface;
    private LinearLayout errorContainer;
    private ImageView errorImage;
    private TextView errorTitle,errorDesc;
    private Button errorButton;
    private NestedScrollView scroll;
    private ProductAdapter productAdapter;
    public Home() {
    }


    public static Home newInstance() {
        Home fragment = new Home();
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
        view=inflater.inflate(R.layout.fragment_home, container, false);
        context=getContext();
        initComponents();
//        requestHome();
        setSlider();
        setCategories();
        setPopular();
        setListener();
        MainPage.hideSplash(getActivity());
        hideError();
        return view;
    }

    private void requestHome() {
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        Call<GetHome> call=apiInterface.getHome();
        call.enqueue(new Callback<GetHome>() {
            @Override
            public void onResponse(Call<GetHome> call, Response<GetHome> response) {
                Log.e("Code",response.code()+"");
                if(response.isSuccessful()){
                    HomeData home=response.body().getData();
                    sliders=home.getBanners();
                    products=home.getProducts();
                    categories=home.getCategories();
                    setSlider();
                    setCategories();
                    setPopular();
                    setListener();
                    MainPage.hideSplash(getActivity());
                    hideError();
                }
            }

            @Override
            public void onFailure(Call<GetHome> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
                MainPage.hideSplash(getActivity());
                showError(R.drawable.no_internet,getResources().getString(R.string.noInternet),getResources().getString(R.string.errorDesc),getResources().getString(R.string.tryAgain));

            }
        });
    }

    private void showError(int image,String errorMessage,String errorTitleStr,String btnStr) {
        scroll.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
        errorImage.setImageResource(image);
        errorTitle.setText(errorTitleStr);
        errorDesc.setText(errorMessage);
        errorButton.setText(btnStr);
        errorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainPage.showSplash(getActivity());
                requestHome();
            }
        });
    }

    private void hideError(){
        scroll.setVisibility(View.VISIBLE);
        errorContainer.setVisibility(View.GONE);
    }

    private void setListener() {
        see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainPage.setBottomNavigationSelectItem(getActivity(),R.id.category);
            }
        });
        see_all2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.hideAdd(new Popular(), Popular.class.getSimpleName(),getFragmentManager(),R.id.content);
                MainPage.firstFragment=new Popular();
            }
        });

    }



    private void initComponents(){
        sliderPager=view.findViewById(R.id.sliderPager);
        categoryRec=view.findViewById(R.id.categoryRec);
        popularRec=view.findViewById(R.id.popularRec);
        see_all=view.findViewById(R.id.see_all);
        see_all2=view.findViewById(R.id.see_all2);
        scroll=view.findViewById(R.id.scroll);
        errorContainer=view.findViewById(R.id.errorContainer);
        errorImage=view.findViewById(R.id.errorImage);
        errorTitle=view.findViewById(R.id.errorTitle);
        errorDesc=view.findViewById(R.id.errorDesc);
        errorButton=view.findViewById(R.id.errorButton);
    }

    private void setSlider(){
        sliders.add(new Banner(1,"","https://img.freepik.com/free-photo/modern-living-room-interior-with-sofa-green-plants-lamp-table-dark-wall-background_41470-1528.jpg?size=626&ext=jpg&ga=GA1.2.747435451.1626393600","","","",""));
        sliders.add(new Banner(1,"","https://img.freepik.com/free-photo/modern-interior-of-living-room-blueprint-home-decor-concept-blue-sofa-and-black-lamp-on-white-flooring-and-dark-blueprint-wall-3d-rendering_33739-484.jpg?size=626&ext=jpg","","","",""));
        sliders.add(new Banner(1,"","https://img.freepik.com/free-photo/vintage-blue-room-interior-blue-sofa-on-white-flooring-3d-render_33739-99.jpg?size=626&ext=jpg","","","",""));
        SliderAdapter sliderAdapter=new SliderAdapter(context,sliders);
        sliderPager.setAdapter(sliderAdapter);
    }

    private void setCategories(){
        categories.add(new Category(1,"","https://cdn11.bigcommerce.com/s-a1aqxosd6a/images/stencil/1280x1280/products/20510/52580/B130-582__93008__79496__64491.1602724054.jpg?c=2","beds","","",""));
        categories.add(new Category(1,"","https://hgtvhome.sndimg.com/content/dam/images/hgtv/fullset/2017/8/15/1/IO_Suzanne-Childress_Avenue-Arts-Crafts_4.jpg.rend.hgtvcom.1280.720.suffix/1502820159896.jpeg","cabinets","","",""));
        categories.add(new Category(1,"","http://cdn.home-designing.com/wp-content/uploads/2021/08/designer-living-room-chair-with-subtle-armrests-minimalistic-modern-armchair-premium-upholstery-multiple-color-options-high-end-seating.jpg","chairs and seating","","",""));
        categories.add(new Category(1,"","https://www.hemswell-antiques.com/uploads/media/default/0002/26/thumb_125108_default_zoomed.jpeg","chests","","",""));
        categories.add(new Category(1,"","https://images.theconversation.com/files/401267/original/file-20210518-13-1a2rmfe.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=1200&h=900.0&fit=crop","clocks","","",""));
        categories.add(new Category(1,"","https://www.archiproducts.com/images/category-b/779@1x.jpg","desks","","",""));
        categories.add(new Category(1,"","https://www.archiproducts.com/images/category-b/779@1x.jpg","tables","","",""));
        categories.add(new Category(1,"","https://www.archiproducts.com/images/category-b/779@1x.jpg","beds","","",""));
        categories.add(new Category(1,"","https://www.archiproducts.com/images/category-b/779@1x.jpg","beds","","",""));
        LinearLayoutManager layoutManager=new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);
        CategoryAdapter adapter=new CategoryAdapter(categories,context);
        categoryRec.setLayoutManager(layoutManager);
        categoryRec.setAdapter(adapter);
    }

    private void setPopular(){
        products.add(new Product(1,"",2,"https://i.pinimg.com/736x/7b/7a/e6/7b7ae6371c1adcee2ff88de3d51e09b6.jpg","Modern chair","","",1000,2000,true,true));
        products.add(new Product(1,"",2,"https://p.kindpng.com/picc/s/4-42790_furniture-png-image-furniture-png-transparent-png.png","Modern chair","","",1000,2000,true,true));
        products.add(new Product(1,"",2,"https://i.pinimg.com/736x/7b/7a/e6/7b7ae6371c1adcee2ff88de3d51e09b6.jpg","Modern chair","","",1000,2000,true,true));
        products.add(new Product(1,"",2,"https://p.kindpng.com/picc/s/4-42790_furniture-png-image-furniture-png-transparent-png.png","Modern chair","","",1000,2000,true,true));

        GridLayoutManager gridLayoutManager=new GridLayoutManager(context, 2);
        productAdapter=new ProductAdapter(products,context);
        popularRec.setLayoutManager(gridLayoutManager);
        popularRec.setAdapter(productAdapter);
    }


}