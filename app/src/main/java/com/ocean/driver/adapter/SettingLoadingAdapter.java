package com.ocean.driver.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.driver.R;
import com.ocean.driver.entity.CountingInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingLoadingAdapter extends RecyclerView.Adapter {



    private Context context;
    private CountingInfo countingInfo;

    public SettingLoadingAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(CountingInfo countingInfo) {
        this.countingInfo = countingInfo;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_setting_loading, parent, false);
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
        viewHolder.ivShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.layoutC.setVisibility(viewHolder.layoutC.getVisibility()==View.GONE?View.VISIBLE:View.GONE);
                viewHolder.layoutT.setVisibility(viewHolder.layoutT.getVisibility()==View.GONE?View.VISIBLE:View.GONE);
            }
        });
        viewHolder.tvGoodsNum.setText(countingInfo.getGoods().get(position).getPro_num());
        viewHolder.tvTypeNum.setText(countingInfo.getGoods().get(position).getJnum());
        viewHolder.etGoodsNum.setText(countingInfo.getGoods().get(position).getNum());
        viewHolder.tvGoodsName.setText(countingInfo.getGoods().get(position).getName()+"");
        viewHolder.tvVolume.setText(countingInfo.getGoods().get(position).getVolume()+"");
        viewHolder.tvWeight.setText(countingInfo.getGoods().get(position).getWeight()+"");
        viewHolder.tvAdmissions.setText(countingInfo.getGoods().get(position).getTake_num()+"");
        if (countingInfo.getGoods().get(position).getType() == 1) {
            viewHolder.cbSelectBill.setChecked(true);
        } else {
            viewHolder.cbSelectBill.setChecked(false);
        }
        viewHolder.cbSelectBill.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    countingInfo.getGoods().get(position).setType(1);
                } else {
                    countingInfo.getGoods().get(position).setType(2);
                }
            }
        });

        viewHolder.etGoodsNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                countingInfo.getGoods().get(position).setNum(editable.toString());
            }
        });
        viewHolder.tvTypeNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                countingInfo.getGoods().get(position).setJnum(editable.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return countingInfo.getGoods().size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    @OnClick(R.id.iv_show)
    public void onViewClicked() {
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_select_bill)
        CheckBox cbSelectBill;
        @BindView(R.id.txt_goods_num)
        TextView txtGoodsNum;
        @BindView(R.id.tv_goods_num)
        TextView tvGoodsNum;
        @BindView(R.id.iv_show)
        ImageView ivShow;
        @BindView(R.id.tv_goods_name)
        TextView tvGoodsName;
        @BindView(R.id.package_name)
        TextView packageName;
        @BindView(R.id.tv_weight)
        TextView tvWeight;
        @BindView(R.id.layout_t)
        LinearLayout layoutT;
        @BindView(R.id.tv_admissions)
        TextView tvAdmissions;
        @BindView(R.id.tv_volume)
        TextView tvVolume;
        @BindView(R.id.layout_c)
        LinearLayout layoutC;
        @BindView(R.id.et_goods_num)
        EditText etGoodsNum;
        @BindView(R.id.tv_type_num)
        EditText tvTypeNum;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}