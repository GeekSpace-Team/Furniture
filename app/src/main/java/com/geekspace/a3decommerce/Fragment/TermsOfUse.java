package com.geekspace.a3decommerce.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekspace.a3decommerce.Activity.MainPage;
import com.geekspace.a3decommerce.Common.Utils;
import com.geekspace.a3decommerce.R;


public class TermsOfUse extends Fragment {
    private ImageView backIcon;
    private TextView backTv;
    private View view;


    public TermsOfUse() {
    }

    public static TermsOfUse newInstance() {
        TermsOfUse fragment = new TermsOfUse();
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
        view=inflater.inflate(R.layout.fragment_terms_of_use, container, false);
        initComponents();
        setListener();
        return view;
    }

    private void setListener() {
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
    }

    private void initComponents() {
        backIcon=view.findViewById(R.id.backIcon);
        backTv=view.findViewById(R.id.backTv);
    }

    private void goBack(){
        Utils.removeShow(new Settings(), Settings.class.getSimpleName(), getFragmentManager(), R.id.content);
        MainPage.fourthFragment = new Settings();
    }
}