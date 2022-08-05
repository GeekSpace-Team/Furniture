package com.geekspace.a3decommerce.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.geekspace.a3decommerce.Adapter.CategoryAdapter;
import com.geekspace.a3decommerce.Adapter.CategoryLargeAdapter;
import com.geekspace.a3decommerce.Api.APIClient;
import com.geekspace.a3decommerce.Api.ApiInterface;
import com.geekspace.a3decommerce.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Category extends Fragment {
    private View view;
    private Context context;
    private ArrayList<com.geekspace.a3decommerce.Model.Category> categories = new ArrayList<>();
    private RecyclerView rec;
    private ApiInterface apiInterface;
    private CategoryLargeAdapter adapter;
    public Category() {
    }


    public static Category newInstance() {
        Category fragment = new Category();
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
        view = inflater.inflate(R.layout.fragment_category, container, false);
        context=getContext();
        initComponents();
        setCategories();
//        requestCategory();
        return view;
    }

    private void requestCategory(){
        adapter=new CategoryLargeAdapter(categories,context);
        rec.setLayoutManager(new GridLayoutManager(context, 2));
        SkeletonScreen skeletonScreen= Skeleton.bind(rec)
                .adapter(adapter)
                .count(8)
                .color(R.color.skeletonColor)
                .load(R.layout.skelton_category_large)
                .show();
        apiInterface= APIClient.getClient().create(ApiInterface.class);
        Call<ArrayList<com.geekspace.a3decommerce.Model.Category>> call=apiInterface.getAllCategories();
        call.enqueue(new Callback<ArrayList<com.geekspace.a3decommerce.Model.Category>>() {
            @Override
            public void onResponse(Call<ArrayList<com.geekspace.a3decommerce.Model.Category>> call, Response<ArrayList<com.geekspace.a3decommerce.Model.Category>> response) {
                if(response.isSuccessful()){
                    categories=response.body();
                    setCategories();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<com.geekspace.a3decommerce.Model.Category>> call, Throwable t) {
                skeletonScreen.hide();
                call.cancel();
                t.printStackTrace();
            }
        });
    }

    private void initComponents() {
        rec=view.findViewById(R.id.rec);
    }
    private void setCategories(){
        categories.add(new com.geekspace.a3decommerce.Model.Category(
                1,
                "1",
                "https://media.architecturaldigest.com/photos/5fe250a2c3b8b23d761d5a38/3:2/w_797,h_531,c_limit/Millicent+Upholstered+Storage+Standard+Bed.jpg",
                "beds",
                "beds",
                "",
                ""));
        categories.add(new com.geekspace.a3decommerce.Model.Category(
                1,
                "1",
                "https://www.thespruce.com/thmb/3KNdslPY-2Pf27raQPU53umdqCI=/2048x1152/smart/filters:no_upscale()/guide-to-common-kitchen-cabinet-sizes-1822029-hero-08f8ed3104a74600839ac5ef7471372e.jpg",
                "cabinets",
                "cabinets",
                "",
                ""));
        categories.add(new com.geekspace.a3decommerce.Model.Category(
                1,
                "1",
                "https://www.vari.com/on/demandware.static/-/Library-Sites-VariGlobalContentLibrary/default/dw648c8037/sub-cat-banner/vari-soft-seating_collection4-casual-meeting.jpg",
                "chairs and seating",
                "chairs and seating",
                "",
                ""));
        categories.add(new com.geekspace.a3decommerce.Model.Category(
                1,
                "1",
                "https://www.hemswell-antiques.com/uploads/media/default/0002/26/thumb_125108_default_zoomed.jpeg",
                "chests",
                "chests",
                "",
                ""));
        categories.add(new com.geekspace.a3decommerce.Model.Category(
                1,
                "1",
                "https://images.theconversation.com/files/401267/original/file-20210518-13-1a2rmfe.jpg?ixlib=rb-1.1.0&rect=371%2C100%2C3914%2C1957&q=45&auto=format&w=1356&h=668&fit=crop",
                "clocks",
                "clocks",
                "",
                ""));
        categories.add(new com.geekspace.a3decommerce.Model.Category(
                1,
                "1",
                "https://cdn.shopify.com/s/files/1/0124/5662/4187/files/sd-nav-final.jpg?v=1613277558",
                "desks",
                "desks",
                "",
                ""));
        categories.add(new com.geekspace.a3decommerce.Model.Category(
                1,
                "1",
                "https://ii1.pepperfry.com/media/catalog/product/f/o/568x625/foldable-table-in-in-brown-colour-by-clasicraft-foldable-table-in-in-brown-colour-by-clasicraft-ecohp3.jpg",
                "tables",
                "tables",
                "",
                ""));
        categories.add(new com.geekspace.a3decommerce.Model.Category(
                1,
                "1",
                "https://ideas.rejuvenation.com/content/images/2016/07/Bent-Wood-Chairs-1.jpg",
                "bentwood furniture",
                "bentwood furniture",
                "",
                ""));
        categories.add(new com.geekspace.a3decommerce.Model.Category(
                1,
                "1",
                "https://howelondon.com/wp-content/uploads/2020/07/Bobbin-Leg-Dining-Chairs-0025-e1616765375578.jpg",
                "bobbin furniture",
                "bobbin furniture",
                "",
                ""));
        categories.add(new com.geekspace.a3decommerce.Model.Category(
                1,
                "1",
                "https://www.africansportingcreations.com/mm5/graphics/00000001/chair-side-table-package.jpg",
                "campaign furniture",
                "campaign furniture",
                "",
                ""));
        categories.add(new com.geekspace.a3decommerce.Model.Category(
                1,
                "1",
                "https://image.made-in-china.com/2f0j00RdtQpwaFqVrT/Nature-Cane-Furniture-Luxury-Natural-Rattan-Weaving-Coffee-Set-Garden-Table-and-Chairs.jpg",
                "cane furniture",
                "cane furniture",
                "",
                ""));

        GridLayoutManager gridLayoutManager=new GridLayoutManager(context, 2);
        adapter=new CategoryLargeAdapter(categories,context);
        rec.setLayoutManager(gridLayoutManager);
        rec.setAdapter(adapter);
    }
}