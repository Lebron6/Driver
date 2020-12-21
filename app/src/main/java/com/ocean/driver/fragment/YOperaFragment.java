package com.ocean.driver.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.ocean.driver.R;
import com.ocean.driver.adapter.NavPagerAdapter;
import com.ocean.driver.fragment.operationsheet.HandoverFragment;
import com.ocean.driver.fragment.operationsheet.OperaListFragment;
import com.ocean.driver.fragment.operationsheet.SetOutFragment;
import com.ocean.driver.view.NavitationLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by James on 2020/8/4.
 * 操作单
 */
public class YOperaFragment extends BaseFragment {

    @BindView(R.id.nav_tab)
    NavitationLayout navTab;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_opera_y;
    }

    @Override
    protected void initViews() {
        String[] strings = {"全部", "受理", "驳回","出发","交接"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new OperaListFragment("1"));
        fragments.add(new OperaListFragment("2"));
        fragments.add(new OperaListFragment("3"));
        fragments.add(new SetOutFragment());
        fragments.add(new HandoverFragment());

        NavPagerAdapter viewPagerAdapter2 = new NavPagerAdapter(getChildFragmentManager());
        viewPagerAdapter2.setData(fragments);
        vpFragment.setAdapter(viewPagerAdapter2);
        vpFragment.setCurrentItem(0);
        navTab.setViewPager(getActivity(), strings, vpFragment, R.color.colorMainBlack, R.color.colorMain, 14, 16, 0, 30, true);
        navTab.setBgLine(getActivity(), 0, R.color.colorMain);
        navTab.setNavLine(getActivity(), 2, R.color.colorMain, 0);
    }

    @Override
    protected void initDatas() {

    }
}
