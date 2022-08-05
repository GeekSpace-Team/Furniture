package com.geekspace.a3decommerce.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekspace.a3decommerce.R;


public class SignUp extends Fragment {

    private View view;
    private Context context;

    public SignUp() {
    }


    public static SignUp newInstance(String param1, String param2) {
        SignUp fragment = new SignUp();
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
        view=inflater.inflate(R.layout.fragment_sign_up, container, false);
        getChildFragmentManager().beginTransaction().replace(R.id.frame,new SignUpPhoneNumber(),SignUpPhoneNumber.class.getSimpleName()).commit();
        return view;
    }
}