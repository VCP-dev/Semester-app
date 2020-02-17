package com.example.a4thsemapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private MenuItem prevMenuItem;
    private BottomNavigationView bottomNav;
    private TextView heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        pager = findViewById(R.id.pager);

        List<Fragment> list = new ArrayList<>();
        list.add(new TimetableFragment());
        list.add(new SyllabusFragment());
        list.add(new PDFsFragment());
        list.add(new SGPAFragment());

        pager = findViewById(R.id.pager);
        pagerAdapter = new SliderPagerAdapter(getSupportFragmentManager(),list);


        pager.setAdapter(pagerAdapter);



        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TimetableFragment()).commit();

        getSupportFragmentManager().beginTransaction().replace(R.id.pager,new TimetableFragment()).commit();

        heading = findViewById(R.id.heading);
        heading.setText("Time table");


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNav.getMenu().getItem(0).setChecked(false);
                }

                bottomNav.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNav.getMenu().getItem(position);

                switch (position)
                {
                    case 0:
                        heading.setText("Time table");
                        break;
                    case 1:
                        heading.setText("Syllabus");
                        break;
                    case 2:
                        heading.setText("PDFs");
                        break;
                    case 3:
                        heading.setText("SGPA");
                        break;

                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    //Fragment selectedFragment = null;
                    TextView heading = findViewById(R.id.heading);
                    pager = findViewById(R.id.pager);

                    switch(menuItem.getItemId())
                    {
                        case R.id.nav_timetable:
                            pager.setCurrentItem(0);
                            heading.setText("Time table");
                            break;
                        case R.id.nav_syllabus:
                            pager.setCurrentItem(1);
                            heading.setText("Syllabus");
                            break;
                        case R.id.nav_pdfs:
                            pager.setCurrentItem(2);
                            heading.setText("PDFs");
                            break;
                        case R.id.nav_sgpa:
                            pager.setCurrentItem(3);
                            heading.setText("SGPA");
                            break;
                    }

                    //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    //getSupportFragmentManager().beginTransaction().replace(R.id.pager,selectedFragment).commit();

                    return true;
                }
            };



}
