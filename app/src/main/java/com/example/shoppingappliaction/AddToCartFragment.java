package com.example.shoppingappliaction;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddToCartFragment extends Fragment {

    ArrayList<DetailsCoffeePojoClass> userAddCartArrayList;
    RecyclerView recyclerView;
    RelativeLayout recylerView_rl;

    ImageView addCart_iv,like_iv,back_iv;
    TextView emptyTv;
    Context context;

    public AddToCartFragment(Context context, ArrayList<DetailsCoffeePojoClass> userAddCartArrayList){
        this.context = context;
        this.userAddCartArrayList = userAddCartArrayList;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_like_fragment2,container,false);

        recyclerView = view.findViewById(R.id.like_recylerview);
        emptyTv = view.findViewById(R.id.emptyTv);
        recylerView_rl = view.findViewById(R.id.recylerView_rl);
        addCart_iv = view.findViewById(R.id.addCart_iv);
        like_iv = view.findViewById(R.id.like_iv);
        back_iv = view.findViewById(R.id.back_iv);

        like_iv.setVisibility(View.GONE);
        addCart_iv.setVisibility(View.GONE);
        CartandLikeListRecyclerView cartandLikeListRecyclerView;
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });
        if(userAddCartArrayList.size() == 0){
            recyclerView.setVisibility(View.GONE);
            emptyTv.setText("no items in the cart");
            emptyTv.setVisibility(View.VISIBLE);
//            cartandLikeListRecyclerView = new CartandLikeListRecyclerView(userlikeArrayList,true,getContext());

        }
        else {


            recyclerView.setVisibility(View.VISIBLE);
            recylerView_rl.setVisibility(View.VISIBLE);
            emptyTv.setVisibility(View.GONE);
            cartandLikeListRecyclerView = new CartandLikeListRecyclerView(userAddCartArrayList,context);
            recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(cartandLikeListRecyclerView);

        }



        return view;
    }
}
