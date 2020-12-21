package com.ocean.driver.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ocean.driver.R;
import com.ocean.driver.adapter.NavPagerAdapter;
import com.ocean.driver.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by James on 2020/11/16.
 * 操作单管理
 */
public class OperaFragment extends BaseFragment {
    @BindView(R.id.rb_f)
    RadioButton rbF;
    @BindView(R.id.rb_t)
    RadioButton rbT;
    @BindView(R.id.rg_type)
    RadioGroup rgType;
    @BindView(R.id.vp_bill_type)
    CustomViewPager vpBillType;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_opera;
    }

    @Override
    protected void initViews() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new YOperaFragment());
        fragments.add(new NOperaListFragment());

        NavPagerAdapter viewPagerAdapter = new NavPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.setData(fragments);
        vpBillType.setAdapter(viewPagerAdapter);
        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_f:
                        vpBillType.setCurrentItem(0, false);
                        break;
                    case R.id.rb_t:
                        vpBillType.setCurrentItem(1, false);
                        break;
                }
            }
        });
        vpBillType.setOnPageChangeListener(onPagerChangerListener);
        rbF.setChecked(true);
    }

    ViewPager.OnPageChangeListener onPagerChangerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    rbF.setChecked(true);
                    break;
                case 1:
                    rbT.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    @Override
    protected void initDatas() {

    }
}
