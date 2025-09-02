package com.example.shoppingappliaction;

import static com.example.shoppingappliaction.MainActivity.complete_address;
import static com.example.shoppingappliaction.MainActivity.currentPlace;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class BuyActivity extends AppCompatActivity {
    DetailsCoffeePojoClass detailsCoffeePojoClass;
    TextView addresstv,delivery_address_tv,increment_no_tv,extraDetails_buy_tv,coffeeName_buy_tv,totalPayment_tv,price_buy_tv,delivery_fee_tv;
    ImageView imageView;
    int delivery_fee= 0,increment_no = 1;
    float money ;
    RelativeLayout increment_rl,decrement_rl;
    String coffee_name="",extra_details="";
    String byteArray;
    RadioButton cash_tv;
    RelativeLayout editAddress_rl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        extraDetails_buy_tv = findViewById(R.id.extraDetails_buy_tv);
        coffeeName_buy_tv = findViewById(R.id.coffeeName_buy_tv);
        totalPayment_tv = findViewById(R.id.totalPayment_tv);
        delivery_fee_tv = findViewById(R.id.delivery_fee_tv);
        price_buy_tv = findViewById(R.id.price_buy_tv);
        increment_rl = findViewById(R.id.increment);
        decrement_rl = findViewById(R.id.decrement);
        editAddress_rl = findViewById(R.id.editAddress_rl);
        increment_no_tv = findViewById(R.id.increment_no_tv);
        delivery_address_tv = findViewById(R.id.delivery_address_tv);
        addresstv = findViewById(R.id.address2);
        price_buy_tv = findViewById(R.id.price_buy_tv);
        cash_tv = findViewById(R.id.cash_tv);
        imageView = findViewById(R.id.img1);
        float unitPrice = 0; // store single item price


        delivery_fee = 10;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            coffee_name = bundle.getString("coffee_name");
            extra_details = bundle.getString("extra_details");
            money = bundle.getFloat("money");
            unitPrice = money;
            byteArray = bundle.getString("image_bitmap");

            Uri uri = Uri.parse(byteArray);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            imageView.setImageURI(uri);

        }

        if(!coffee_name.isEmpty()){
            coffeeName_buy_tv.setText(coffee_name);
        }
        if(!extra_details.isEmpty()){
            extraDetails_buy_tv.setText(extra_details);
        }
        if(money !=0 ){
            price_buy_tv.setText("$ "+ money);
        }
        if(!coffee_name.isEmpty()){
            coffeeName_buy_tv.setText(coffee_name);
        }

        delivery_fee_tv.setText(String.valueOf(delivery_fee));
        delivery_address_tv.setText(currentPlace);

        addresstv.setText(complete_address);
        float finalUnitPrice = unitPrice;
        increment_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment_no++;
                increment_no_tv.setText(String.valueOf(increment_no));

                money = finalUnitPrice * increment_no;  // calculate fresh
                price_buy_tv.setText(String.valueOf( money));
                totalPayment_tv.setText(String.valueOf((money + delivery_fee)));
                cash_tv.setText("$"+String.valueOf((money + delivery_fee)));
            }
        });

        editAddress_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetView bottomSheetView = new BottomSheetView(getApplicationContext(),currentPlace,complete_address);
                bottomSheetView.setAddressCallBack(new AddressCallBack() {
                    @Override
                    public void onChangedAddress(String currentPlace, String completeAddress) {
                        delivery_address_tv.setText(currentPlace);

                        addresstv.setText(completeAddress);
                    }
                });
                bottomSheetView.setCancelable(false);
                bottomSheetView.show(getSupportFragmentManager(),"bottomsheet");
            }
        });

        decrement_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (increment_no > 1) {
                    increment_no--;
                }

                increment_no_tv.setText(String.valueOf(increment_no));

                money = finalUnitPrice * increment_no;  // calculate fresh
                price_buy_tv.setText(String.valueOf( money));
                totalPayment_tv.setText(String.valueOf((money + delivery_fee)));
                cash_tv.setText("$"+String.valueOf((money + delivery_fee)));

            }
        });
        cash_tv.setText("$"+String.valueOf((money + delivery_fee)));


        totalPayment_tv.setText(String.valueOf((money+delivery_fee)));
    }
}