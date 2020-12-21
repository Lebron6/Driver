package com.ocean.driver.fragment.bill;

import android.support.v7.widget.RecyclerView;
import com.liaoinstan.springview.widget.SpringView;
import com.ocean.driver.R;
import com.ocean.driver.adapter.CompleteAdapter;
import com.ocean.driver.api.BaseUrl;
import com.ocean.driver.api.HttpUtil;
import com.ocean.driver.entity.BillData;
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
 * 完成
 */
public class CompleteFragment extends BaseFragment {

    @BindView(R.id.rv_complete)
    RecyclerView rvComplete;
    @BindView(R.id.sv_list)
    SpringView svList;
    private CompleteAdapter adapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_complete;
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter = new CompleteAdapter(getActivity());
    }

    private void initSpringViewStyle() {
        svList.setType(SpringView.Type.FOLLOW);
        svList.setListener(onFreshListener);
        svList.setHeader(new SimpleHeader(getActivity()));
        svList.setFooter(new SimpleFooter(getActivity()));
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
    List<BillData.DataBean.ListBean> listBeans = new ArrayList<>();

    private void getData() {
        HttpUtil.createRequest(BaseUrl.getInstence().listingGet()).listingGet(PreferenceUtils.getInstance().getUserToken(), page + "", "1").enqueue(new Callback<BillData>() {
            @Override
            public void onResponse(Call<BillData> call, Response<BillData> response) {
                if (svList != null) {
                    svList.onFinishFreshAndLoad();
                }
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        if (page == 1) {
                            listBeans.clear();
                            listBeans.addAll(response.body().getData().getList());
                            RecyclerViewHelper.initRecyclerViewV(getActivity(), rvComplete, false, adapter);
                        } else {
                            listBeans.addAll(response.body().getData().getList());
                        }
                        adapter.setDatas(listBeans);

                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<BillData> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取列表失败");
            }
        });
    }

    @Override
    protected void initDatas() {
        getData();
    }

}
