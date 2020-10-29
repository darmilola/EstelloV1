package com.estello.android;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    TextInputLayout email_layout,password_layout;
    TextInputEditText email_field,password_field;
    TextView forgot_password;
    Button login_button;
    TextView welcome;
    LinearLayout loginRoot;
    int softInputHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
        loginRoot = findViewById(R.id.login_root);
        email_field = findViewById(R.id.login_email);
        password_field = findViewById(R.id.login_password);
        email_layout = findViewById(R.id.login_email_layout);
        password_layout = findViewById(R.id.login_password_layout);
        forgot_password = findViewById(R.id.forgot_Password);
        login_button = findViewById(R.id.login_button);
        welcome = findViewById(R.id.login_welcome_back);
        Typeface customfont= Typeface.createFromAsset(getAssets(),"opensansreg.ttf");
        email_layout.setTypeface(customfont);
        password_layout.setTypeface(customfont);
        forgot_password.setTypeface(customfont);
        login_button.setTypeface(customfont);
        email_field.setTypeface(customfont);
        welcome.setTypeface(customfont);
        password_field.setTypeface(customfont);


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,MainActivity.class));
            }
        });




        loginRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                loginRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                softInputHeight = loginRoot.getRootView().getHeight() - loginRoot.getHeight();

                if(softInputHeight > 150) {


                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(login.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("softInputHeight", softInputHeight);
                    editor.commit();

                }
                loginRoot.getViewTreeObserver().addOnGlobalLayoutListener(this);

            }
        });

    }
}
