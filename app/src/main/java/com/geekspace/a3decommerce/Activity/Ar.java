package com.geekspace.a3decommerce.Activity;

import android.app.Activity;
import android.app.ActivityManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.geekspace.a3decommerce.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;

import com.google.ar.core.Trackable;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public class Ar extends AppCompatActivity {
    private static final String TAG = Ar.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;

    private ArFragment arFragment;
    private Renderable renderable;
    private String raw_name="";


    @RequiresApi(api = VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ar);
        raw_name=getIntent().getStringExtra("raw_name");


//        FirebaseApp.initializeApp(this);
//
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference modelRef = storage.getReference().child("AnyConv.com__chair.glb");
        InputStream ins = getResources().openRawResource(
                getResources().getIdentifier(raw_name,
                        "raw", getPackageName()));
        File file = createFileFromInputStream(ins);
        buildModel(file);

//        modelRef.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//
//                buildModel(file);
//
//            }
//        });


        arFragment = (ArFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

            AnchorNode anchorNode = new AnchorNode(hitResult.createAnchor());
            anchorNode.setRenderable(renderable);
            anchorNode.setLocalScale(new Vector3(0.55f, 0.55f, 0.55f));
            arFragment.getArSceneView().getScene().addChild(anchorNode);

        });
    }

    private File createFileFromInputStream(InputStream inputStream) {
        try{
            File f = new File(getFilesDir(), "temp1.txt");
            OutputStream outputStream = new FileOutputStream(f);
            byte buffer[] = new byte[1024];
            int length = 0;

            while((length=inputStream.read(buffer)) > 0) {
                outputStream.write(buffer,0,length);
            }

            outputStream.close();
            inputStream.close();

            return f;
        }
        catch (IOException e) { e.printStackTrace(); }
        return null;
    }

    private void buildModel(File file) {

        RenderableSource renderableSource = RenderableSource
                .builder()
                .setSource(this, Uri.parse(file.getPath()), RenderableSource.SourceType.GLB)
                .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                .build();

        if (Build.VERSION.SDK_INT >= VERSION_CODES.N) {
            ModelRenderable
                    .builder()
                    .setSource(this, renderableSource)
                    .setRegistryId(file.getPath())
                    .build()
                    .thenAccept(modelRenderable -> {
                        Toast.makeText(this, "Model built", Toast.LENGTH_SHORT).show();
                        renderable = modelRenderable;
                    });
        }

    }

    private void addObject(Uri parse) {
        Frame frame = arFragment.getArSceneView().getArFrame();
        Point point = getScreenCenter();
        if (frame != null) {
            List<HitResult> hits = frame.hitTest((float) point.x, (float) point.y);

            for (int i = 0; i < hits.size(); i++) {
                Trackable trackable = hits.get(i).getTrackable();
                if (trackable instanceof Plane && ((Plane) trackable).isPoseInPolygon(hits.get(i).getHitPose())) {
                    placeObject(arFragment, hits.get(i).createAnchor(), parse);
                }
            }
        }
    }

    private final void placeObject(final ArFragment fragment, final Anchor createAnchor, Uri model) {
        if (Build.VERSION.SDK_INT >= VERSION_CODES.N) {
            ModelRenderable.builder().setSource(fragment.getContext(), model).build().thenAccept((new Consumer() {
                public void accept(Object var1) {
                    this.accept((ModelRenderable) var1);
                }

                public final void accept(ModelRenderable it) {
                    if (it != null)
                        addNode(arFragment, createAnchor, it);
                }
            })).exceptionally((new Function() {
                public Object apply(Object var1) {
                    return this.apply((Throwable) var1);
                }

                @Nullable
                public final Void apply(Throwable it) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Ar.this);
                    builder.setMessage(it.getMessage()).setTitle("error!");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return null;
                }
            }));
        }
    }

    private void addNode(ArFragment fragment, Anchor createAnchor, ModelRenderable renderable) {

        AnchorNode anchorNode = new AnchorNode(createAnchor);
        TransformableNode transformableNode = new TransformableNode(fragment.getTransformationSystem());
        transformableNode.setRenderable(renderable);
        transformableNode.setParent(anchorNode);
        fragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }

    private Point getScreenCenter() {
        View vw = findViewById(android.R.id.content);
        return new Point(vw.getWidth() / 2, vw.getHeight() / 2);
    }


}