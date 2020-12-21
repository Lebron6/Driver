package com.ocean.driver.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ocean.driver.R;
import com.ocean.driver.adapter.SettingLoadingAdapter;
import com.ocean.driver.api.BaseUrl;
import com.ocean.driver.api.HttpUtil;
import com.ocean.driver.entity.ApiResponse;
import com.ocean.driver.entity.BillDetailsData;
import com.ocean.driver.entity.CountingInfo;
import com.ocean.driver.entity.SettingLoadingUpData;
import com.ocean.driver.tools.PreferenceUtils;
import com.ocean.driver.tools.RecyclerViewHelper;
import com.ocean.driver.tools.TitleManger;
import com.ocean.driver.tools.ToastUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/7/15.
 * 设置装车
 */
public class SettingLoadingActivity extends BaseActivity implements TagFlowLayout.OnSelectListener {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.txt_num)
    TextView txtNum;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.txt_car_num)
    TextView txtCarNum;
    @BindView(R.id.tv_car_num)
    TextView tvCarNum;
    @BindView(R.id.txt_driver)
    TextView txtDriver;
    @BindView(R.id.tv_driver)
    TextView tvDriver;
    @BindView(R.id.view_line_t)
    View viewLineT;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.rv_loading)
    RecyclerView rvLoading;
    @BindView(R.id.layout_center)
    LinearLayout layoutCenter;
    @BindView(R.id.btn_scan_counting)
    Button btnScanCounting;
    @BindView(R.id.btn_manual_counting)
    Button btnManualCounting;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;
    private SettingLoadingAdapter adapter;
    private CountingInfo countingInfo;

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("点货装车");
        manger.setBack();
    }

    public static final String DPV_ID = "DPV_ID";

    public static void actionStart(Context context, String dpv_id) {
        Intent intent = new Intent(context, SettingLoadingActivity.class);
        intent.putExtra(DPV_ID, dpv_id);
        context.startActivity(intent);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_setting_loading;
    }

    @Override
    protected void initViews() {
        adapter = new SettingLoadingAdapter(this);

//        List<String> strings = new ArrayList<>();
//        strings.add("132132468515");
//        strings.add("132132468515");
//        strings.add("132132468515");
//        strings.add("132132468515");
//        strings.add("132132468515");
//        strings.add("132132468515");
//        idFlowlayout.setAdapter(new TagAdapter<String>(strings) {
//            @Override
//            public View getView(FlowLayout parent, int position, String s) {
//                TextView tv = (TextView) LayoutInflater.from(SettingLoadingActivity.this).inflate(R.layout.tv_label,
//                        idFlowlayout, false);
//                tv.setText(s);
//                return tv;
//            }
//        });
//        idFlowlayout.setOnSelectListener(SettingLoadingActivity.this);
    }

    @Override
    protected void initDatas() {
        getData();
    }

    private void getData() {

        HttpUtil.createRequest(TAG, BaseUrl.getInstence().countingInfo()).countingInfo(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(DPV_ID)).enqueue(new Callback<ApiResponse<CountingInfo>>() {
            @Override
            public void onResponse(Call<ApiResponse<CountingInfo>> call, Response<ApiResponse<CountingInfo>> response) {
                if (response.body().getCode() == 1) {
                    countingInfo = response.body().getData();
                    adapter.setDatas(countingInfo);
                    RecyclerViewHelper.initRecyclerViewV(SettingLoadingActivity.this, rvLoading, false, adapter);
                    tvNum.setText(countingInfo.getDp_num());
                    tvCarNum.setText(countingInfo.getVehicle());

                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<CountingInfo>> call, Throwable t) {
                ToastUtil.showToast("网络异常:数据获取失败");
            }
        });
    }

    @OnClick({R.id.btn_scan_counting, R.id.btn_manual_counting, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_scan_counting:
                break;
            case R.id.btn_manual_counting:
//                ManualCountingActivity.actionStart(this);
                break;
            case R.id.btn_commit:
                commit();
                break;
        }
    }

    private void commit() {
        List<SettingLoadingUpData> goods = new ArrayList<>();
        if (countingInfo != null && countingInfo.getGoods().size() > 0) {
            for (int i = 0; i < countingInfo.getGoods().size(); i++) {
                if (countingInfo.getGoods().get(i).getType() == 1) {
                    SettingLoadingUpData good = new SettingLoadingUpData();
                    good.setG_id(countingInfo.getGoods().get(i).getG_id());
                    good.setNum(countingInfo.getGoods().get(i).getNum());
                    good.setJnum(countingInfo.getGoods().get(i).getJnum());
                    goods.add(good);
                }
            }
            HttpUtil.createRequest(TAG, BaseUrl.getInstence().countingSave()).countingSave(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(DPV_ID), new Gson().toJson(goods)).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.body().getCode() == 1) {
                        ToastUtil.showToast("装车成功");
                        finish();
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    ToastUtil.showToast("网络异常:装车失败");
                }
            });
        } else {
            ToastUtil.showToast("暂无代操作货物");
        }
    }

    @Override
    public void onSelected(Set<Integer> selectPosSet) {
        Log.e("选中了这些", selectPosSet.toString());
    }
}
