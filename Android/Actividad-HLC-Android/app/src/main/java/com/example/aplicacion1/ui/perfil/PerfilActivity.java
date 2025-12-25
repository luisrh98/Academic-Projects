package com.example.aplicacion1.ui.perfil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacion1.R;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Obtener referencias a los TextView
        TextView txtNombreUsuario = findViewById(R.id.txtNombreUsuario);
        TextView txtEmailUsuario = findViewById(R.id.txtEmailUsuario);

// Obtener datos guardados en SharedPreferences
        SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String nombreUsuario = preferences.getString("loggedUsername", "Usuario Desconocido");
        String emailUsuario = preferences.getString("loggedEmail", "Sin Email");

        txtNombreUsuario.setText("Nombre: " + nombreUsuario);
        txtEmailUsuario.setText("Email: " + emailUsuario);


        Button btnVolver = findViewById(R.id.btnVolverPerfil);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad y vuelve a la anterior (MainActivity)
            }
        });
    }
}
