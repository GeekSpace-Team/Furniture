package com.geekspace.a3decommerce.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekspace.a3decommerce.Activity.MainPage;
import com.geekspace.a3decommerce.Adapter.TabAdapter;
import com.geekspace.a3decommerce.Common.Utils;
import com.geekspace.a3decommerce.R;
import com.google.android.material.tabs.TabLayout;


public class Profile extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view;
    private ImageView backIcon;
    private TextView backTv;
    private Context context;
    public Profile() {
    }


    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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
        view=inflater.inflate(R.layout.fragment_profile, container, false);
        context=getContext();
        initComponents();
        tabLayout();
        setListener();
        return view;
    }

    private void initComponents() {
        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(2);
        backIcon=view.findViewById(R.id.backIcon);
        backTv=view.findViewById(R.id.backTv);
    }

    private void tabLayout(){
        tabLayout.setupWithViewPager(viewPager);
        TabAdapter adapter=new TabAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        if(Utils.getSharedPreferences(context,"tkn").isEmpty()){
            adapter.addFragment(new SignIn(),"Içeri gir");
            adapter.addFragment(new SignUp(),"Hasap döret");
        } else if(!Utils.getSharedPreferences(context,"tkn").isEmpty() && !Utils.getSharedPreferences(context,"user_id").isEmpty()) {
            adapter.addFragment(new ProfileUpdate(),getResources().getString(R.string.update_profile));
            adapter.addFragment(new UpdateNumber(),getResources().getString(R.string.update_number));
            adapter.addFragment(new UpdatePassword(),getResources().getString(R.string.update_password));
        }


        viewPager.setAdapter(adapter);

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



    private void goBack(){
        Utils.removeShow(new Settings(), Settings.class.getSimpleName(), getFragmentManager(), R.id.content);
        MainPage.fourthFragment = new Settings();
    }
}