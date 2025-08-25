package com.example.shoppingappliaction;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LikeFragment extends Fragment {

    ArrayList<DetailsCoffeePojoClass> userlikeArrayList;
    RecyclerView recyclerView;
    RelativeLayout recylerView_rl;

    TextView emptyTv;
    Context context;

    public LikeFragment(Context context, ArrayList<DetailsCoffeePojoClass> userlikeArrayList) {
        this.userlikeArrayList = userlikeArrayList;
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_like_fragment2,container,false);

        recyclerView = view.findViewById(R.id.like_recylerview);
        emptyTv = view.findViewById(R.id.emptyTv);
        recylerView_rl = view.findViewById(R.id.recylerView_rl);


        CartandLikeListRecyclerView cartandLikeListRecyclerView;

        if(userlikeArrayList.size() == 0){
            recyclerView.setVisibility(View.GONE);
            emptyTv.setVisibility(View.VISIBLE);
            Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show();
//            cartandLikeListRecyclerView = new CartandLikeListRecyclerView(userlikeArrayList,true,getContext());

        }
        else {

            Toast.makeText(context, "no empty", Toast.LENGTH_SHORT).show();

            recyclerView.setVisibility(View.VISIBLE);
            recylerView_rl.setVisibility(View.VISIBLE);
            emptyTv.setVisibility(View.GONE);
            cartandLikeListRecyclerView = new CartandLikeListRecyclerView(userlikeArrayList,context);
            recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(cartandLikeListRecyclerView);

        }






        return view;
    }
}
