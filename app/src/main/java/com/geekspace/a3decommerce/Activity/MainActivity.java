package com.geekspace.a3decommerce.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.geekspace.a3decommerce.Adapter.AddImageAdapter;
import com.geekspace.a3decommerce.BuildConfig;
import com.geekspace.a3decommerce.Camera.ImageFilterAdapter;
import com.geekspace.a3decommerce.Camera.ImageFilterClass;
import com.geekspace.a3decommerce.Common.Constant;
import com.geekspace.a3decommerce.Common.RotateZoomImageView;
import com.geekspace.a3decommerce.Furniture.FurnitureTypeAdapter;
import com.geekspace.a3decommerce.Furniture.TypeClass;
import com.geekspace.a3decommerce.R;
import com.geekspace.a3decommerce.Common.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;
import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.controls.Flash;
import com.otaliastudios.cameraview.controls.Grid;
import com.otaliastudios.cameraview.controls.Mode;
import com.otaliastudios.cameraview.filter.Filters;
import com.otaliastudios.cameraview.gesture.Gesture;
import com.otaliastudios.cameraview.gesture.GestureAction;
import com.zhy.base.fileprovider.FileProvider7;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


import static android.os.Build.VERSION.SDK_INT;

public class MainActivity extends AppCompatActivity {
    CameraView camera;
    ImageView cameraSwitch;
    RotateZoomImageView image;
    ImageView takenImage, panorama;
    float xDown = 0, yDown = 0;
    RecyclerView filterRec, furnitureTypeRec;
    RelativeLayout gridCon, container;
    float flash = 0;
    ArrayList<ImageFilterClass> imageFilterClassArrayList = new ArrayList<>();
    ImageView cameraFlash, cameraGrid;
    TextView gridTxt;
    LinearLayout cameraFilterBtn, furnitureTypesBtn, takePhoto;
    ArrayList<TypeClass> furnitureTypes = new ArrayList<>();
    private boolean isTakePhoto = false;
    private int countSnap = 0;
    Button saveImage;
    FurnitureTypeAdapter adapter;
    private Context context = this;
    private ImageView add_image;
    private ArrayList<TypeClass> addImages = new ArrayList<>();
    RotateZoomImageView selectedImage;
    private Uri imageUri;

