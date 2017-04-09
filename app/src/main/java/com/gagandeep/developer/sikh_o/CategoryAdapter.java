package com.gagandeep.developer.sikh_o;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by gagandeep on 9/25/2016.
 * Fixing up the categories (tabs)
 */
public class CategoryAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public CategoryAdapter(Context context,FragmentManager fm)
    {
        super(fm);
        mContext = context;
    }
    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new NumbersFragment();
        else if(position == 1)
            return new PhrasesFragment();
        else if(position == 2)
            return new ColorsFragment();
        else
            return new AlphabetsFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return mContext.getString(R.string.category_numbers);
        else if(position == 1)
            return mContext.getString(R.string.category_phrases);
        else if(position == 2)
            return mContext.getString(R.string.category_colors);
        else
            return mContext.getString(R.string.category_alphabets);
    }
}
