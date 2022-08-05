package com.geekspace.a3decommerce.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.geekspace.a3decommerce.Common.Utils;
import com.geekspace.a3decommerce.R;
//import com.github.gabrielbb.cutout.CutOut;
//import com.slazzer.bgremover.Slazzer;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class AutoCrop extends AppCompatActivity {
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_crop);
        image=findViewById(R.id.image);
//        CutOut.activity().start(this);
//        Slazzer.INSTANCE.init("3a45e42b637740ca8dab865b619405f8");
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent, 1);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            ProgressDialog progressDialog=new ProgressDialog(this);
//
//            try {
//                Slazzer.INSTANCE.get(Utils.getFileFromUri(this, data.getData()), new Slazzer.ResponseCallback() {
//                    @Override
//                    public void onProgressStart() {
//                        progressDialog.show();
//                    }
//
//                    @Override
//                    public void onProgressEnd() {
//                        progressDialog.dismiss();
//                    }
//
//                    @Override
//                    public void onProgressUpdate(float v) {
//                        Log.e("OK",v+"");
//                    }
//
//                    @Override
//                    public void onSuccess(@NotNull Bitmap bitmap) {
//                        Toast.makeText(AutoCrop.this, "Success", Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                        image.setImageBitmap(bitmap);
//                    }
//
//                    @Override
//                    public void onError(@NotNull String s) {
//                        Toast.makeText(AutoCrop.this, s, Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                    }
//                });
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

//        }

    }
}