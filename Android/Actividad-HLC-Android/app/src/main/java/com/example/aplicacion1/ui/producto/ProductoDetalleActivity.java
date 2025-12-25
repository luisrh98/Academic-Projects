package com.example.aplicacion1.ui.producto;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion1.ui.comentario.ComentariosAdapter;
import com.example.aplicacion1.R;
import com.example.aplicacion1.data.model.Comentario;
import com.example.aplicacion1.data.model.Producto;
import com.example.aplicacion1.data.repository.PersistenciaUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductoDetalleActivity extends AppCompatActivity {

    private List<Comentario> comentarios; // Cambia a una lista de Comentario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_detalle);

        // Recuperar el producto enviado desde MainActivity
        Producto producto = getIntent().getParcelableExtra("producto");
        if (producto != null) {
            ImageView imgProductoDetalle = findViewById(R.id.imgProductoDetalle);
            TextView txtNombreProducto = findViewById(R.id.txtNombreProducto);
            TextView txtPrecioProducto = findViewById(R.id.txtPrecioProducto);
            TextView txtDescripcionProducto = findViewById(R.id.txtDescripcionProducto);
            RecyclerView recyclerComentarios = findViewById(R.id.recyclerComentarios);
            Button btnAgregarComentario = findViewById(R.id.btnAgregarComentario);

            // Configurar vistas con datos del producto
            imgProductoDetalle.setImageResource(producto.getImagenResId());
            txtNombreProducto.setText(producto.getNombre());
            txtPrecioProducto.setText(String.format("%.2f €", producto.getPrecio()));
            txtDescripcionProducto.setText(producto.getDescripcion());

            // Cargar comentarios específicos del producto
            comentarios = PersistenciaUtils.cargarComentarios(this, producto.getNombre());
            if (comentarios == null || comentarios.isEmpty()) {
                comentarios = new ArrayList<>();
            }

            // Crear el adaptador y configurar el RecyclerView
            ComentariosAdapter comentariosAdapter = new ComentariosAdapter(comentarios);
            recyclerComentarios.setLayoutManager(new LinearLayoutManager(this));
            recyclerComentarios.setAdapter(comentariosAdapter);

            // Configurar el botón para agregar un comentario
            btnAgregarComentario.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProductoDetalleActivity.this);
                builder.setTitle("Agregar comentario");
                final EditText input = new EditText(ProductoDetalleActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("Agregar", (dialog, which) -> {
                    String nuevoMensaje = input.getText().toString().trim();
                    if (!nuevoMensaje.isEmpty()) {
                        // Obtener el nombre del usuario actual
                        SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                        String usuario = preferences.getString("loggedUsername", "Anónimo");

                        // Crear un nuevo comentario
                        Comentario nuevoComentario = new Comentario(usuario, nuevoMensaje);

                        // Agregar el comentario a la lista
                        comentarios.add(nuevoComentario);

                        // Guardar los comentarios específicos del producto
                        PersistenciaUtils.guardarComentarios(ProductoDetalleActivity.this, producto.getNombre(), comentarios);

                        // Notificar al adaptador que los datos han cambiado
                        comentariosAdapter.notifyDataSetChanged();
                        Toast.makeText(ProductoDetalleActivity.this, "Comentario agregado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProductoDetalleActivity.this, "Comentario vacío", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
                builder.show();
            });

            Button btnDetalles = findViewById(R.id.btnVolverDetalles);
            btnDetalles.setOnClickListener(v -> finish());
        }
    }
}