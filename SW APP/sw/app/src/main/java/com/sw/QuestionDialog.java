package com.sw;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class QuestionDialog extends Dialog {
    private Context context;

    private Drawable image;
    private String title,content;

    private OnDialogClick onDialogClick;

    private TextView txtTitle, txtContent;
    private ImageView imgMain;
    private Button btnYes, btnNo, btnClose;
    private EditText editTxt;
    private Button btnSave;
    String dialogName;

    private Boolean result;
    private View self;

    public QuestionDialog(@NonNull Context context) {
        super(context);

        this.context = context;
//inflater xml레이아웃을 붙임
        LayoutInflater view = LayoutInflater.from(context);
        View self = view.inflate(R.layout.dialog_question,null);

        setContentView(self);
//객체들을 가져옴
        txtTitle = self.findViewById(R.id.txt_title);
        txtContent = self.findViewById(R.id.txt_content);
        imgMain = self.findViewById(R.id.img_main);
        btnYes = self.findViewById(R.id.btn_yes);
        btnNo = self.findViewById(R.id.btn_no);
        btnClose = self.findViewById(R.id.btn_close);

        btnSave = self.findViewById(R.id.btn_save);
        editTxt = self.findViewById(R.id.edit);

        imgMain.setImageDrawable(image);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNote(dialogName,editTxt.getText().toString());
                Toast.makeText(getContext(),getNote(dialogName),Toast.LENGTH_SHORT).show();
            }
        });


//버튼 예스, 노, 다이얼로그 닫기 버튼에 대해 다른 행동 저장
        btnYes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                result = true;
                dismiss(); //다이얼로그를 닫는 행동
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                result = false;
                dismiss();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void setDialogName(String dialogName){
        this.dialogName = dialogName;
    }

    private void writeNote(String identifier, String msg){
        SharedPreferences s = getContext().getSharedPreferences("note", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = s.edit();

        e.putString(identifier,msg);
        e.apply();

        Toast.makeText(getContext(),identifier+"에 대한 노트 작성이 완료되었습니다.",Toast.LENGTH_SHORT).show();
    }

    private String getNote(String identifier){
        SharedPreferences s= getContext().getSharedPreferences("note",Context.MODE_PRIVATE);
        return s.getString(identifier,"메모가 없습니다.");
    }

    private void refreshNotes(){

    }

    @Override
    public void show() {
        imgMain.setImageDrawable(image);
        txtTitle.setText((title));
        txtContent.setText((content));
        editTxt.setText(getNote(dialogName));
        super.show();
    }
    @Override
    public void dismiss() { // result 값 버튼을 누르는 거에 따라 다름
        onDialogClick.OnClick(result);//다이얼로그가 닫히는 순간에 프래그먼트에서 지정한 행동 함
        super.dismiss();
    }
    public void setImage(Drawable image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setOnDialogClick(OnDialogClick onDialogClick) {
        this.onDialogClick = onDialogClick;
    }
    public QuestionDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected QuestionDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    public interface OnDialogClick{
        void OnClick(Boolean isYes); //result값을 isYes가 return 해주는 역할(->map_fragment로 전달)
    }
}//인터페이스는 메소드 선언 가능(맘대로 바꾸겠다) map_fragment 에서 new부터 괄호
