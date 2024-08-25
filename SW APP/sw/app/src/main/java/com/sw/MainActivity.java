package com.sw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences s = getSharedPreferences("user", MODE_PRIVATE);

        String savedUser = s.getString("user", "");
        String savedCompany = s.getString("company", "");

        edit1 = findViewById(R.id.idInput);
        edit2 = findViewById(R.id.passwordInput);

        edit1.setText(savedUser);
        edit2.setText(savedCompany);

        Button b = (Button) findViewById(R.id.loginButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = edit1.getText().toString();
                String s2 = edit2.getText().toString();
                if (s1.matches("")) {
                    String s = "You did not enter a User Name";
                    Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (s2.matches("")) {
                    String s = "You did not enter a Company Name";
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    writeNote("user",s1);
                    writeNote("company",s2);

                    Intent intent = new Intent(MainActivity.this, SplashActivity2.class);
                    intent.putExtra("user",s1);
                    intent.putExtra("company",s2);
                    startActivity(intent);
                }
            }
        });
    }

    private void writeNote(String id, String msg){
        SharedPreferences s = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = s.edit();

        e.putString(id,msg);
        e.apply();
    }
}