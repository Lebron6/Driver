package com.ocean.driver.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.ocean.driver.R;
import com.ocean.driver.adapter.MainPageAdapter;
import com.ocean.driver.api.BaseUrl;
import com.ocean.driver.api.HttpUtil;
import com.ocean.driver.dialog.FillInfoDialog;
import com.ocean.driver.entity.ApiResponse;
import com.ocean.driver.entity.OnWay;
import com.ocean.driver.entity.VehicleStatus;
import com.ocean.driver.jpush.SetAlias;
import com.ocean.driver.tools.AppManager;
import com.ocean.driver.tools.PreferenceUtils;
import com.ocean.driver.tools.ToastUtil;
import com.ocean.driver.view.CustomViewPager;

import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.layout_empty)
    LinearLayout layoutEmpty;
    @BindView(R.id.layout_half)
    LinearLayout layoutHalf;
    @BindView(R.id.layout_full)
    LinearLayout layoutFull;
    @BindView(R.id.view_Show_Pop)
    View viewShowPop;
    @BindView(R.id.rb_operation_sheet)
    RadioButton rbOperationSheet;
    private MainPageAdapter adapter;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.vp_content)
    CustomViewPager vpContent;
    @BindView(R.id.rb_bill_of_lading)
    RadioButton rbBillOfLading;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.mine)
    RadioButton mine;
    @BindView(R.id.rg_group)
    RadioGroup rgGroup;
    @BindView(R.id.layout_bottom)
    RelativeLayout layoutBottom;

    @Override
    protected void initTitle() {
        if (PreferenceUtils.getInstance().getFillInfo() == true) {
            FillInfoDialog dialog = new FillInfoDialog(this, R.style.MyDialog);
            dialog.show();
        }
//        HttpUtil.createRequest(TAG, BaseUrl.getInstence().getVehicleStatus()).getVehicleStatus(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<VehicleStatus>>() {
//            @Override
//            public void onResponse(Call<ApiResponse<VehicleStatus>> call, Response<ApiResponse<VehicleStatus>> response) {
//                if (response.body().getCode() == 1) {
//                    switch (response.body().getData().getVehicle().getStatus()) {
//                        case "1"://空置
//                            layoutEmpty.setVisibility(View.VISIBLE);
//                            layoutHalf.setVisibility(View.GONE);
//                            layoutFull.setVisibility(View.GONE);
//                            break;
//                        case "2"://半载
//                            layoutEmpty.setVisibility(View.GONE);
//                            layoutHalf.setVisibility(View.VISIBLE);
//                            layoutFull.setVisibility(View.GONE);
//                            break;
//                        case "3"://满载
//                            layoutEmpty.setVisibility(View.GONE);
//                            layoutHalf.setVisibility(View.GONE);
//                            layoutFull.setVisibility(View.VISIBLE);
//                            break;
//                        case "4"://禁用
//                            layoutEmpty.setVisibility(View.GONE);
//                            layoutHalf.setVisibility(View.GONE);
//                            layoutFull.setVisibility(View.GONE);
//                            break;
//                    }
//                } else {
//                    layoutEmpty.setVisibility(View.GONE);
//                    layoutHalf.setVisibility(View.GONE);
//                    layoutFull.setVisibility(View.GONE);
//                    ToastUtil.showToast(response.body().getMsg());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ApiResponse<VehicleStatus>> call, Throwable t) {
//                ToastUtil.showToast("网络异常:获取车载状态失败");
//            }
//        });
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    String time;

    @Override
    protected void initViews() {
        checkQX();
        adapter = new MainPageAdapter(getSupportFragmentManager());
        vpContent.setAdapter(adapter);
//        vpContent.setOffscreenPageLimit(2);
        vpContent.setOnPageChangeListener(onPagerChangerListener);
        rgGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        rbOperationSheet.setChecked(true);
//        getVersionInfo();
        /**
         * 获取途中状态
         */
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().onWay()).onWay(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<OnWay>>() {
            @Override
            public void onResponse(Call<ApiResponse<OnWay>> call, Response<ApiResponse<OnWay>> response) {
                if (response.body().getCode() == 1) {
                    if (response.body().getData().isOn_way() == true) {
                        time=response.body().getData().getLocation_time();
                                upLoadLocation();
                    }
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<OnWay>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取途中状态失败");
            }
        });
    }

//    private void getVersionInfo() {
//        HttpUtil.createRequest(TAG,)
//    }

    private double Latitude;
    private double Longitude;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    private void upLoadLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
//设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        Latitude = aMapLocation.getLatitude();
                        Longitude = aMapLocation.getLongitude();
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        });
        //启动定位
        mLocationClient.startLocation();
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, Long.valueOf(600000));
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            HttpUtil.createRequest(TAG, BaseUrl.getInstence().locationUpload()).locationUpload(PreferenceUtils.getInstance().getUserToken(), Longitude + "", Latitude + ""
            ).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.body().getCode() == 1) {
                        Log.e("轨迹已上传", "success");
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    ToastUtil.showToast("网络异常:轨迹上传失败");
                }
            });
        }
    };

    private void checkQX() {
        if (Build.VERSION.SDK_INT >= 23) {
            //打电话的权限
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            if (EasyPermissions.hasPermissions(this, mPermissionList)) {
                //已经同意过

            } else {
                //未同意过,或者说是拒绝了，再次申请权限
                EasyPermissions.requestPermissions(
                        this,  //上下文
                        "需要拨打电话的权限", //提示文言
                        1, //请求码
                        mPermissionList //权限列表
                );
            }
        }
    }

    //同意授权
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        Log.i(TAG, "onPermissionsGranted:" + requestCode + ":" + list.size());
    }

    //拒绝授权
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.i(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());
        ToastUtil.showToast("请在APP管理处为APP添加应有权限");
        AppManager.getAppManager().AppExit(this);
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
//        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
////            finish();
//            new AppSettingsDialog.Builder(this).build().show();
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            //拒绝授权后，从系统设置了授权后，返回APP进行相应的操作
            Log.i(TAG, "onPermissionsDenied:------>自定义设置授权后返回APP");
        }
    }

    @Override
    protected void initDatas() {
        new SetAlias(this, PreferenceUtils.getInstance().getUserId() + "").setAlias();   //设置极光推送别名
    }

    ViewPager.OnPageChangeListener onPagerChangerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    rbOperationSheet.setChecked(true);
                    break;

                case 1:
                    rbBillOfLading.setChecked(true);
                    break;
                case 2:
                    mine.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            setTabSelection(checkedId);
        }
    };

    private void setTabSelection(int checkedId) {
        switch (checkedId) {
            case R.id.rb_operation_sheet:
                vpContent.setCurrentItem(0, false);
                break;
            case R.id.rb_bill_of_lading:
                vpContent.setCurrentItem(1, false);
                break;
            case R.id.mine:
                vpContent.setCurrentItem(2, false);
                break;
        }
    }


    @OnClick({R.id.layout_empty, R.id.layout_half, R.id.layout_full})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_empty:
                UpdateOnBoardStatusActivity.actionStart(this);
                break;
            case R.id.layout_half:
                UpdateOnBoardStatusActivity.actionStart(this);
                break;
            case R.id.layout_full:
                UpdateOnBoardStatusActivity.actionStart(this);
                break;
        }
    }

}
