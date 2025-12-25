package com.example.aplicacion1.ui.auth;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacion1.ui.main.MainActivity;
import com.example.aplicacion1.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        // Verificar si ya ha iniciado sesión
        SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Obtener credenciales guardadas
                String savedUsername = preferences.getString("username", "");
                String savedPassword = preferences.getString("password", "");
                String savedEmail = preferences.getString("email", "Sin Email"); // Recuperar email guardado

                if (username.equals(savedUsername) && password.equals(savedPassword)) {
                    // Guardar sesión activa y datos del usuario
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.putString("loggedUsername", savedUsername);
                    editor.putString("loggedEmail", savedEmail); // Guardar email en la sesión
                    editor.apply();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Enlace para ir a la pantalla de registro
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
