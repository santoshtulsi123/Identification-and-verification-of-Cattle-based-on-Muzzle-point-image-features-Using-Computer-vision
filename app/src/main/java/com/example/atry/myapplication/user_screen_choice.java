package com.example.atry.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class user_screen_choice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen_choice);
        Button register_cow = (Button) findViewById(R.id.register_cattle_user_choice);
        register_cow.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(getApplicationContext(), regsiter_cattle.class);
                register.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(register);
            }
        });
    }
}
