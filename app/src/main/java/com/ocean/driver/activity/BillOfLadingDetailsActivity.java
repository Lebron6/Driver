package com.ocean.driver.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.driver.R;
import com.ocean.driver.api.BaseUrl;
import com.ocean.driver.api.HttpUtil;
import com.ocean.driver.entity.ApiResponse;
import com.ocean.driver.entity.BillDetailsData;
import com.ocean.driver.tools.PreferenceUtils;
import com.ocean.driver.tools.TitleManger;
import com.ocean.driver.tools.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/7.
 * 提货单详情
 */
public class BillOfLadingDetailsActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_logistics_company)
    TextView tvLogisticsCompany;
    @BindView(R.id.iv_call_phone)
    ImageView ivCallPhone;
    @BindView(R.id.tv_car_num)
    TextView tvCarNum;
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.tv_bill_num)
    TextView tvBillNum;
    @BindView(R.id.txt_t)
    TextView txtT;
    @BindView(R.id.txt_s)
    TextView txtS;
    @BindView(R.id.layout_line)
    RelativeLayout layoutLine;
    @BindView(R.id.tv_name_t)
    TextView tvNameT;
    @BindView(R.id.tv_phone_t)
    TextView tvPhoneT;
    @BindView(R.id.tv_addr_t)
    TextView tvAddrT;
    @BindView(R.id.layout_t_info)
    LinearLayout layoutTInfo;
    @BindView(R.id.tv_name_s)
    TextView tvNameS;
    @BindView(R.id.tv_phone_s)
    TextView tvPhoneS;
    @BindView(R.id.tv_addr_s)
    TextView tvAddrS;
    @BindView(R.id.layout_s_info)
    LinearLayout layoutSInfo;
    @BindView(R.id.layout_addr)
    RelativeLayout layoutAddr;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.layout_goods_list)
    LinearLayout layoutGoodsList;
    @BindView(R.id.layout_to_bill_of_lading)
    LinearLayout layoutToBillOfLading;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;

    public static final String TYPE = "TYPE";
    public static final String SET_OUT = "SET_OUT";
    public static final String ARRVE = "ARRVE";
    @BindView(R.id.layout_to_sure)
    LinearLayout layoutToSure;
    public static final String DPV_ID = "DPV_ID";
    public static void actionStart(Context context, String dpv_id, String type) {
        Intent intent = new Intent(context, BillOfLadingDetailsActivity.class);
        intent.putExtra(DPV_ID, dpv_id);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("提货单详情");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_bill_of_lading;
    }

    @Override
    protected void initViews() {
        if (getIntent().getStringExtra(TYPE).equals(SET_OUT)) {
            layoutToBillOfLading.setVisibility(View.VISIBLE);
            layoutToSure.setVisibility(View.GONE);
        } else {
            layoutToBillOfLading.setVisibility(View.GONE);
            layoutToSure.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initDatas() {

        HttpUtil.createRequest(TAG, BaseUrl.getInstence().listingInfo()).listingInfo(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(DPV_ID)).enqueue(new Callback<ApiResponse<BillDetailsData>>() {
            @Override
            public void onResponse(Call<ApiResponse<BillDetailsData>> call, Response<ApiResponse<BillDetailsData>> response) {
                if (response.body().getCode() == 1) {
                    tvLogisticsCompany.setText(response.body().getData().getSdl_name());
                    tvCarNum.setText(response.body().getData().getVehicle());
                    tvBillNum.setText(response.body().getData().getDp_num());
                    tvNameT.setText(response.body().getData().getStart_address().getContract_name());
                    tvPhoneT.setText(response.body().getData().getStart_address().getContract_tel());
                    tvAddrT.setText(response.body().getData().getStart_address().getInfo());

                    tvNameS.setText(response.body().getData().getEnd_address().getContract_name());
                    tvPhoneS.setText(response.body().getData().getEnd_address().getContract_tel());
                    tvAddrS.setText(response.body().getData().getEnd_address().getInfo());
                    servicePhone = response.body().getData().getSdl_mobile();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<BillDetailsData>> call, Throwable t) {
                ToastUtil.showToast("网络异常:提单详情数据获取失败");
            }
        });
    }

    private String servicePhone;

    @OnClick({R.id.iv_call_phone, R.id.layout_goods_list, R.id.layout_to_bill_of_lading, R.id.layout_to_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_call_phone:
                if (TextUtils.isEmpty(servicePhone)) {
                    ToastUtil.showToast("暂不支持拨打电话");
                    return;
                }
                callPhone(servicePhone);
                break;
            case R.id.layout_goods_list:
                QuotationActivity.actionStart(this, getIntent().getStringExtra(DPV_ID));
                break;
            case R.id.layout_to_bill_of_lading:
                commit();
                break;
            case R.id.layout_to_sure:
                arrv();
                break;
        }
    }

    private void commit() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().listingChangeStatus()).listingChangeStatus(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(DPV_ID), "4").enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("提货成功");
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:提货失败");
            }
        });
    }

    private void arrv() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().listingChangeStatus()).listingChangeStatus(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(DPV_ID), "5").enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("确认成功");
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:确认失败");
            }
        });
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

}
