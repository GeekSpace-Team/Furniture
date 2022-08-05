package com.geekspace.a3decommerce.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.geekspace.a3decommerce.Api.APIClient;
import com.geekspace.a3decommerce.Api.ApiInterface;
import com.geekspace.a3decommerce.DataBase.FavDB;
import com.geekspace.a3decommerce.Fragment.Home;
import com.geekspace.a3decommerce.Model.ImageType;
import com.geekspace.a3decommerce.Model.SingleProduct;
import com.geekspace.a3decommerce.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPage extends AppCompatActivity {
    private Context context = this;
    private LinearLayout typeContainer,linear;
    private ArrayList<ImageType> images = new ArrayList<>();
    private Button addToCard;
    private LinearLayout counter;
    private TextView minus, plus, count;
    private int productCount = 0;
    private SingleProduct singleProduct;
    private ApiInterface apiInterface;
    private String productId;
    private FavDB favDB;
    private ImageView image,fav;
    private TextView category,name,cost,oldCost,descTitle,desc;
    private Cursor favCursor;
    private int updatePosition=-1;
    private boolean isFav=false;
    private SkeletonScreen skeletonScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        Intent intent = getIntent();
        productId = intent.getStringExtra("uuid");
        updatePosition = intent.getIntExtra("pos",-1);
        initComponents();
        favDB=new FavDB(this);
//        request();
        setTypes();
        setListener();
    }

    private void request() {
        skeletonScreen = Skeleton.bind(linear)
                .load(R.layout.skeleton_product_page)
                .color(R.color.skeleton)
                .angle(10)
                .show();
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        Call<SingleProduct> call = apiInterface.getSingleProduct(productId);
        call.enqueue(new Callback<SingleProduct>() {
            @Override
            public void onResponse(Call<SingleProduct> call, Response<SingleProduct> response) {
                if (response.isSuccessful()) {
                    skeletonScreen.hide();
                    SingleProduct singleProduct=response.body();
                    String images2=singleProduct.getProduct_images();
                    String rotateImages=singleProduct.getProduct_rotating_image();
                    if(images2!=null){
                        String[] splitarray = images2.split(",");
                        for (int i = 2; i < splitarray.length; i++) {
                            images.add(new ImageType(0,splitarray[i],"default"));
                        }
                    }
                    oldCost.setPaintFlags(oldCost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    name.setText(singleProduct.getProduct_name_tm());
                    category.setText(singleProduct.getCategoryName());
                    double productCost=0;
                    if(singleProduct.getProduct_discount()>0){
                        productCost=singleProduct.getProduct_price()-singleProduct.getProduct_discount();
                        cost.setText(productCost+" TMT");
                        oldCost.setVisibility(View.VISIBLE);
                        oldCost.setText(singleProduct.getProduct_price()+" TMT");
                    } else{
                        cost.setText(singleProduct.getProduct_price()+" TMT");
                        oldCost.setVisibility(View.GONE);
                    }
                    desc.setText(singleProduct.getProduct_description_tm());

                    favCursor=favDB.getSelect(singleProduct.getProduct_id());

                    if (favCursor.getCount()>0) {
                        fav.setImageResource(R.drawable.ic_baseline_favorite_24);
                        isFav=true;
                    }

                    fav.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (favCursor.getCount()>0) {
                                fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                                favDB.deleteData(singleProduct.getProduct_id());
                                isFav=false;
                            } else {
                                fav.setImageResource(R.drawable.ic_baseline_favorite_24);
                                favDB.insertData(singleProduct.getProduct_id());
                                isFav=true;
                            }
                            favCursor=favDB.getSelect(singleProduct.getProduct_id());
                        }
                    });


                    setTypes();
                    setListener();
                }
            }

            @Override
            public void onFailure(Call<SingleProduct> call, Throwable t) {

            }
        });
    }

    private void setListener() {
        addToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.setVisibility(View.VISIBLE);
                addToCard.setVisibility(View.GONE);
                productCount = 1;
                count.setText(String.valueOf(productCount));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productCount--;
                if (productCount == 0) {
                    counter.setVisibility(View.GONE);
                    addToCard.setVisibility(View.VISIBLE);
                }
                count.setText(String.valueOf(productCount));
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productCount++;
                count.setText(String.valueOf(productCount));
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,MainActivity.class));
            }
        });


    }

    private void setTypes() {
        images.clear();
        images.add(new ImageType(1, "https://pg-cdn-a2.datacaciques.com/00/NzAzNg/19/11/27/zot475jvph5bgs73/588f96870254ed4b.jpg", "180"));
        images.add(new ImageType(1, "https://secure.img1-fg.wfcdn.com/im/02115025/compr-r85/1175/117503332/vero-22-w-velvet-armchair.jpg", "default"));
        images.add(new ImageType(1, "https://damro.com/wp-content/uploads/2020/06/roselsofa.jpg", "default"));
        images.add(new ImageType(1, "https://damro.lk/wp-content/uploads/2020/02/preston-sofa.jpg", "default"));
        images.add(new ImageType(1, "https://images-na.ssl-images-amazon.com/images/I/41o1kbVwk3L.jpg", "default"));
        images.add(new ImageType(1, "https://pg-cdn-a2.datacaciques.com/00/NzAzNg/19/11/27/zot475jvph5bgs73/588f96870254ed4b.jpg", "default"));
        typeContainer.removeAllViews();
        for (int i = 0; i < images.size(); i++) {
            ImageType image = images.get(i);
            View view = LayoutInflater.from(context).inflate(R.layout.image_type, typeContainer, false);
            ;
            View view2 = LayoutInflater.from(context).inflate(R.layout.one_hundred_eighty_degree, typeContainer, false);


            if (image.getType().equals("default")) {
                RoundedImageView imageView = view.findViewById(R.id.image);
                Glide.with(context)
                        .load(image.getImageUrl())
                        .into(imageView);
                LinearLayout.LayoutParams pr = new LinearLayout.LayoutParams(imageView.getLayoutParams());
                pr.setMargins(0, 0, 0, 0);
                if (i == 0)
                    imageView.setLayoutParams(pr);
                typeContainer.addView(view);

            } else if (image.getType().equals("180")) {
                RoundedImageView imageView2 = view2.findViewById(R.id.image);
                Glide.with(context)
                        .load(image.getImageUrl())
                        .into(imageView2);
                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, PartDegree.class);
                        startActivity(intent);
                    }
                });
                typeContainer.addView(view2);
            }


        }
    }

    private void initComponents() {
        typeContainer = findViewById(R.id.typeContainer);
        addToCard = findViewById(R.id.addToCard);
        counter = findViewById(R.id.counter);
        count = findViewById(R.id.count);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        image = findViewById(R.id.image);
        category = findViewById(R.id.category);
        name = findViewById(R.id.name);
        cost = findViewById(R.id.cost);
        oldCost = findViewById(R.id.oldCost);
        descTitle = findViewById(R.id.descTitle);
        desc = findViewById(R.id.desc);
        fav = findViewById(R.id.fav);
        linear = findViewById(R.id.linear);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}