package com.ocean.driver.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.ocean.driver.fragment.BillOfLadingFragment;
import com.ocean.driver.fragment.MineFragment;
import com.ocean.driver.fragment.OperaFragment;
import com.ocean.driver.fragment.YOperaFragment;


public class MainPageAdapter extends FragmentPagerAdapter {

    private OperaFragment operaFragment;
    private BillOfLadingFragment billOfLadingFragment;
    private MineFragment mineFragment;

    public MainPageAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            if(operaFragment==null){
                operaFragment = new OperaFragment();
                return operaFragment;
            }else{
                return operaFragment;
            }
        }else if (position==1){
            if(billOfLadingFragment==null){
                billOfLadingFragment = new BillOfLadingFragment();
                return billOfLadingFragment;
            }else{
                return billOfLadingFragment;
            }
        }else if(position==2){
            if(mineFragment ==null){
                mineFragment = new MineFragment();
                return mineFragment;
            }else{
                return mineFragment;
            }
        }else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


}
