package com.app.demoquestions;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter
{
    int mNumOfTabs;
    Fragment fragment = null;

    public ViewPagerAdapter(FragmentManager fm, int mNumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position)
    {
        for (int i= 0;i<mNumOfTabs;i++)
            if (i == position)
            {
                fragment = DynamicFragment.newInstance();
            }
        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