    public static String mainImage="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        setContentView(R.layout.activity_main);
        initComponents();
        setListeners();
        startCamera();
        setFilter();
        imageProcessing();
    }

    private void initComponents() {
        camera = findViewById(R.id.camera);
        cameraSwitch = findViewById(R.id.cameraSwitch);
        image = findViewById(R.id.image);
        takenImage = findViewById(R.id.takenImage);
        filterRec = findViewById(R.id.filterRec);
        cameraFlash = findViewById(R.id.cameraFlash);
        cameraGrid = findViewById(R.id.cameraGrid);
        gridTxt = findViewById(R.id.gridTxt);
        cameraFilterBtn = findViewById(R.id.cameraFilterBtn);
        furnitureTypesBtn = findViewById(R.id.furnitureTypesBtn);
        furnitureTypeRec = findViewById(R.id.furnitureTypeRec);
        gridCon = findViewById(R.id.gridCon);
        takePhoto = findViewById(R.id.takePhoto);
        container = findViewById(R.id.container);
        saveImage = findViewById(R.id.saveImage);
        add_image = findViewById(R.id.add_image);
        panorama = findViewById(R.id.panorama);
    }


    private void setListeners() {
        furnitureTypesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (furnitureTypeRec.getVisibility() == View.GONE) {
                    setFurnitureTypes(context, image);
                    filterRec.setVisibility(View.GONE);
                    furnitureTypeRec.setVisibility(View.VISIBLE);
                    SkeletonScreen skeletonScreen = Skeleton.bind(furnitureTypeRec)
                            .adapter(adapter)
                            .load(R.layout.skelton_person)
                            .show();

                    skeletonScreen.hide();


                } else if (furnitureTypeRec.getVisibility() == View.VISIBLE) {
                    furnitureTypeRec.setVisibility(View.GONE);
                    filterRec.setVisibility(View.GONE);
                }
            }
        });

        cameraFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filterRec.getVisibility() == View.GONE) {
                    setFilter();
                    filterRec.setVisibility(View.VISIBLE);
                    furnitureTypeRec.setVisibility(View.GONE);
                } else if (filterRec.getVisibility() == View.VISIBLE) {
                    filterRec.setVisibility(View.GONE);
                    furnitureTypeRec.setVisibility(View.GONE);
                }
            }
        });

        camera.takePictureSnapshot();

        takePhoto.setOnClickListener(view -> {
            isTakePhoto = true;
            hideButtons();
            furnitureTypeRec.setVisibility(View.GONE);
            furnitureTypesBtn.setVisibility(View.GONE);


            camera.takePictureSnapshot();


        });

        saveImage.setOnClickListener(view -> {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        11);

            } else {
                save_toMediaStore();
            }
        });

        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });

        panorama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Degree.class);
                startActivity(intent);
            }
        });
    }

    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);
        return b;
    }

    private void imageProcessing() {
        Glide.with(this)
                .load("https://static.wixstatic.com/media/2cd43b_a76059d71b564ffeb8bfb8f0cdf48180~mv2.png/v1/fill/w_320,h_341,q_90/2cd43b_a76059d71b564ffeb8bfb8f0cdf48180~mv2.png")
                .into(image);

        image.setOnTouchListener((v, event) -> {
            setFurnitureTypes(context, image);
            if (image.getRotation() >= 360 || image.getRotation() <= -360) {
                image.setRotation(0);
            }
            if ((image.getRotation() >= 0 && image.getRotation() <= 10) || (image.getRotation() > -10 && image.getRotation() <= 0)) {
                image.setRotation(0);
            }
            if ((image.getRotation() <= 360 && image.getRotation() >= 350) || (image.getRotation() < -350 && image.getRotation() <= -360)) {
                image.setRotation(0);
            }

            return image.onTouch(v, event);
        });
    }


    private void hideButtons() {
        gridCon.setVisibility(View.GONE);
        cameraFlash.setVisibility(View.GONE);
        cameraSwitch.setVisibility(View.GONE);
        filterRec.setVisibility(View.GONE);
        cameraFilterBtn.setVisibility(View.GONE);
        takePhoto.setVisibility(View.GONE);
        add_image.setVisibility(View.GONE);
        panorama.setVisibility(View.GONE);
    }

    private void showButtons() {
        gridCon.setVisibility(View.VISIBLE);
        cameraFlash.setVisibility(View.VISIBLE);
        cameraSwitch.setVisibility(View.VISIBLE);
        cameraFilterBtn.setVisibility(View.VISIBLE);
        takePhoto.setVisibility(View.VISIBLE);
        add_image.setVisibility(View.VISIBLE);
        panorama.setVisibility(View.VISIBLE);
        saveImage.setVisibility(View.GONE);
    }

    private void startCamera() {
        camera.setLifecycleOwner(this);
        camera.mapGesture(Gesture.PINCH, GestureAction.ZOOM); // Pinch to zoom!
        camera.mapGesture(Gesture.TAP, GestureAction.TAKE_PICTURE_SNAPSHOT); // Long tap to shoot!
        camera.setMode(Mode.PICTURE);
        camera.setUseDeviceOrientation(false);

        camera.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(@NonNull @NotNull PictureResult result) {
                if (result.getData().length > 0) {
                    takenImage.setImageBitmap(Utils.getImage(result.getData()));
                    takenImage.setVisibility(View.VISIBLE);
                    hideButtons();

                    if (isTakePhoto) {
//                        Bitmap bitmap = loadBitmapFromView(container);
//                        takenImage.setImageBitmap(bitmap);
//                        image.setVisibility(View.GONE);
                        isTakePhoto = false;
                        saveImage.setVisibility(View.VISIBLE);
                    }
                    countSnap++;
                }

            }
        });

        takenImage.setOnClickListener(view -> {
            takenImage.setVisibility(View.GONE);
            showButtons();
            image.setVisibility(View.VISIBLE);
            furnitureTypesBtn.setVisibility(View.VISIBLE);
        });

        cameraSwitch.setOnClickListener(view -> {
            if (camera.getFacing().equals(Facing.BACK)) {
                camera.setFacing(Facing.FRONT);
            } else {
                camera.setFacing(Facing.BACK);
            }
        });

        cameraGrid.setOnClickListener(view -> changeGrid());
        gridTxt.setOnClickListener(view -> changeGrid());

        camera.setFlash(Flash.OFF);
        camera.setGrid(Grid.DRAW_3X3);


        takenImage.setImageResource(R.drawable.auto_fix);

    }

    private void changeGrid() {
        if (camera.getGrid().equals(Grid.DRAW_3X3)) {
            camera.setGrid(Grid.DRAW_4X4);
            gridTxt.setText("4X4");
        } else if (camera.getGrid().equals(Grid.DRAW_4X4)) {
            camera.setGrid(Grid.DRAW_PHI);
            gridTxt.setText("PHI");
        } else if (camera.getGrid().equals(Grid.DRAW_PHI)) {
            camera.setGrid(Grid.OFF);
            gridTxt.setText("OFF");
        } else if (camera.getGrid().equals(Grid.OFF)) {
            camera.setGrid(Grid.DRAW_3X3);
            gridTxt.setText("3X3");
        }
    }

    public void setFurnitureTypes(Context context, RotateZoomImageView selectedImage) {
        furnitureTypes.clear();
//        furnitureTypes.add(new TypeClass("1", "Perde", "https://firebasestorage.googleapis.com/v0/b/d-ecommerce-a32f1.appspot.com/o/png4.png?alt=media&token=b943a78e-b55b-4c41-840a-97081160e511"));
//        furnitureTypes.add(new TypeClass("2","Perde","https://firebasestorage.googleapis.com/v0/b/d-ecommerce-a32f1.appspot.com/o/png1.png?alt=media&token=9feadc6e-ab41-4a0c-966c-a0e43d2abe04"));
//        furnitureTypes.add(new TypeClass("3","Perde","https://firebasestorage.googleapis.com/v0/b/d-ecommerce-a32f1.appspot.com/o/png2.png?alt=media&token=9ea852ff-790f-40f5-bda4-2da72c955a1d"));
//        furnitureTypes.add(new TypeClass("4","Perde","https://firebasestorage.googleapis.com/v0/b/d-ecommerce-a32f1.appspot.com/o/png3.png?alt=media&token=70b02c92-bf1e-4974-86d3-fe1972b27f0b"));
//
        furnitureTypes.add(new TypeClass(
                "1",
                "Mebel",
                "https://static.wixstatic.com/media/2cd43b_a76059d71b564ffeb8bfb8f0cdf48180~mv2.png/v1/fill/w_320,h_341,q_90/2cd43b_a76059d71b564ffeb8bfb8f0cdf48180~mv2.png"
        ));
        furnitureTypes.add(new TypeClass(
                "2",
                "Mebel",
                "https://gallery.yopriceville.com/var/albums/Free-Clipart-Pictures/Furniture-PNG/Transparent_Patio_Sofa_PNG_Picture.png?m=1434276749"
        ));
        furnitureTypes.add(new TypeClass(
                "3",
                "Mebel",
                "https://static.wixstatic.com/media/2cd43b_810e23b956eb43a0ae60d365813eaf70~mv2.png/v1/fill/w_320,h_157,q_90/2cd43b_810e23b956eb43a0ae60d365813eaf70~mv2.png"
        ));
        furnitureTypes.add(new TypeClass(
                "4",
                "Mebel",
                "https://gallery.yopriceville.com/var/albums/Free-Clipart-Pictures/Furniture-PNG/Transparent_Beige_Sofa_PNG_Picture.png?m=1434276749"
        ));
        furnitureTypes.add(new TypeClass(
                "5",
                "Mebel",
                "https://theme.mybobs.com/_ui/desktop/common/bobs/images/Desktop_321x321_login.png"
        ));
        adapter = new FurnitureTypeAdapter(context, furnitureTypes, selectedImage);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView rec = ((Activity) context).findViewById(R.id.furnitureTypeRec);
        rec.setLayoutManager(layoutManager);
        rec.setAdapter(adapter);


    }

    private void setFilter() {
        imageFilterClassArrayList.clear();
        imageFilterClassArrayList.add(new ImageFilterClass("None", R.drawable.normal, Filters.NONE.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("AUTO_FIX", R.drawable.auto_fix, Filters.AUTO_FIX.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("BLACK_AND_WHITE", R.drawable.black_white, Filters.BLACK_AND_WHITE.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("BRIGHTNESS", R.drawable.brightnes, Filters.BRIGHTNESS.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("CONTRAST", R.drawable.contrast, Filters.CONTRAST.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("CROSS_PROCESS", R.drawable.cross, Filters.CROSS_PROCESS.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("DOCUMENTARY", R.drawable.document, Filters.DOCUMENTARY.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("DUOTONE", R.drawable.duotone, Filters.DUOTONE.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("FILL_LIGHT", R.drawable.fill, Filters.FILL_LIGHT.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("GAMMA", R.drawable.gamma, Filters.GAMMA.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("GRAIN", R.drawable.gray, Filters.GRAIN.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("GRAYSCALE", R.drawable.gray, Filters.GRAYSCALE.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("HUE", R.drawable.hue, Filters.HUE.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("INVERT_COLORS", R.drawable.invert_colors, Filters.INVERT_COLORS.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("LOMOISH", R.drawable.lomoish, Filters.LOMOISH.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("POSTERIZE", R.drawable.posterize, Filters.POSTERIZE.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("SATURATION", R.drawable.saturation, Filters.SATURATION.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("SEPIA", R.drawable.sepia, Filters.SEPIA.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("SHARPNESS", R.drawable.sharpen, Filters.SHARPNESS.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("TEMPERATURE", R.drawable.temperature, Filters.TEMPERATURE.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("TINT", R.drawable.tint, Filters.TINT.newInstance()));
        imageFilterClassArrayList.add(new ImageFilterClass("VIGNETTE", R.drawable.vignette, Filters.VIGNETTE.newInstance()));
        ImageFilterAdapter adapter = new ImageFilterAdapter(this, imageFilterClassArrayList, camera);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        filterRec.setLayoutManager(layoutManager);
        filterRec.setAdapter(adapter);


    }

    public void setFlash(View view) {
        if (camera.getFlash().equals(Flash.OFF)) {
            camera.setFlash(Flash.ON);
            cameraFlash.setImageResource(R.drawable.ic_baseline_flash_on_24);
        } else if (camera.getFlash().equals(Flash.ON)) {
            camera.setFlash(Flash.AUTO);
            cameraFlash.setImageResource(R.drawable.ic_baseline_flash_auto_24);
        } else if (camera.getFlash().equals(Flash.AUTO)) {
            camera.setFlash(Flash.TORCH);
            cameraFlash.setImageResource(R.drawable.ic_baseline_highlight_24);
        } else if (camera.getFlash().equals(Flash.TORCH)) {
            camera.setFlash(Flash.OFF);
            cameraFlash.setImageResource(R.drawable.ic_baseline_flash_off_24);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    1);
        } else {
            camera.open();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        camera.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        camera.destroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            camera.open();
        }
        if (requestCode == 11 && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            save_toMediaStore();
        }
    }

    private void gotoShare() {
        hideDeleteButton();
        saveImage.setVisibility(View.GONE);
        Bitmap bitmap = loadBitmapFromView(container);
        saveImage.setVisibility(View.VISIBLE);
        showDeleteButton();
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator;
        File myDir = new File(root);
        myDir.mkdirs();
        Random generator = new Random();
        int n = 100000;
        n = generator.nextInt(n);
        String fname = n + ".jpg";
        File file = new File(myDir, fname);
        Log.i("IMAGE", "" + file);
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

            Intent sendIntent;
            sendIntent = new Intent();
            // uri =
            imageUri = Uri.fromFile(file);

            if (SDK_INT > Build.VERSION_CODES.N) {
                imageUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file);
            }
            if (!imageUri.toString().isEmpty()) {
                Log.d("URI", imageUri.toString());
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                sendIntent.setType("image/jpg");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                this.startActivity(shareIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void save_toMediaStore() {
        hideDeleteButton();
        saveImage.setVisibility(View.GONE);
        Bitmap bitmap = loadBitmapFromView(container);
        saveImage.setVisibility(View.VISIBLE);
        showDeleteButton();
        Random generator = new Random();
        int n = 100000;
        n = generator.nextInt(n);

        try {
            saveImage(bitmap, n + "");
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            String type = mime.getMimeTypeFromExtension("image/*");
            Intent intent = new Intent(Intent.ACTION_VIEW);
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), n + ".jpg");
            FileProvider7.setIntentDataAndType(MainActivity.this,
                    intent, type, file, true);

            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void save() {
        hideDeleteButton();
        saveImage.setVisibility(View.GONE);
        Bitmap bitmap = loadBitmapFromView(container);
        saveImage.setVisibility(View.VISIBLE);
        showDeleteButton();
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator;
        File myDir = new File(root);
        myDir.mkdirs();
        Random generator = new Random();
        int n = 100000;
        n = generator.nextInt(n);
        String fname = n + ".jpg";
        File file = new File(myDir, fname);
        Log.i("IMAGE", "" + file);
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

            Intent sendIntent;
            sendIntent = new Intent();
            // uri =
            imageUri = Uri.fromFile(file);

            if (SDK_INT > Build.VERSION_CODES.N) {
                imageUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".android7.fileprovider", file);
            }
            if (!imageUri.toString().isEmpty()) {
                MimeTypeMap mime = MimeTypeMap.getSingleton();
                String type = mime.getMimeTypeFromExtension("image/*");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                // 仅需改变这一行
                FileProvider7.setIntentDataAndType(MainActivity.this,
                        intent, type, file, true);
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveImage(Bitmap bitmap, @NonNull String name) throws IOException {
        OutputStream fos;
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentResolver resolver = getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name + ".jpg");
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
            uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            fos = resolver.openOutputStream(Objects.requireNonNull(uri));
        } else {
            String imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
            File image = new File(imagesDir, name + ".jpg");
            fos = new FileOutputStream(image);
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        Objects.requireNonNull(fos).close();

    }

    public void showBottomSheetDialog() {
        addImages.clear();
//        addImages.add(new TypeClass("1", "Perde", "https://firebasestorage.googleapis.com/v0/b/d-ecommerce-a32f1.appspot.com/o/png4.png?alt=media&token=b943a78e-b55b-4c41-840a-97081160e511"));
//        addImages.add(new TypeClass("2","Perde","https://firebasestorage.googleapis.com/v0/b/d-ecommerce-a32f1.appspot.com/o/png1.png?alt=media&token=9feadc6e-ab41-4a0c-966c-a0e43d2abe04"));
//        addImages.add(new TypeClass("3","Perde","https://firebasestorage.googleapis.com/v0/b/d-ecommerce-a32f1.appspot.com/o/png2.png?alt=media&token=9ea852ff-790f-40f5-bda4-2da72c955a1d"));
//        addImages.add(new TypeClass("4","Perde","https://firebasestorage.googleapis.com/v0/b/d-ecommerce-a32f1.appspot.com/o/png3.png?alt=media&token=70b02c92-bf1e-4974-86d3-fe1972b27f0b"));

        addImages.add(new TypeClass(
                "1",
                "Mebel",
                "https://static.wixstatic.com/media/2cd43b_a76059d71b564ffeb8bfb8f0cdf48180~mv2.png/v1/fill/w_320,h_341,q_90/2cd43b_a76059d71b564ffeb8bfb8f0cdf48180~mv2.png"
        ));
        addImages.add(new TypeClass(
                "2",
                "Mebel",
                "https://gallery.yopriceville.com/var/albums/Free-Clipart-Pictures/Furniture-PNG/Transparent_Patio_Sofa_PNG_Picture.png?m=1434276749"
        ));
        addImages.add(new TypeClass(
                "3",
                "Mebel",
                "https://static.wixstatic.com/media/2cd43b_810e23b956eb43a0ae60d365813eaf70~mv2.png/v1/fill/w_320,h_157,q_90/2cd43b_810e23b956eb43a0ae60d365813eaf70~mv2.png"
        ));
        addImages.add(new TypeClass(
                "4",
                "Mebel",
                "https://gallery.yopriceville.com/var/albums/Free-Clipart-Pictures/Furniture-PNG/Transparent_Beige_Sofa_PNG_Picture.png?m=1434276749"
        ));
        addImages.add(new TypeClass(
                "5",
                "Mebel",
                "https://theme.mybobs.com/_ui/desktop/common/bobs/images/Desktop_321x321_login.png"
        ));

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.add_image_sheet);
        RecyclerView imagesRec = bottomSheetDialog.findViewById(R.id.imagesRec);
        AddImageAdapter addImageAdapter = new AddImageAdapter(context, addImages, container);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 4);
        imagesRec.setAdapter(addImageAdapter);
        imagesRec.setLayoutManager(layoutManager);
        bottomSheetDialog.show();


    }

    public void setSelectedImage(RotateZoomImageView image) {
        selectedImage = image;
    }

    private void hideDeleteButton() {
        for (int i = 0; i <= container.getChildCount(); i++) {
            View view = container.getChildAt(i);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                if (textView.getText().equals("remove")) {
                    textView.setVisibility(View.INVISIBLE);
                }
            }
        }
    }


    @Override
    public void onBackPressed() {
        Glide.get(context).clearMemory();
        super.onBackPressed();
    }

    private void showDeleteButton() {
        for (int i = 0; i <= container.getChildCount(); i++) {
            View view = container.getChildAt(i);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                if (textView.getText().equals("remove")) {
                    textView.setVisibility(View.VISIBLE);
                }
            }
        }
    }


}