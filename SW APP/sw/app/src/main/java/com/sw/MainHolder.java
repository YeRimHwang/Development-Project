package com.sw;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainHolder extends RecyclerView.ViewHolder {
    private Button btnMain;
    private TextView txtDescription;
    private boolean isExpended;
    public MainHolder (@NonNull View itemView){
        super(itemView);
        findObjects(itemView);
    }
    private void findObjects(View self){
        btnMain = self.findViewById(R.id.btn_main);
        txtDescription = self.findViewById(R.id.txt_description);
    }
    private void changeVisibility(){
        txtDescription.setVisibility(isExpended ? View.VISIBLE : View.GONE);
    }
    public void bind(Main main){
        txtDescription.setVisibility(View.GONE);
        if (main.getBtnName() !=null)
            btnMain.setText(main.getBtnName());
        if(main.getBtnDescription() != null)
            txtDescription.setText(main.getBtnDescription());
        if (main.getBtnClickListener() != null)
            btnMain.setOnClickListener(main.getBtnClickListener());
        else{
            btnMain.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    isExpended = !isExpended;
                    changeVisibility();
                }
            });
        }
    }
}

