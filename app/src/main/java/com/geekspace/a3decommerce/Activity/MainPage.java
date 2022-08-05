package com.geekspace.a3decommerce.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.geekspace.a3decommerce.Adapter.MainViewPagerAdapter;
import com.geekspace.a3decommerce.Common.Utils;
import com.geekspace.a3decommerce.Fragment.About;
import com.geekspace.a3decommerce.Fragment.Category;
import com.geekspace.a3decommerce.Fragment.Favourite;
import com.geekspace.a3decommerce.Fragment.FirstRootFragment;
import com.geekspace.a3decommerce.Fragment.Home;
import com.geekspace.a3decommerce.Fragment.Popular;
import com.geekspace.a3decommerce.Fragment.Profile;
import com.geekspace.a3decommerce.Fragment.ProfileUpdate;
import com.geekspace.a3decommerce.Fragment.Settings;
import com.geekspace.a3decommerce.Fragment.TermsOfDelivery;
import com.geekspace.a3decommerce.Fragment.TermsOfUse;
import com.geekspace.a3decommerce.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

import static android.content.ContentValues.TAG;

public class MainPage extends AppCompatActivity {
    private FloatingActionButton fab;
    private BottomNavigationView bottomNavigationView;
    private Button btn;
    public static Fragment firstFragment = new Home();
    public static Fragment secondFragment = new Category();
    public static Fragment thirdFragment = new Favourite();
    public static Fragment fourthFragment = new Settings();
    private Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        showSplash(this);
        initComponents();
        setNavigation();
        setTint();
    }

    private void setNavigation() {

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        changeFragment(firstFragment, firstFragment.getClass().getSimpleName());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        changeFragment(firstFragment, firstFragment.getClass().getSimpleName());
                        break;
                    case R.id.category:
                        changeFragment(secondFragment, secondFragment.getClass().getSimpleName());
                        break;
                    case R.id.fav:
                        changeFragment(thirdFragment, thirdFragment.getClass().getSimpleName());
                        break;
                    case R.id.settings:
                        changeFragment(fourthFragment, fourthFragment.getClass().getSimpleName());
                        break;

                }
                return true;
            }
        });




    }

    @Override
    public void onBackPressed() {
        Home home = (Home) getSupportFragmentManager().findFragmentByTag(Home.class.getSimpleName());
        Category category = (Category) getSupportFragmentManager().findFragmentByTag(Category.class.getSimpleName());
        Favourite favourite = (Favourite) getSupportFragmentManager().findFragmentByTag(Favourite.class.getSimpleName());
        Settings settings = (Settings) getSupportFragmentManager().findFragmentByTag(Settings.class.getSimpleName());
        if ((home != null && home.isVisible()) || (category != null && category.isVisible()) || (favourite != null && favourite.isVisible()) || (settings != null && settings.isVisible())) {
            finish();
            firstFragment=new Home();
            secondFragment=new Category();
            thirdFragment=new Favourite();
            fourthFragment=new Settings();
        }

        Popular popular = (Popular) getSupportFragmentManager().findFragmentByTag(Popular.class.getSimpleName());
        if (popular != null && popular.isVisible()) {
            Utils.removeShow(new Home(), Home.class.getSimpleName(), getSupportFragmentManager(), R.id.content);
            firstFragment = new Home();
        }
        TermsOfUse termsOfUse = (TermsOfUse) getSupportFragmentManager().findFragmentByTag(TermsOfUse.class.getSimpleName());
        if (termsOfUse != null && termsOfUse.isVisible()) {
            Utils.removeShow(new Settings(), Settings.class.getSimpleName(), getSupportFragmentManager(), R.id.content);
            fourthFragment = new Settings();
        }

        TermsOfDelivery termsOfDelivery = (TermsOfDelivery) getSupportFragmentManager().findFragmentByTag(TermsOfDelivery.class.getSimpleName());
        if (termsOfDelivery != null && termsOfDelivery.isVisible()) {
            Utils.removeShow(new Settings(), Settings.class.getSimpleName(), getSupportFragmentManager(), R.id.content);
            fourthFragment = new Settings();
        }
        About about = (About) getSupportFragmentManager().findFragmentByTag(About.class.getSimpleName());
        if (about != null && about.isVisible()) {
            Utils.removeShow(new Settings(), Settings.class.getSimpleName(), getSupportFragmentManager(), R.id.content);
            fourthFragment = new Settings();
        }
        Profile profile = (Profile) getSupportFragmentManager().findFragmentByTag(Profile.class.getSimpleName());
        if (profile != null && profile.isVisible()) {
            Utils.removeShow(new Settings(), Settings.class.getSimpleName(), getSupportFragmentManager(), R.id.content);
            fourthFragment = new Settings();
        }
    }

    private void initComponents() {
        fab = findViewById(R.id.fab);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        btn = findViewById(R.id.btn);
    }

    private void setTint() {
        int color = getResources().getColor(R.color.white);
        Drawable myFabSrc = getResources().getDrawable(R.drawable.shopping_bag);
        Drawable willBeWhite = myFabSrc.getConstantState().newDrawable();
        willBeWhite.mutate().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        fab.setImageDrawable(willBeWhite);
    }

    public void presentActivity(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, btn, "transition");
        int revealX = (int) (view.getX() + view.getWidth() / 2);
        int revealY = (int) (view.getY() + view.getHeight() / 2);

        Intent intent = new Intent(this, Basket.class);
        intent.putExtra(Basket.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(Basket.EXTRA_CIRCULAR_REVEAL_Y, revealY);

        ActivityCompat.startActivity(this, intent, options.toBundle());
    }


    public void changeFragment(Fragment fragment, String tagFragmentName) {

        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        Fragment currentFragment = mFragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragmentTemp = mFragmentManager.findFragmentByTag(tagFragmentName);
        if (fragmentTemp == null) {
            fragmentTemp = fragment;
            fragmentTransaction.add(R.id.content, fragmentTemp, tagFragmentName);
        } else {
            fragmentTransaction.show(fragmentTemp);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    public static void setBottomNavigationSelectItem(Activity activity, int id) {
        BottomNavigationView bnv = activity.findViewById(R.id.bottom_navigation);
        bnv.setSelectedItemId(id);
    }

    public static void showSplash(Activity activity){
        RelativeLayout splash=activity.findViewById(R.id.splash);
        FrameLayout content=activity.findViewById(R.id.content);
        FloatingActionButton fab=activity.findViewById(R.id.fab);
        BottomAppBar bottomAppBar=activity.findViewById(R.id.bottomAppBar);
        Button btn=activity.findViewById(R.id.btn);

        splash.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
        bottomAppBar.setVisibility(View.GONE);
        btn.setVisibility(View.GONE);
    }

    public static void hideSplash(Activity activity){
        RelativeLayout splash=activity.findViewById(R.id.splash);
        FrameLayout content=activity.findViewById(R.id.content);
        FloatingActionButton fab=activity.findViewById(R.id.fab);
        BottomAppBar bottomAppBar=activity.findViewById(R.id.bottomAppBar);
        Button btn=activity.findViewById(R.id.btn);
        splash.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
        bottomAppBar.setVisibility(View.VISIBLE);
        btn.setVisibility(View.VISIBLE);
    }
}