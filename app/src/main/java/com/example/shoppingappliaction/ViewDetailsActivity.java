package com.example.shoppingappliaction;

import static com.example.shoppingappliaction.HomeScreen.userlikeArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;

public class ViewDetailsActivity extends AppCompatActivity {

    RelativeLayout buyNow_tv,s_size,m_size,l_size;
    ImageView back_iv,heartView_iv,coffee_iv;
    TextView price_tv,coffeeName_tv,sub_txtTv,s_tv,m_tv,l_tv;
    float price;
    String byteArray;
    DetailsCoffeePojoClass detailsCoffeePojoClass;
    String coffee_name="",extra_details="",money="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        buyNow_tv = findViewById(R.id.buyNow_tv);
        coffeeName_tv = findViewById(R.id.coffeeName_tv);
        sub_txtTv = findViewById(R.id.sub_txt);
        back_iv = findViewById(R.id.back_iv);
        heartView_iv = findViewById(R.id.like_iv);
        price_tv = findViewById(R.id.price_tv);
        s_size = findViewById(R.id.s_size);
        m_size = findViewById(R.id.m_size);
        l_size = findViewById(R.id.l_size);
        s_tv = findViewById(R.id.s_tv);
        coffee_iv = findViewById(R.id.img1);
        m_tv = findViewById(R.id.m_tv);
        l_tv = findViewById(R.id.l_tv);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            coffee_name = bundle.getString("coffee_name");
            extra_details = bundle.getString("extra_details");
            money = bundle.getString("money");
            byteArray = bundle.getString("image_bitmap");

            Uri uri = Uri.parse(byteArray);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            coffee_iv.setImageURI(uri);
             detailsCoffeePojoClass = new DetailsCoffeePojoClass(bitmap,coffee_name,extra_details,money);

        }
        if(!coffee_name.isEmpty()){
            coffeeName_tv.setText(coffee_name);
        }
        if(!extra_details.isEmpty()){
            sub_txtTv.setText(extra_details);
        }
        if (!money.isEmpty()) {
            price = Float.parseFloat(money); // ✅ use variable
            price_tv.setText(String.format("%.2f", price));
        }

        s_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s_size.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.orange_round_corners));
                m_size.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.grey_stroke));
                l_size.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.grey_stroke));

                s_tv.setTextColor(Color.WHITE);
                m_tv.setTextColor(Color.BLACK);
                l_tv.setTextColor(Color.BLACK);


                price = Float.parseFloat(money); // ✅
                price_tv.setText(String.format("%.2f", price));
            }
        });
        m_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s_size.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.grey_stroke));
                m_size.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.orange_round_corners));
                l_size.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.grey_stroke));


                m_tv.setTextColor(Color.WHITE);
                s_tv.setTextColor(Color.BLACK);
                l_tv.setTextColor(Color.BLACK);


                price = Float.parseFloat(money) * 2; // ✅
                price_tv.setText(String.format("%.2f", price));
            }
        });
        l_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s_size.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.grey_stroke));
                m_size.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.grey_stroke));
                l_size.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.orange_round_corners));

                s_tv.setTextColor(Color.BLACK);
                m_tv.setTextColor(Color.BLACK);
                l_tv.setTextColor(Color.WHITE);



                price = Float.parseFloat(money) * 3; // ✅
                price_tv.setText(String.format("%.2f", price));
            }
        });

        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        heartView_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               userlikeArrayList.add(detailsCoffeePojoClass);
                finish();

            }
        });

        buyNow_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  intent = new Intent(ViewDetailsActivity.this,BuyActivity.class);
                startActivity(intent);
            }
        });
    }

}