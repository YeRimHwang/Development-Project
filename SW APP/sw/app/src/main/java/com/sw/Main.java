package com.sw;

import android.view.View;

public class Main {

    private String btnName;
    private String btnDescription;
    private View.OnClickListener btnClickListener;

    public Main(String btnName,View.OnClickListener btnClickListener) {
        this.btnName = btnName;
        this.btnClickListener = btnClickListener;
    }

    public void setBtnDescription(String btnDescription) {
        this.btnDescription = btnDescription;
    }

    public String getBtnDescription(){
        return btnDescription;
    }

    public String getBtnName() {
        return btnName;
    }

    public View.OnClickListener getBtnClickListener() {
        return btnClickListener;
    }

}
