package com.ocean.driver.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.driver.R;
import com.ocean.driver.api.BaseUrl;
import com.ocean.driver.api.HttpUtil;
import com.ocean.driver.callback.ArriveImp;
import com.ocean.driver.entity.ApiResponse;
import com.ocean.driver.entity.BillData;
import com.ocean.driver.tools.PreferenceUtils;
import com.ocean.driver.tools.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ocean.driver.activity.BillOfLadingDetailsActivity.DPV_ID;

public class ArriveAdapter extends RecyclerView.Adapter {


    private Context context;
    private ArriveImp arriveImp;
    List<BillData.DataBean.ListBean> listBeans;
    public ArriveAdapter(Context context,ArriveImp arriveImp) {
        this.context = context;
        this.arriveImp = arriveImp;
    }

    public void setDatas(List<BillData.DataBean.ListBean> listBeans) {
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_arrive, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        viewHolder.tvAddress.setText(listBeans.get(position).getStart_address().getInfo());
        viewHolder.tvListNum.setText(listBeans.get(position).getDp_num());
        viewHolder.tvDispatcher.setText(listBeans.get(position).getSdl_name());
        viewHolder.tvTime.setText(listBeans.get(position).getStart_time() + "-" + listBeans.get(position).getEnd_time());
        viewHolder.layoutSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrv(listBeans.get(position).getDpv_id());
            }
        });
    }
    private void arrv(String dpv_id) {
        HttpUtil.createRequest("ArriveAdapter", BaseUrl.getInstence().listingChangeStatus()).listingChangeStatus(PreferenceUtils.getInstance().getUserToken(), dpv_id, "5").enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("确认成功");
                    arriveImp.arrive();
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
    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_list_num)
        TextView tvListNum;
        @BindView(R.id.tv_dispatcher)
        TextView tvDispatcher;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.layout_sure)
        LinearLayout layoutSure;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}