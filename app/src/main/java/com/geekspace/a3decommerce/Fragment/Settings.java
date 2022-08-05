package com.geekspace.a3decommerce.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.geekspace.a3decommerce.Activity.MainPage;
import com.geekspace.a3decommerce.Common.Utils;
import com.geekspace.a3decommerce.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class Settings extends Fragment {
    private LinearLayout profile, language, termsOfUse, termsOfDelivery, about;
    private View view;
    private Context context;

    public Settings() {
    }


    public static Settings newInstance() {
        Settings fragment = new Settings();
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
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        context = getContext();
        initComponents();
        setListener();
        return view;
    }

    private void setListener() {
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile();
            }
        });
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language();
            }
        });
        termsOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.hideAdd(new TermsOfUse(), TermsOfUse.class.getSimpleName(), getFragmentManager(), R.id.content);
                MainPage.fourthFragment = new TermsOfUse();
            }
        });
        termsOfDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.hideAdd(new TermsOfDelivery(), TermsOfDelivery.class.getSimpleName(), getFragmentManager(), R.id.content);
                MainPage.fourthFragment = new TermsOfDelivery();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.hideAdd(new About(), About.class.getSimpleName(), getFragmentManager(), R.id.content);
                MainPage.fourthFragment = new About();
            }
        });
    }

    private void initComponents() {
        profile = view.findViewById(R.id.profile);
        language = view.findViewById(R.id.language);
        termsOfUse = view.findViewById(R.id.termsOfUse);
        termsOfDelivery = view.findViewById(R.id.termsOfDelivery);
        about = view.findViewById(R.id.about);
    }

    private void profile() {
        Utils.hideAdd(new Profile(), Profile.class.getSimpleName(), getFragmentManager(), R.id.content);
        MainPage.fourthFragment = new Profile();
    }

    private void language() {
        bottomSheet();
    }

    private void bottomSheet() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.change_language);

        bottomSheetDialog.show();
    }

}