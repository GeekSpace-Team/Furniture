package com.geekspace.a3decommerce.Parahat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.geekspace.a3decommerce.R;

import java.util.ArrayList;

public class KoshiPerdeler extends AppCompatActivity {
    private ArrayList<Curtain> curtains=new ArrayList<>();
    private Context context=this;
    private RecyclerView rec,recTwoD;
    private ArrayList<ProductParahat> productParahats=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koshi_perdeler);
        rec=findViewById(R.id.rec);
        recTwoD=findViewById(R.id.recTwoD);
        curtains.add(new Curtain(1,
                "1",
                1,
                R.drawable.a,
                "Perde",
                "Perde",
                "Perdeler",
                113.0,
                120.0,
                true,
                false,
                "a"));

        curtains.add(new Curtain(1,
                "1",
                1,
                R.drawable.b,
                "Perde",
                "Perde",
                "Perdeler",
                113.0,
                120.0,
                true,
                false,
                "b"));

        curtains.add(new Curtain(1,
                "1",
                1,
                R.drawable.c,
                "Perde",
                "Perde",
                "Perdeler",
                113.0,
                120.0,
                true,
                false,
                "c"));

        curtains.add(new Curtain(1,
                "1",
                1,
                R.drawable.d,
                "Perde",
                "Perde",
                "Perdeler",
                113.0,
                120.0,
                true,
                false,
                "d"));

        curtains.add(new Curtain(1,
                "1",
                1,
                R.drawable.e,
                "Perde",
                "Perde",
                "Perdeler",
                113.0,
                120.0,
                true,
                false,
                "e"));


        curtains.add(new Curtain(1,
                "1",
                1,
                R.drawable.ic_chair1,
                "Stul",
                "Stul",
                "Mebel",
                113.0,
                120.0,
                true,
                false,
                "chair"));


        rec.setAdapter(new CurtainAdapter(curtains,context));
        rec.setLayoutManager(new LinearLayoutManager(context));


        productParahats.add(new ProductParahat(
                1,
                "1",
                1,
                R.drawable.pl1,
                "Perde",
                "Perde",
                "Perdeler",
                123.0,
                100.0,
                true,
                true,
                "https://firebasestorage.googleapis.com/v0/b/d-ecommerce-a32f1.appspot.com/o/png1.png?alt=media&token=9feadc6e-ab41-4a0c-966c-a0e43d2abe04"
        ));

        productParahats.add(new ProductParahat(
                1,
                "1",
                1,
                R.drawable.pl2,
                "Perde",
                "Perde",
                "Perdeler",
                123.0,
                100.0,
                true,
                true,
                "https://firebasestorage.googleapis.com/v0/b/d-ecommerce-a32f1.appspot.com/o/png2.png?alt=media&token=9ea852ff-790f-40f5-bda4-2da72c955a1d"
        ));

        productParahats.add(new ProductParahat(
                1,
                "1",
                1,
                R.drawable.pl3,
                "Perde",
                "Perde",
                "Perdeler",
                123.0,
                100.0,
                true,
                true,
                "https://firebasestorage.googleapis.com/v0/b/d-ecommerce-a32f1.appspot.com/o/png3.png?alt=media&token=70b02c92-bf1e-4974-86d3-fe1972b27f0b"
        ));

        productParahats.add(new ProductParahat(
                1,
                "1",
                1,
                R.drawable.pl4,
                "Perde",
                "Perde",
                "Perdeler",
                123.0,
                100.0,
                true,
                true,
                "https://firebasestorage.googleapis.com/v0/b/d-ecommerce-a32f1.appspot.com/o/png4.png?alt=media&token=b943a78e-b55b-4c41-840a-97081160e511"
        ));

        recTwoD.setAdapter(new ProductAdapter(productParahats,context));
        recTwoD.setLayoutManager(new GridLayoutManager(context,2));


    }
}