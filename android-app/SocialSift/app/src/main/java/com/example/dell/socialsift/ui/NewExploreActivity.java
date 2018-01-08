package com.example.dell.socialsift.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.socialsift.R;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by DELL on 8/23/2017.
 */

public class NewExploreActivity extends ParentActivity {

    private RecyclerView recyclerView,recyclerView2;
        private CategoryAdapter adapter;
    private HorizontalAdapter adapter2;
    private List<Data2> Data2;
    List<ComplaintCategoryMaster> list = new ArrayList<ComplaintCategoryMaster>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

//        fill_with_Data2();
        init();

    }


    public void init(){


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView2 = (RecyclerView) findViewById(R.id.recycler_view_horizontal);
//        Data2baseHandler = new Data2baseHandler(this);
//        List<ComplaintCategoryMaster> categoryList = new ArrayList<ComplaintCategoryMaster>();

//        for (int i = 0; i < 4; i++) {
            ComplaintCategoryMaster catMaster = new ComplaintCategoryMaster("ABC",R.drawable.img1);

        ComplaintCategoryMaster catMaster1 = new ComplaintCategoryMaster("ABC",R.drawable.img2);

        ComplaintCategoryMaster catMaster2 = new ComplaintCategoryMaster("ABC",R.drawable.img3);

        ComplaintCategoryMaster catMaster3 = new ComplaintCategoryMaster("ABC",R.drawable.img4);

        ComplaintCategoryMaster catMaster4 = new ComplaintCategoryMaster("ABC",R.drawable.img1);

        ComplaintCategoryMaster catMaster5 = new ComplaintCategoryMaster("ABC",R.drawable.img2);

        ComplaintCategoryMaster catMaster6 = new ComplaintCategoryMaster("ABC",R.drawable.img3);

        ComplaintCategoryMaster catMaster7 = new ComplaintCategoryMaster("ABC",R.drawable.img4);

            list.add(catMaster);
        list.add(catMaster1);
        list.add(catMaster2);
        list.add(catMaster3);
        list.add(catMaster5);
        list.add(catMaster6);
        list.add(catMaster7);
        list.add(catMaster4);

//        }

        System.out.println("LIST IS : " + list);


//        list= Data2baseHandler.getComplaintCategory();


        adapter = new CategoryAdapter(this, list, new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ComplaintCategoryMaster commonModel, int position) {

                Toast.makeText(getApplicationContext(),"Hello!!!" , Toast.LENGTH_LONG).show();
//                Intent i = new Intent(getApplicationContext(),ComplaintManagementActivity.class);
//                i.putExtra(Constant.CATEGORY_NAME,commonModel.getCategoryNm());
//                i.putExtra(Constant.CATEGORY_ID,commonModel.getComplaintCategoryId());
//                startActivity(i);

            }

        });



        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);



//        adapter2 = new HorizontalAdapter(this, list, new HorizontalAdapter.OnItemClickListener() {
//
//
//            @Override
//            public void onItemClick(ComplaintCategoryMaster commonModel, int position) {
//
//            }
//        });
        List<Data2> Data2 = new ArrayList<>();

        Data2.add(new Data2( R.drawable.sport, "FIFA Mobile"));
        Data2.add(new Data2( R.drawable.sport2, "EA Sport"));
        Data2.add(new Data2( R.drawable.sport, "ESPN"));
        Data2.add(new Data2( R.drawable.sport, "FIFA Mobile"));
        Data2.add(new Data2( R.drawable.sport2, "EA Sport"));
        Data2.add(new Data2( R.drawable.sport, "ESPN"));
        Data2.add(new Data2( R.drawable.sport, "FIFA Mobile"));
        Data2.add(new Data2( R.drawable.sport2, "EA Sport"));
        Data2.add(new Data2( R.drawable.sport, "ESPN"));

System.out.println("Data2 IS +==> " + Data2);
        adapter2=new HorizontalAdapter(Data2, getApplication());

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(NewExploreActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(horizontalLayoutManager);
        recyclerView2.setAdapter(adapter2);


//        RecyclerView.LayoutManager mLayoutManager2 = new GridLayoutManager(this, 3);
//        recyclerView2.setLayoutManager(mLayoutManager2);
//        recyclerView2.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
////        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView2.setAdapter(adapter);



    }


    public List<Data2> fill_with_Data2() {

        List<Data2> Data2 = new ArrayList<>();




        return Data2;
    }

    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {


        List<Data2> horizontalList = Collections.emptyList();
        Context context;


        public HorizontalAdapter(List<Data2> horizontalList, Context context) {
            this.horizontalList = horizontalList;
            this.context = context;
            System.out.println("horizontalList " + horizontalList);
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView txtview;
            public MyViewHolder(View view) {
                super(view);
                imageView=(ImageView) view.findViewById(R.id.imageview);
                txtview=(TextView) view.findViewById(R.id.txtview);
            }
        }



        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_menu, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.imageView.setImageResource(horizontalList.get(position).getImage());
            holder.txtview.setText(horizontalList.get(position).getName());

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    String list = horizontalList.get(position).getName().toString();
                    Toast.makeText(NewExploreActivity.this, list, Toast.LENGTH_SHORT).show();
                }

            });

        }


        @Override
        public int getItemCount()
        {
            return horizontalList.size();
        }
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
