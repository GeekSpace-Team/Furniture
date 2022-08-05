package com.geekspace.a3decommerce.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekspace.a3decommerce.R;


public class SignIn extends Fragment {

    private View view;
    private Context context;

    public SignIn() {
    }

    public static SignIn newInstance(String param1, String param2) {
        SignIn fragment = new SignIn();
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
        view=inflater.inflate(R.layout.fragment_sign_in, container, false);
        return view;
    }
}