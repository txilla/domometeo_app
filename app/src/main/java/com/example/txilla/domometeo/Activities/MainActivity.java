package com.example.txilla.domometeo.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.txilla.domometeo.Fragments.DashboardFragment;
import com.example.txilla.domometeo.Fragments.UtilitiesFragment;
import com.example.txilla.domometeo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation) BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {

                    case R.id.action_dash:
                        selectedFragment = DashboardFragment.newInstance();
                        break;

                    case R.id.action_scenes:
                        selectedFragment = DashboardFragment.newInstance();
                        break;

                    case R.id.action_utilities:
                        selectedFragment = UtilitiesFragment.newInstance();
                        break;

                    default:

                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.containerView, selectedFragment)
                        .commit();
                return true;
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.containerView, DashboardFragment.newInstance())
                .commit();

    }
}
