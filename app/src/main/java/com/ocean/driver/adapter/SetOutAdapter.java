package com.ocean.driver.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocean.driver.R;
import com.ocean.driver.entity.BillData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetOutAdapter extends RecyclerView.Adapter {



    private Context context;
    List<BillData.DataBean.ListBean> listBeans;

    public SetOutAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<BillData.DataBean.ListBean> listBeans) {
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_set_out, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
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
        viewHolder.tvTime.setText(listBeans.get(position).getStart_time()+"-"+listBeans.get(position).getEnd_time());

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
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}