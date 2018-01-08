package com.example.dell.socialsift.ui;

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

class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CustomViewHolder> {


    private Context mContext;
    private List<ComplaintCategoryMaster> commonModel;

    public interface OnItemClickListener {
        void onItemClick(ComplaintCategoryMaster commonModel, int position);
    }

    private final OnItemClickListener listener;

    //ClickListener of RemoveItem Button
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final CustomViewHolder holder = (CustomViewHolder) view.getTag();
            final int position = holder.getAdapterPosition();

        }
    };

    private String TAG = "ComplaintResolution";

    public CategoryAdapter(Context mContext, List<ComplaintCategoryMaster> commonModel, OnItemClickListener listener) {
        this.mContext = mContext;
        this.commonModel = commonModel;

        this.listener = listener;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_ro, null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row_items, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int position) {


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

        public void bind(final ComplaintCategoryMaster commonModel, final OnItemClickListener listener, final int position) {


            title.setText(commonModel.getCategoryNm());
            layout.setBackgroundResource(commonModel.getIconUrl());
//

//            Glide.with(mContext).load(commonModel.getIconUrl()).into(thumbnail);

//
//            layout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listener.onItemClick(commonModel, position);
//
//                }
//            });



        }




    }


}


