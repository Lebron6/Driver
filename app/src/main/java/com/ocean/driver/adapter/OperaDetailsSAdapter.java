package com.ocean.driver.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.ocean.driver.R;
import com.ocean.driver.entity.OperaDetails;
import com.ocean.driver.tools.ToastUtil;
import com.ocean.driver.tools.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okio.Utf8;

public class OperaDetailsSAdapter extends RecyclerView.Adapter {



    private Context context;
    List<OperaDetails.DeliveryAddressBean> listBeans;

    public OperaDetailsSAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<OperaDetails.DeliveryAddressBean> listBeans) {
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_opera_details_s, parent, false);
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
        viewHolder.tvName.setText(listBeans.get(position).getName());
        viewHolder.tvInfo.setText(listBeans.get(position).getInfo());
        viewHolder.tvUserPhone.setText(listBeans.get(position).getContract_name()+","+listBeans.get(position).getContract_tel());
        if (TextUtils.isEmpty(listBeans.get(position).getArrive_time())||listBeans.get(position).getArrive_time()==null){
            viewHolder.tvTime.setVisibility(View.GONE);
        }else{
            viewHolder.tvTime.setText("要求到达时间:"+listBeans.get(position).getArrive_time());
        }
        viewHolder.ivNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.copy(context,listBeans.get(position).getInfo())==true){
                    ToastUtil.showToast("已复制当前地址");
                    AmapNaviParams params = new AmapNaviParams(null, null, null, AmapNaviType.DRIVER, AmapPageType.ROUTE);
//启动导航组件
                AmapNaviPage.getInstance().showRouteActivity(context, params, null);
                }
                //终点
//                Poi end = new Poi(listBeans.get(position).getInfo(), new LatLng(listBeans.get(position).get(),116.426319), "B000A816R6");
//
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
        @BindView(R.id.txt_s)
        TextView txtS;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_info)
        TextView tvInfo;
        @BindView(R.id.tv_user_phone)
        TextView tvUserPhone;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.iv_navigation)
        ImageView ivNavigation;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}