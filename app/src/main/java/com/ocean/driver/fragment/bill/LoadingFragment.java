package com.ocean.driver.fragment.bill;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ocean.driver.R;
import com.ocean.driver.adapter.NavPagerAdapter;
import com.ocean.driver.fragment.BaseFragment;
import com.ocean.driver.fragment.bill.loading.LoadingTypeOneFragment;
import com.ocean.driver.fragment.bill.loading.LoadingTypeThreeFragment;
import com.ocean.driver.fragment.bill.loading.LoadingTypeTwoFragment;
import com.ocean.driver.view.NavitationLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by James on 2020/6/29.
 * 装车
 */
public class LoadingFragment extends BaseFragment {
    @BindView(R.id.nav_tab)
    NavitationLayout navTab;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    Unbinder unbinder;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_loading;
    }

    @Override
    protected void initViews() {
//        String[] strings = {"待装车", "待确认", "驳回"};
//        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(new LoadingTypeOneFragment());
//        fragments.add(new LoadingTypeTwoFragment());
//        fragments.add(new LoadingTypeThreeFragment());
//        NavPagerAdapter viewPagerAdapter2 = new NavPagerAdapter(getChildFragmentManager());
//        viewPagerAdapter2.setData(fragments);
//        vpFragment.setAdapter(viewPagerAdapter2);
//        vpFragment.setCurrentItem(0);
//        navTab.setViewPager(getActivity(), strings, vpFragment, R.color.colorMainBlack, R.color.colorMain, 14, 16, 0, 55, true);
//        navTab.setBgLine(getActivity(), 0, R.color.colorMain);
//        navTab.setNavLine(getActivity(), 0, R.color.colorMain, 0);
    }

    @Override
    protected void initDatas() {

    }

}
