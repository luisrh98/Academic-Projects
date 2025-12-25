package com.example.aplicacion1.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion1.R;
import com.example.aplicacion1.data.model.Producto;
import com.example.aplicacion1.data.repository.PersistenciaUtils;
import com.example.aplicacion1.ui.auth.LoginActivity;
import com.example.aplicacion1.ui.carrito.CarritoActivity;
import com.example.aplicacion1.ui.perfil.PerfilActivity;
import com.example.aplicacion1.ui.producto.ProductoAdapter;
import com.example.aplicacion1.ui.producto.ProductoClickListener;
import com.example.aplicacion1.ui.producto.ProductoDetalleActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerProductos;
    private ProductoAdapter productoAdapter;
    private List<Producto> listaProductos;
    private List<Producto> carritoProductos;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton btnCarrito;
    private Toolbar toolbar;
    private Button btnLogout;

    private void cerrarSesion() {
        SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.remove("loggedUsername");
        editor.remove("loggedEmail");
        editor.remove("carrito"); // Limpiar el carrito al cerrar sesión
        editor.apply();

        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Verificar si el usuario está autenticado
        SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            // Redirigir a LoginActivity si no está autenticado
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish(); // Finalizar MainActivity para evitar que el usuario regrese aquí presionando "Atrás"
            return; // Salir del método onCreate para evitar ejecutar el resto del código
        }

        // Continuar con la inicialización normal de MainActivity
        setContentView(R.layout.activity_main);


        // Inicializar elementos
        recyclerProductos = findViewById(R.id.recyclerProductos);
        btnCarrito = findViewById(R.id.btnCarrito);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        btnLogout = findViewById(R.id.btnLogout);

        // Configurar Toolbar como ActionBar
        setSupportActionBar(toolbar);

        // Configurar ActionBarDrawerToggle para el menú lateral
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Lista de productos
        listaProductos = new ArrayList<>();
        listaProductos.add(new Producto("Auriculares Bluetooth", 29.99, R.drawable.auriculares, "Auriculares inalámbricos con cancelación de ruido."));
        listaProductos.add(new Producto("Smartwatch", 59.99, R.drawable.smartwatch, "Reloj inteligente con monitor de ritmo cardíaco y GPS incorporado."));
        listaProductos.add(new Producto("Teclado Mecánico", 45.00, R.drawable.teclado, "Teclado mecánico con switches táctiles y retroiluminación RGB."));
        listaProductos.add(new Producto("Mochila Antirrobo", 39.50, R.drawable.mochila_antirrobo, "Mochila con compartimentos seguros y tecnología antirrobo."));
        listaProductos.add(new Producto("Lámpara LED", 19.99, R.drawable.lampara_led, "Lámpara LED ajustable con tres niveles de brillo."));

        // Cargar carrito (usando CarritoUtils)
        carritoProductos = PersistenciaUtils.cargarCarrito(this);

        // Configurar RecyclerView con ProductoAdapter
        productoAdapter = new ProductoAdapter(listaProductos, new ProductoClickListener() {
            @Override
            public void onAddToCartClick(Producto producto) {
                carritoProductos.add(producto);
                PersistenciaUtils.guardarCarrito(MainActivity.this, carritoProductos);
                Toast.makeText(MainActivity.this, "Producto añadido al carrito", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProductDetailsClick(Producto producto) {
                Intent intent = new Intent(MainActivity.this, ProductoDetalleActivity.class);
                intent.putExtra("producto", producto);
                startActivity(intent);
            }
        });

        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        recyclerProductos.setAdapter(productoAdapter);

        // Botón para abrir el carrito
        btnCarrito.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CarritoActivity.class)));

        // Manejo del menú lateral
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                // Ya estamos en MainActivity
            } else if (id == R.id.nav_perfil) {
                startActivity(new Intent(MainActivity.this, PerfilActivity.class));
            } else if (id == R.id.nav_logout) {
                cerrarSesion();
            }
            drawerLayout.closeDrawers();
            return true;
        });

        // Botón de cerrar sesión
        btnLogout.setOnClickListener(v -> cerrarSesion());


    }

    @Override
    protected void onResume() {
        super.onResume();
        carritoProductos = PersistenciaUtils.cargarCarrito(this);
    }
}
