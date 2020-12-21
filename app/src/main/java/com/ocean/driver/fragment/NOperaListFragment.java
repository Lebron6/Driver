package com.ocean.driver.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.driver.R;
import com.ocean.driver.activity.OperaDetailsActivity;
import com.ocean.driver.adapter.OperaListAdapter;
import com.ocean.driver.api.BaseUrl;
import com.ocean.driver.api.HttpUtil;
import com.ocean.driver.entity.OperaListData;
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
 * 操作单列表
 */
public class NOperaListFragment extends BaseFragment {

    @BindView(R.id.rv_bill)
    RecyclerView rvBill;
    @BindView(R.id.sv_bill)
    SpringView svBill;
    private int type;
    private int status;
    private OperaListAdapter adapter;


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_opera_list;
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter = new OperaListAdapter(getActivity());
    }

    private void initSpringViewStyle() {
        svBill.setType(SpringView.Type.FOLLOW);
        svBill.setListener(onFreshListener);
        svBill.setHeader(new SimpleHeader(getActivity()));
        svBill.setFooter(new SimpleFooter(getActivity()));
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
    List<OperaListData.DataBean.ListBean> listBeans = new ArrayList<>();

    private void getData() {
        HttpUtil.createRequest(BaseUrl.getInstence().operationListingGet()).operationListingGet(PreferenceUtils.getInstance().getUserToken(), page + "", 6 + "").enqueue(new Callback<OperaListData>() {
            @Override
            public void onResponse(Call<OperaListData> call, Response<OperaListData> response) {
                if (svBill != null) {
                    svBill.onFinishFreshAndLoad();
                }
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        if (page == 1) {
                            listBeans.clear();
                            listBeans.addAll(response.body().getData().getList());
                            RecyclerViewHelper.initRecyclerViewV(getActivity(), rvBill, false, adapter);
                        } else {
                            listBeans.addAll(response.body().getData().getList());
                        }
                        adapter.setDatas(listBeans);
                        adapter.setOnItemClickLitener(new OperaListAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {

                            }
                        });
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }
            //
            @Override
            public void onFailure(Call<OperaListData> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取列表失败");
            }
        });
    }

    @Override
    protected void initDatas() {
        getData();
    }
}
