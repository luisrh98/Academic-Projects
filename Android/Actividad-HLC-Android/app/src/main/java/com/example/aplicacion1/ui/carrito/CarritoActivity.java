package com.example.aplicacion1.ui.carrito;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion1.ui.qr.QrScannerActivity;
import com.example.aplicacion1.R;
import com.example.aplicacion1.data.model.Producto;
import com.example.aplicacion1.data.repository.PersistenciaUtils;

import java.util.List;

public class CarritoActivity extends AppCompatActivity {

    private RecyclerView recyclerCarrito;
    private CarritoAdapter carritoAdapter;
    // En esta versión, seguimos usando la lista estática y PersistenciaUtils
    private static List<Producto> carritoProductos;
    private TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        // Referencias a las vistas
        recyclerCarrito = findViewById(R.id.recyclerCarrito);
        Button btnVaciarCarrito = findViewById(R.id.btnVaciarCarrito);
        Button btnVolver = findViewById(R.id.btnVolverCarrito);
        txtTotal = findViewById(R.id.txtTotal);

        // Cargar el carrito desde SharedPreferences
        carritoProductos = PersistenciaUtils.cargarCarrito(this);

        if (carritoProductos == null || carritoProductos.isEmpty()) {
            Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show();
            findViewById(R.id.emptyCarritoMessage).setVisibility(View.VISIBLE);
            recyclerCarrito.setVisibility(View.GONE);
        } else {
            // Configurar el adaptador con el callback para eliminar producto
            carritoAdapter = new CarritoAdapter(carritoProductos, producto -> {
                int position = carritoProductos.indexOf(producto);
                carritoProductos.remove(producto);
                PersistenciaUtils.guardarCarrito(CarritoActivity.this, carritoProductos);
                carritoAdapter.notifyItemRemoved(position);
                Toast.makeText(CarritoActivity.this, "Producto eliminado", Toast.LENGTH_SHORT).show();
                // Actualizar el total después de eliminar un producto
                actualizarTotal(txtTotal, carritoProductos);
            });

            recyclerCarrito.setLayoutManager(new LinearLayoutManager(this));
            recyclerCarrito.setAdapter(carritoAdapter);
            actualizarTotal(txtTotal, carritoProductos);
        }

        // Botón para vaciar carrito con animación scale_down
        btnVaciarCarrito.setOnClickListener(v -> {
            Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
            scaleDown.setDuration(500); // Duración ajustada
            recyclerCarrito.startAnimation(scaleDown);
            scaleDown.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) { }

                @Override
                public void onAnimationEnd(Animation animation) {
                    // Vaciar la lista, guardar los cambios y actualizar la UI
                    carritoProductos.clear();
                    PersistenciaUtils.guardarCarrito(CarritoActivity.this, carritoProductos);
                    carritoAdapter.notifyDataSetChanged();
                    findViewById(R.id.emptyCarritoMessage).setVisibility(View.VISIBLE);
                    recyclerCarrito.setVisibility(View.GONE);
                    actualizarTotal(txtTotal, carritoProductos);
                    Toast.makeText(CarritoActivity.this, "Carrito vaciado", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAnimationRepeat(Animation animation) { }
            });
        });

        // Botón para volver
        btnVolver.setOnClickListener(v -> finish());

        Button btnEscanearQR = findViewById(R.id.btnEscanearQR);
        btnEscanearQR.setOnClickListener(v -> {
            Intent intent = new Intent(CarritoActivity.this, QrScannerActivity.class);
            startActivity(intent);
        });
    }

    // Método para calcular y actualizar el total
    private void actualizarTotal(TextView txtTotal, List<Producto> carritoProductos) {
        double total = 0;
        for (Producto producto : carritoProductos) {
            total += producto.getPrecio();
        }
        PersistenciaUtils.guardarTotalCarrito(this, total);
        txtTotal.setText(String.format("Total:  %.2f €", total));
    }

    public static List<Producto> getCarritoProductos() {
        return carritoProductos;
    }
}
