package com.ocean.driver.fragment.operationsheet;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.liaoinstan.springview.widget.SpringView;
import com.ocean.driver.R;
import com.ocean.driver.activity.BillOfLadingDetailsActivity;
import com.ocean.driver.adapter.OperaSetoutAdapter;
import com.ocean.driver.adapter.SetOutAdapter;
import com.ocean.driver.api.BaseUrl;
import com.ocean.driver.api.HttpUtil;
import com.ocean.driver.callback.NotiImp;
import com.ocean.driver.entity.ApiResponse;
import com.ocean.driver.entity.BillData;
import com.ocean.driver.entity.OperaSetoutList;
import com.ocean.driver.fragment.BaseFragment;
import com.ocean.driver.tools.PreferenceUtils;
import com.ocean.driver.tools.RecyclerViewHelper;
import com.ocean.driver.tools.SimpleFooter;
import com.ocean.driver.tools.SimpleHeader;
import com.ocean.driver.tools.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/6/29.
 * 出发
 */
public class SetOutFragment extends BaseFragment {

    @BindView(R.id.rv_set_out)
    RecyclerView rvSetOut;
    @BindView(R.id.sv_list)
    SpringView svList;
    private OperaSetoutAdapter adapter;
    private double Latitude;
    private double Longitude;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_set_out;
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter = new OperaSetoutAdapter(getActivity());
        adapter.setNotiImp(new NotiImp() {
            @Override
            public void noti() {
                getData();
            }
        });
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
//设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
//可在其中解析amapLocation获取相应内容。
//                        Log.e("经纬度",aMapLocation.getLatitude()+"---"+aMapLocation.getLongitude()+"---城市："+aMapLocation.getCity());
                        Latitude=aMapLocation.getLatitude();
                        Longitude=aMapLocation.getLongitude();
                        adapter.setZb(Latitude,Longitude);
                    }else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError","location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        });
        //启动定位
        mLocationClient.startLocation();
    }

    private void initSpringViewStyle() {
        svList.setType(SpringView.Type.FOLLOW);
        svList.setListener(onFreshListener);
        svList.setHeader(new SimpleHeader(getActivity()));
//        svList.setFooter(new SimpleFooter(getActivity()));
    }

    private int page = 1;
    SpringView.OnFreshListener onFreshListener = new SpringView.OnFreshListener() {
        @Override
        public void onRefresh() {
            page = 1;
            getData();
        }

        @Override
        public void onLoadmore() {
            page = ++page;
            getData();
        }
    };
    List<OperaSetoutList.ListBean> listBeans = new ArrayList<>();

    private void getData() {
        HttpUtil.createRequest(BaseUrl.getInstence().listingSetoutList()).listingSetoutList(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<OperaSetoutList>>() {
            @Override
            public void onResponse(Call<ApiResponse<OperaSetoutList>> call, Response<ApiResponse<OperaSetoutList>> response) {
                if (svList != null) {
                    svList.onFinishFreshAndLoad();
                }
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        if (page == 1) {
                            listBeans.clear();
                            listBeans.addAll(response.body().getData().getList());
                            RecyclerViewHelper.initRecyclerViewV(getActivity(), rvSetOut, false, adapter);
                        } else {
                            listBeans.addAll(response.body().getData().getList());
                        }
                        adapter.setDatas(listBeans);
//                        adapter.setOnItemClickLitener(new OperaSetoutAdapter.OnItemClickLitener() {
//                            @Override
//                            public void onItemClick(View view, int position) {
//                                BillOfLadingDetailsActivity.actionStart(getActivity(), listBeans.get(position).getOsd_id(), BillOfLadingDetailsActivity.SET_OUT);
//                            }
//                        });
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<OperaSetoutList>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取列表失败");
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
    }
    @Override
    protected void initDatas() {
        getData();
    }
}
