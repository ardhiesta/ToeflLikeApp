package com.example.djakaumbarawurung.persipantestcept.tutor_screen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.djakaumbarawurung.persipantestcept.R;

import me.relex.circleindicator.CircleIndicator;

public class TutorActivity extends AppCompatActivity {
    ViewPager vpTutor;
    CircleIndicator circleIndicator;

    // jumlah fragment (jumlah halaman tutorial)
    private static final int NUM_PAGES = 2;

    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);

        vpTutor = (ViewPager) findViewById(R.id.vp_tutor);
        circleIndicator = (CircleIndicator) findViewById(R.id.circle_indicator);

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        vpTutor.setAdapter(mPagerAdapter);
        circleIndicator.setViewPager(vpTutor);

        vpTutor.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentTutor1();
                case 1:
                    return new FragmentTutor2();

            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
