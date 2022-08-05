package com.geekspace.a3decommerce.Adapter;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.geekspace.a3decommerce.Fragment.Category;
import com.geekspace.a3decommerce.Fragment.Favourite;
import com.geekspace.a3decommerce.Fragment.FirstRootFragment;
import com.geekspace.a3decommerce.Fragment.Home;
import com.geekspace.a3decommerce.Fragment.Settings;

import org.jetbrains.annotations.NotNull;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {


    public MainViewPagerAdapter(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
               return FirstRootFragment.newInstance();
            case 1:
                return Category.newInstance();
            case 2:
                return Favourite.newInstance();
            case 3:
                return Settings.newInstance();
            default:
                return FirstRootFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
