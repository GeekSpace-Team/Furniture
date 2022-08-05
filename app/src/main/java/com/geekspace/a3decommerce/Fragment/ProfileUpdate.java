package com.geekspace.a3decommerce.Fragment;

import android.content.Context;
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


public class ProfileUpdate extends Fragment {



    private View view;
    private Context context;

    public ProfileUpdate() {
    }


    public static ProfileUpdate newInstance(String param1, String param2) {
        ProfileUpdate fragment = new ProfileUpdate();
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
        view=inflater.inflate(R.layout.fragment_profile_update, container, false);
        return view;
    }


}