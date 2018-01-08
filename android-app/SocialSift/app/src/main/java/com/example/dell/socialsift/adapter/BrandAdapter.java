package com.example.dell.socialsift.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.socialsift.R;


import java.util.List;

/**
 * Created by DELL on 8/23/2017.
 */

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.CustomViewHolder> {


    private Context mContext;
    private List<ComplaintCategoryMaster2> commonModel;

    public interface OnItemClickListener {
        void onItemClick(ComplaintCategoryMaster2 commonModel, int position);
    }

    private final BrandAdapter.OnItemClickListener listener;

    //ClickListener of RemoveItem Button
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final BrandAdapter.CustomViewHolder holder = (BrandAdapter.CustomViewHolder) view.getTag();
            final int position = holder.getAdapterPosition();

        }
    };

    private String TAG = "ComplaintResolution";

    public BrandAdapter(Context mContext, List<ComplaintCategoryMaster2> commonModel, BrandAdapter.OnItemClickListener listener) {
        this.mContext = mContext;
        this.commonModel = commonModel;

        this.listener = listener;
    }


    @Override
    public BrandAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_ro, null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_row_items, null);
        BrandAdapter.CustomViewHolder viewHolder = new BrandAdapter.CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BrandAdapter.CustomViewHolder customViewHolder, int position) {


        customViewHolder.bind(commonModel.get(position), listener, position);

    }

    @Override
    public int getItemCount() {
        return (null != commonModel ? commonModel.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {



        private TextView title;
        private ImageView thumbnail;
        private RelativeLayout layout;



        public CustomViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.title);
            this.layout  = (RelativeLayout) itemView.findViewById(R.id.reltive_layout);
//            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);


        }

        public void bind(final ComplaintCategoryMaster2 commonModel, final BrandAdapter.OnItemClickListener listener, final int position) {


            title.setText(commonModel.getCategoryNm());
            layout.setBackgroundResource(commonModel.getIconUrl());



        }




    }


}



