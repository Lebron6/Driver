package com.ocean.driver.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import com.ocean.driver.R;
import com.ocean.driver.adapter.NavPagerAdapter;
import com.ocean.driver.fragment.bill.ArriveFragment;
import com.ocean.driver.fragment.bill.CompleteFragment;
import com.ocean.driver.fragment.bill.LoadingFragment;
import com.ocean.driver.fragment.bill.SetOutFragment;
import com.ocean.driver.view.NavitationLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * Created by James on 2020/8/4.
 * 提货单
 */
public class BillOfLadingFragment extends BaseFragment {
    @BindView(R.id.layout_search)
    LinearLayout layoutSearch;
    @BindView(R.id.nav_tab)
    NavitationLayout navTab;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_bill_of_lading;
    }

    @Override
    protected void initViews() {
        String[] strings = {"出发", "到达", "装车", "完成"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SetOutFragment());
        fragments.add(new ArriveFragment());
        fragments.add(new LoadingFragment());
        fragments.add(new CompleteFragment());
        NavPagerAdapter viewPagerAdapter2 = new NavPagerAdapter(getChildFragmentManager());
        viewPagerAdapter2.setData(fragments);
        vpFragment.setAdapter(viewPagerAdapter2);
        vpFragment.setCurrentItem(0);
        navTab.setViewPager(getActivity(), strings, vpFragment, R.color.colorMainBlack, R.color.colorMain, 14, 16, 0, 35, true);
        navTab.setBgLine(getActivity(), 0, R.color.colorMain);
        navTab.setNavLine(getActivity(), 2, R.color.colorMain, 0);
    }

    @Override
    protected void initDatas() {

    }
}
