package com.ocean.driver.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.ocean.driver.R;
import com.ocean.driver.adapter.OperaDetailsJAdapter;
import com.ocean.driver.adapter.OperaDetailsSAdapter;
import com.ocean.driver.api.BaseUrl;
import com.ocean.driver.api.HttpUtil;
import com.ocean.driver.entity.ApiResponse;
import com.ocean.driver.entity.OperaDetails;
import com.ocean.driver.tools.PreferenceUtils;
import com.ocean.driver.tools.RecyclerViewHelper;
import com.ocean.driver.tools.TitleManger;
import com.ocean.driver.tools.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/7/3.
 * 操作单详情
 */
public class OperaDetailsActivity extends BaseActivity {

    public static final String OSD_ID = "osd_id";
    public static final String O_ID = "o_id";
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_reject_time)
    TextView tvRejectTime;
    @BindView(R.id.tc_reject_content)
    TextView tcRejectContent;
    @BindView(R.id.layout_t)
    LinearLayout layoutT;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.tv_zzzl)
    TextView tvZzzl;
    @BindView(R.id.tv_zzjs)
    TextView tvZzjs;
    @BindView(R.id.zztj)
    TextView zztj;
    @BindView(R.id.tv_zzsl)
    TextView tvZzsl;
    @BindView(R.id.layout_see_list)
    LinearLayout layoutSeeList;
    @BindView(R.id.sv)
    ScrollView sv;
    @BindView(R.id.layout_see_map)
    LinearLayout layoutSeeMap;
    @BindView(R.id.layout_fp)
    LinearLayout layoutFp;
    @BindView(R.id.layout_reject)
    LinearLayout layoutReject;
    @BindView(R.id.layout_accept)
    LinearLayout layoutAccept;
    @BindView(R.id.layout_sl)
    LinearLayout layoutSl;
    @BindView(R.id.layout_bottom_one)
    RelativeLayout layoutBottomOne;
    @BindView(R.id.tv_addr_start)
    TextView tvAddrStart;
    @BindView(R.id.tv_addr_end)
    TextView tvAddrEnd;
    @BindView(R.id.rv_jiehuo)
    RecyclerView rvJiehuo;
    @BindView(R.id.rv_jiaohuo)
    RecyclerView rvJiaohuo;
    @BindView(R.id.tv_bczz)
    TextView tvBczz;


    public static void actionStart(Context context, String osd_id, String o_id) {
        Intent intent = new Intent(context, OperaDetailsActivity.class);
        intent.putExtra(OSD_ID, osd_id);
        intent.putExtra(O_ID, o_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("操作单详情");
        manger.setBack();
        getDetailsData();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_opera_details;
    }

    @Override
    protected void initViews() {

    }

    private void getDetailsData() {
        getDara();
    }

    String o_id;

    private void getDara() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().operationListingInfo()).operationListingInfo(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(OSD_ID)).enqueue(new Callback<ApiResponse<OperaDetails>>() {
            @Override
            public void onResponse(Call<ApiResponse<OperaDetails>> call, Response<ApiResponse<OperaDetails>> response) {
                if (response.body().getCode() == 1) {
                    o_id = response.body().getData().getO_id();
                    tvAddrStart.setText(response.body().getData().getStart_city()+"");
                    tvAddrEnd.setText(response.body().getData().getEnd_city());
                    tvZzjs.setText(response.body().getData().getGoods_jnum());
                    tvZzsl.setText(response.body().getData().getGoods_num());
                    tvZzzl.setText(response.body().getData().getTotal_weight() + "kg");
                    zztj.setText(response.body().getData().getTotal_volume() + "m³");
                    tvBczz.setText(response.body().getData().getNow_goods_num() + "/" + response.body().getData().getNow_goods_jnum());

                    OperaDetailsJAdapter pickupAdapter = new OperaDetailsJAdapter(OperaDetailsActivity.this);
                    pickupAdapter.setDatas(response.body().getData().getPickup_address());
                    RecyclerViewHelper.initRecyclerViewV(OperaDetailsActivity.this, rvJiehuo, false, pickupAdapter);

                    OperaDetailsSAdapter deliveryAdapter = new OperaDetailsSAdapter(OperaDetailsActivity.this);
                    deliveryAdapter.setDatas(response.body().getData().getDelivery_address());
                    RecyclerViewHelper.initRecyclerViewV(OperaDetailsActivity.this, rvJiaohuo, false, deliveryAdapter);

                    layoutT.setVisibility(View.GONE);
                    switch (response.body().getData().getStatus()) {
                        case "1":

                            break;
                        case "2":
                            layoutT.setVisibility(View.VISIBLE);
                            tvRejectTime.setText(response.body().getData().getD_reject_time());
                            tcRejectContent.setText(response.body().getData().getD_reject());
                            break;
                        case "3":

                            break;
                        case "4":

                            break;
                        case "5":

                            break;
                    }
                    switch (response.body().getData().getS_type()) {//供应商类型 1提货 2干线 3派送 4交货 5其他
                        case "1":

                            break;
                        case "2":

                            break;
                        case "3":

                            break;
                        case "4":

                            break;
                        case "5":

                            break;
                    }

                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<OperaDetails>> call, Throwable t) {
                ToastUtil.showToast("网络异常:操作单详情数据获取失败");
            }
        });
    }

    @Override
    protected void initDatas() {
        getDetailsData();
    }


    @OnClick({R.id.layout_see_list, R.id.layout_see_map, R.id.layout_fp, R.id.layout_reject, R.id.layout_accept, R.id.layout_sl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_see_list:
                OperaGoodsQutationActivity.actionStart(this, o_id);
                break;
            case R.id.layout_see_map:
//                MapViewActivity.actionStart(this);
                AmapNaviParams params = new AmapNaviParams(null, null, null, AmapNaviType.DRIVER, AmapPageType.ROUTE);
//启动导航组件
                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), params, null);
                break;
            case R.id.layout_fp:
                break;
            case R.id.layout_reject:
//                RejectShippedRemarksDialog dialog = new RejectShippedRemarksDialog(this, R.style.Theme_AppCompat_Dialog, getIntent().getStringExtra(WA_ID));
//                dialog.show();
                break;
            case R.id.layout_accept:
                sure();
                break;
            case R.id.layout_sl:
                break;
        }
    }

    private void sure() {

    }

}
