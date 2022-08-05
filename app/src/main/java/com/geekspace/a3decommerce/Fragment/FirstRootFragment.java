package com.geekspace.a3decommerce.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.geekspace.a3decommerce.R;


public class FirstRootFragment extends Fragment {

    private View view;
    private Context context;
    private FrameLayout frameLayout;
    public FirstRootFragment() {
    }

    public static FirstRootFragment newInstance() {
        FirstRootFragment fragment = new FirstRootFragment();
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
        view=inflater.inflate(R.layout.fragment_first_root, container, false);
        context=getContext();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        /*
         * When this container fragment is created, we fill it with our first
         * "real" fragment
         */
        transaction.replace(R.id.root_frame, new Home());

        transaction.commit();
        return view;
    }
}