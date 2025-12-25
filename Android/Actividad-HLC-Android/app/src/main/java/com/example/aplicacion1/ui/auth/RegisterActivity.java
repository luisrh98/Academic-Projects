package com.example.aplicacion1.ui.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacion1.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etNewUsername, etNewPassword, etNewEmail; // Agregado etNewEmail
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNewUsername = findViewById(R.id.etNewUsername);
        etNewPassword = findViewById(R.id.etNewPassword);
        etNewEmail = findViewById(R.id.etNewEmail); // Obtener email del XML
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = etNewUsername.getText().toString().trim();
                String newPassword = etNewPassword.getText().toString().trim();
                String newEmail = etNewEmail.getText().toString().trim(); // Obtener email

                if (newUsername.isEmpty() || newPassword.isEmpty() || newEmail.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Guardar usuario en SharedPreferences
                    SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", newUsername);
                    editor.putString("password", newPassword);
                    editor.putString("email", newEmail); // Guardamos el email
                    editor.apply();

                    Toast.makeText(RegisterActivity.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();

                    // Volver al login
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });
    }
}
