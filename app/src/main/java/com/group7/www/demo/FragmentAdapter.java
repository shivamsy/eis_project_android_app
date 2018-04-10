package com.group7.www.demo;

/**
 * Created by shivam on 31/03/18.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class FragmentAdapter extends FragmentPagerAdapter {

    private Context myContext;

    public FragmentAdapter(FragmentManager fm, Context context) {
        super(fm);

        myContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FirstFragment.newInstance(0, "page1");
            case 1:
                return SecondFragment.newInstance(1, "page2");
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){

            case 0:return "Option A";
            case 1:return "Option B";
            default:return null;
        }
    }
}
