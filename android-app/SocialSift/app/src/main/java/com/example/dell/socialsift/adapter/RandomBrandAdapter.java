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
import com.example.dell.socialsift.beans.brandBean.Datum;

import java.util.List;

/**
 * Created by DELL on 10/6/2017.
 */

public class RandomBrandAdapter extends RecyclerView.Adapter<RandomBrandAdapter.CustomViewHolder> {


    private Context mContext;
    private List<Datum> commonModel;

    public interface OnItemClickListener {
        void onItemClick(Datum commonModel, int position);
    }

    private final RandomBrandAdapter.OnItemClickListener listener;

    //ClickListener of RemoveItem Button
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final BrandAdapter.CustomViewHolder holder = (BrandAdapter.CustomViewHolder) view.getTag();
            final int position = holder.getAdapterPosition();

        }
    };

    private String TAG = "ComplaintResolution";

    public RandomBrandAdapter(Context mContext, List<Datum> commonModel, RandomBrandAdapter.OnItemClickListener listener) {
        this.mContext = mContext;
        this.commonModel = commonModel;

        this.listener = listener;
    }


    @Override
    public RandomBrandAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_ro, null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_row_items, null);
        RandomBrandAdapter.CustomViewHolder viewHolder = new RandomBrandAdapter.CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RandomBrandAdapter.CustomViewHolder customViewHolder, int position) {


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

        public void bind(final Datum commonModel, final RandomBrandAdapter.OnItemClickListener listener, final int position) {


            title.setText(commonModel.getName());
            layout.setBackgroundResource(R.drawable.logo_brand);
//            Glide.with(getApplicationContext()).load(commonModel.getBrandLogo()).placeholder(R.drawable.default_image).into(layout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(commonModel, position);

                }
            });


        }




    }


}




