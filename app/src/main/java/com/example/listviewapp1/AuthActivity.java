package com.example.listviewapp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AuthActivity extends AppCompatActivity {

    EditText nameInput,passwordInput;
    private Button btnValid,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        getSupportActionBar().hide();

        nameInput = findViewById(R.id.name);
        passwordInput = findViewById(R.id.password);

        btnValid = findViewById(R.id.valid_btn);
        btnExit = findViewById(R.id.exit_btn);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthActivity.this.finish();
            }
        });

        btnValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString();
                String password = passwordInput.getText().toString();
                if(name.equalsIgnoreCase("wadia") && password.equalsIgnoreCase("9400")){
                    Intent I = new Intent(AuthActivity.this,HomeActivity.class);
                    I.putExtra("Name",name);
                    startActivity(I);
                }else{
                    Toast.makeText(AuthActivity.this, "Votre information son incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
