package com.strivestay.viewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * recyclerview适配器
 *
 * @author StriveStay
 * @date 2017/12/8
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ItemViewHolder> {
    private List<String> mdatas;
    private LayoutInflater mInflater;
    private Context mContext;
    private List<Integer> mHeights;

    public SimpleAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mdatas = data;

        mHeights = new ArrayList<>();
        for (int i = 0; i < mdatas.size(); i++) {
            mHeights.add(new Random().nextInt(150) + 50);
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler, parent, false);
        Log.e("recyclerview","onCreateViewHolder========");
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        Log.e("recyclerview","onBindViewHolder========"+  mdatas.get(position));

        holder.mTv.setText(mdatas.get(position));
//        holder.itemView.getLayoutParams().height = mHeights.get(position);
        // item点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了"+mdatas.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "长按了"+mdatas.get(position), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView mTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
