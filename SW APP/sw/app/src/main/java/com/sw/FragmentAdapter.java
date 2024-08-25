package com.sw;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private info main;
    private map second;
    private memo third;

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        main = new info();
        second = new map();
        third = new memo();
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return main;
            case 1:
                return second;
            case 2:
                return third;
        }
        return null;
    }
    @Override
    public int getCount() {
        return 3;
    }
}
