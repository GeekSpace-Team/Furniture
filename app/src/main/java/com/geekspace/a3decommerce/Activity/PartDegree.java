package com.geekspace.a3decommerce.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.geekspace.a3decommerce.R;
import com.geekspace.a3decommerce.Task.LoadImageTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PartDegree extends AppCompatActivity implements LoadImageTask.Listener {
    private ImageView image;
    ArrayList<String> listImages = new ArrayList<>();
    int position = 0;
    public static final String DEBUG_TAG = "GESTURE";
    private float oldX = -100000;
    ArrayList<Bitmap> bitmaps = new ArrayList<>();
    Context context = this;
    int in = 0;
    ProgressDialog progressDialog;
    private ArrayList<LoadImageTask> tasks=new ArrayList<>();
    private NumberProgressBar numberProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_degree);
        initComponents();
        loadImages();
    }


    private void loadImages() {

//        progressDialog = new ProgressDialog(context);
//        progressDialog.setTitle("Uns Berin");
//        progressDialog.setMessage("Suratlar yuklenyar");
//        progressDialog.setCancelable(true);
//        progressDialog.show();
        listImages.clear();
        bitmaps.clear();
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(100).png?alt=media&token=2f2bc6b8-99d2-40a1-80db-ca71369dc38b");
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(101).png?alt=media&token=12039a2d-3d62-4a6b-8929-82c630603ade");
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(102).png?alt=media&token=04a98f32-45d3-4487-951e-afd2400cd78b");
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(103).png?alt=media&token=19e10a26-28c9-4f1c-84c2-a496e42b6bfc");
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(104).png?alt=media&token=c9f9c704-d3ad-493a-ba6d-f4e5af095842");
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(105).png?alt=media&token=db8d1f86-d63d-4d26-93de-8ccddb2cb31d");
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(106).png?alt=media&token=8ccea1ad-c4c6-474e-baf0-0ec279d9a0b8");
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(107).png?alt=media&token=415ba7b6-ce75-48dc-8204-16382c8cc4c8");
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(108).png?alt=media&token=9df16f0e-00c7-4a65-abf1-26893ad7df78");
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(109).png?alt=media&token=42dc6377-a4d0-4b84-a432-75b3631391b5");
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(110).png?alt=media&token=21085da5-fbd5-4f47-a852-341b34b4267e");
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(111).png?alt=media&token=ce3fa5e9-c0db-4825-9ec1-a2e14aa2e09a");
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(112).png?alt=media&token=de22748e-804f-47fc-9d21-cc15ed7ab631");
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(113).png?alt=media&token=25322221-1c78-4df1-948d-43546b5f3aae");
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(114).png?alt=media&token=ed7403c1-9529-4502-8122-061094243dab");
        listImages.add("https://firebasestorage.googleapis.com/v0/b/calculator-6d99b.appspot.com/o/Screenshot%20(115).png?alt=media&token=2d3857a5-0ac8-48e7-9639-f14bf8767648");

        tasks.clear();

        for (String image1 : listImages) {
            LoadImageTask loadImageTask=new LoadImageTask(this);
            loadImageTask.execute(image1);
            tasks.add(loadImageTask);

        }



    }

    private void setImage(int pos) {
        if(pos<0)
            return;
        if(pos>=bitmaps.size())
            return;
        image.setImageBitmap(bitmaps.get(pos));
    }


    private void setPercentage(int i){
        int percentage=(i*100)/listImages.size();
        Log.e("Percent",percentage+"");
        numberProgressBar.setProgress(percentage);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (event.getRawX() > oldX) {
                    position++;
                } else if (event.getRawX() < oldX) {
                    position--;
                }
                oldX = event.getRawX();
                setImage(position);
                break;
        }
        return super.onTouchEvent(event);
    }

    private void initComponents() {
        image = findViewById(R.id.image);
        numberProgressBar = findViewById(R.id.numberProgressBar);
    }

    @Override
    protected void onStop() {
        super.onStop();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        for(LoadImageTask task:tasks){
            task.cancel(true);
        }
    }


    @Override
    public void onImageLoaded(Bitmap bitmap) {
        image.setImageBitmap(bitmap);
        bitmaps.add(bitmap);
        in++;

        if (in == listImages.size()) {
//            progressDialog.dismiss();
            int pos = listImages.size() / 2;
            position = pos;
            setImage(pos);
            setPercentage(in);
            numberProgressBar.setVisibility(View.GONE);
        } else{
            setPercentage(in);
        }
        Log.e("Item",in+" / "+listImages.size());
    }

    @Override
    public void onError() {
        Log.e("Error","error");
    }
}