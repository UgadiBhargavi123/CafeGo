package com.example.shoppingappliaction;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetView extends BottomSheetDialogFragment {

    EditText current_place_et,complete_address_et;
    Button submitBtn;
    ImageView close_iv,back_iv;
    String currentPlace ="", completeAddress ="";
    AddressCallBack addressCallBack;
    Context context;

    public BottomSheetView(Context context,String currentPlace,String completeAddress ){
        this.context = context;
        this.currentPlace = currentPlace;
        this.completeAddress = completeAddress;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_address_layout,container,false);
        current_place_et = view.findViewById(R.id.current_place_et);
        complete_address_et = view.findViewById(R.id.complete_place_et);
        submitBtn = view.findViewById(R.id.verify_otp_btn);
        close_iv = view.findViewById(R.id.close_iv);
        back_iv = view.findViewById(R.id.back_iv);
        current_place_et.setText(currentPlace);
        complete_address_et.setText(completeAddress);
        close_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPlace = current_place_et.getText().toString();
                completeAddress = complete_address_et.getText().toString();

                if(!currentPlace.isEmpty() && !completeAddress.isEmpty()){
                    addressCallBack.onChangedAddress(currentPlace,completeAddress);
                    dismiss();
                }
                else{
                    Toast.makeText(context, "Please enter the complete address", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    public void setAddressCallBack(AddressCallBack callBack) {
        this.addressCallBack = callBack;
    }
}
