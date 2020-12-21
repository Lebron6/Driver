package com.ocean.driver.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ocean.driver.R;
import com.ocean.driver.activity.OperaDetailsActivity;
import com.ocean.driver.api.BaseUrl;
import com.ocean.driver.api.HttpUtil;
import com.ocean.driver.callback.NotiImp;
import com.ocean.driver.dialog.QrCodeDialog;
import com.ocean.driver.dialog.RejectOperaRemarksDialog;
import com.ocean.driver.entity.ApiResponse;
import com.ocean.driver.entity.OperaListData;
import com.ocean.driver.entity.OperaSetoutList;
import com.ocean.driver.tools.PreferenceUtils;
import com.ocean.driver.tools.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OperaSetoutAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<OperaSetoutList.ListBean> listBeans;
    double Latitude;
    double Longitude;

    public OperaSetoutAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<OperaSetoutList.ListBean> listBeans) {
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }

    public void setZb(double Latitude, double Longitude) {
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_opera_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        viewHolder.tvHwxx.setText(listBeans.get(position).getGoods_jnum() + "件," + listBeans.get(position).getTotal_weight() + "吨," + listBeans.get(position).getTotal_volume() + "m³");
        viewHolder.tvBzlx.setText(listBeans.get(position).getPk_name());
        viewHolder.tvJhdz.setText(listBeans.get(position).getPickup_address());
        viewHolder.tvJiaohdz.setText(listBeans.get(position).getDelivery_address());
        viewHolder.tvAddrS.setText(listBeans.get(position).getStart_city());
        viewHolder.tvAddrE.setText(listBeans.get(position).getEnd_city());
        viewHolder.tvYqddsj.setText(listBeans.get(position).getArrival_time());
        viewHolder.tvBczz.setText(listBeans.get(position).getNow_goods_num()+"/"+listBeans.get(position).getNow_goods_jnum());
        if (!TextUtils.isEmpty(listBeans.get(position).getS_type())){
            switch (listBeans.get(position).getS_type()) {
                //供应商类型 1 提货 2 干线 3 派送 4 交货 5 其他
                case "1":
                    viewHolder.tvStatus.setText("提货");
                    break;
                case "2":
                    viewHolder.tvStatus.setText("干线");
                    break;
                case "3":
                    viewHolder.tvStatus.setText("派送");
                    break;
                case "4":
                    viewHolder.tvStatus.setText("交货");
                    break;
                case "5":
                    viewHolder.tvStatus.setText("其它");
                    break;

            }
        }

        switch (listBeans.get(position).getS_status()) {//供应商状态 1 待受理 2 驳回 3 已受理 4 途中 5 完成
            case "1":
                viewHolder.tvXq.setVisibility(View.VISIBLE);
                viewHolder.tvBh.setVisibility(View.VISIBLE);
                viewHolder.tvSl.setVisibility(View.VISIBLE);

                viewHolder.tvCkewm.setVisibility(View.GONE);
                viewHolder.tvBhzt.setVisibility(View.GONE);
                viewHolder.tvCf.setVisibility(View.GONE);
                viewHolder.tvSmjh.setVisibility(View.GONE);
                viewHolder.tvYswc.setVisibility(View.GONE);
                break;
            case "2":
                viewHolder.tvXq.setVisibility(View.VISIBLE);
                viewHolder.tvBh.setVisibility(View.GONE);
                viewHolder.tvSl.setVisibility(View.GONE);

                viewHolder.tvCkewm.setVisibility(View.VISIBLE);
                viewHolder.tvBhzt.setVisibility(View.VISIBLE);
                viewHolder.tvCf.setVisibility(View.GONE);
                viewHolder.tvSmjh.setVisibility(View.GONE);
                viewHolder.tvYswc.setVisibility(View.GONE);
                break;
            case "3":
                viewHolder.tvXq.setVisibility(View.VISIBLE);
                viewHolder.tvCkewm.setVisibility(View.VISIBLE);
                    viewHolder.tvCf.setVisibility(View.VISIBLE);
                    viewHolder.tvSmjh.setVisibility(View.GONE);
                viewHolder.tvBh.setVisibility(View.GONE);
                viewHolder.tvSl.setVisibility(View.GONE);
                viewHolder.tvBhzt.setVisibility(View.GONE);
                viewHolder.tvYswc.setVisibility(View.GONE);
                break;
            case "4":
                viewHolder.tvXq.setVisibility(View.VISIBLE);
                viewHolder.tvCkewm.setVisibility(View.VISIBLE);
                if (listBeans.get(position).isHas_next() == false) {
                    viewHolder.tvYswc.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.tvCf.setVisibility(View.VISIBLE);
                    viewHolder.tvCf.setText("已出发");
                    viewHolder.tvCf.setEnabled(false);
                }
                viewHolder.tvSmjh.setVisibility(View.GONE);
                viewHolder.tvBh.setVisibility(View.GONE);
                viewHolder.tvSl.setVisibility(View.GONE);
                viewHolder.tvBhzt.setVisibility(View.GONE);

                break;
            case "5":
                viewHolder.tvXq.setVisibility(View.VISIBLE);
                viewHolder.tvCkewm.setVisibility(View.VISIBLE);
                viewHolder.tvBh.setVisibility(View.GONE);
                viewHolder.tvSl.setVisibility(View.GONE);

                viewHolder.tvBhzt.setVisibility(View.GONE);
                viewHolder.tvCf.setVisibility(View.GONE);
                viewHolder.tvSmjh.setVisibility(View.GONE);
                viewHolder.tvYswc.setVisibility(View.GONE);
                break;
        }

        viewHolder.tvXq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OperaDetailsActivity.actionStart(context, listBeans.get(position).getOsd_id(),listBeans.get(position).getO_id());
            }
        });
        viewHolder.tvCkewm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String> map=new HashMap<>();
                map.put("o_id",listBeans.get(position).getO_id());
                Log.e("map.toString",map.toString());
                QrCodeDialog dialog=new QrCodeDialog(context,R.style.MyDialog,new Gson().toJson(map));
                dialog.show();
            }
        });
        viewHolder.tvSl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sl(listBeans.get(position).getOsd_id());
            }
        });
        viewHolder.tvBh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reject(listBeans.get(position).getOsd_id());
            }
        });
        viewHolder.tvCf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cf(listBeans.get(position).getOsd_id());
            }
        });
        viewHolder.tvSmjh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        viewHolder.tvYswc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(listBeans.get(position).getOsd_id());
            }
        });
    }

    //运输完成
    private void finish(String osd_id) {
        HttpUtil.createRequest("Adapter", BaseUrl.getInstence().listingFinish()).listingFinish(PreferenceUtils.getInstance().getUserToken(), osd_id, Longitude + "", Latitude + ""
        ).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("已完成");
                    imp.noti();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:操作失败");
            }
        });
    }

    //出发
    private void cf(String osd_id) {

        HttpUtil.createRequest("Adapter", BaseUrl.getInstence().listingSetout()).listingSetout(PreferenceUtils.getInstance().getUserToken(), osd_id, Longitude + "", Latitude + ""
        ).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("已出发");
                    imp.noti();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:操作失败");
            }
        });
    }

    //驳回
    private void reject(String osd_id) {
        RejectOperaRemarksDialog dialog = new RejectOperaRemarksDialog(context, R.style.Theme_AppCompat_Dialog, osd_id);
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                imp.noti();
            }
        });
    }

    NotiImp imp;

    public void setNotiImp(NotiImp imp) {
        this.imp = imp;
    }

    //受理
    private void sl(String osd_id) {
        HttpUtil.createRequest("Adapter", BaseUrl.getInstence().listingReceipt()).listingReceipt(PreferenceUtils.getInstance().getUserToken(), osd_id
        ).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("受理成功");
                    imp.noti();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:操作失败");
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
        @BindView(R.id.tv_addr_s)
        TextView tvAddrS;
        @BindView(R.id.iv_grass)
        ImageView ivGrass;
        @BindView(R.id.tv_addr_e)
        TextView tvAddrE;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_hwxx)
        TextView tvHwxx;
        @BindView(R.id.tv_bzlx)
        TextView tvBzlx;
        @BindView(R.id.tv_jhdz)
        TextView tvJhdz;
        @BindView(R.id.tv_jiaohdz)
        TextView tvJiaohdz;
        @BindView(R.id.tv_yqddsj)
        TextView tvYqddsj;
        @BindView(R.id.tv_xq)
        TextView tvXq;
        @BindView(R.id.tv_ckewm)
        TextView tvCkewm;
        @BindView(R.id.tv_bh)
        TextView tvBh;
        @BindView(R.id.tv_sl)
        TextView tvSl;
        @BindView(R.id.tv_bhzt)
        TextView tvBhzt;
        @BindView(R.id.tv_cf)
        TextView tvCf;
        @BindView(R.id.tv_smjh)
        TextView tvSmjh;
        @BindView(R.id.tv_yswc)
        TextView tvYswc;
        @BindView(R.id.layout_vis)
        LinearLayout layoutVis;
        @BindView(R.id.tv_bczz)
        TextView tvBczz;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}