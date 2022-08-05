package com.geekspace.a3decommerce.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekspace.a3decommerce.Common.Util;
import com.geekspace.a3decommerce.R;


public class SignUpBody extends Fragment {



    public SignUpBody() {
    }


    public static SignUpBody newInstance(String param1, String param2) {
        SignUpBody fragment = new SignUpBody();
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

        return inflater.inflate(R.layout.fragment_sign_up_body, container, false);
    }
}