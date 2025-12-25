package com.example.aplicacion1.ui.producto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion1.R;
import com.example.aplicacion1.data.model.Producto;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> listaProductos;
    private ProductoClickListener productoClickListener;

    public ProductoAdapter(List<Producto> listaProductos, ProductoClickListener productoClickListener) {
        this.listaProductos = listaProductos;
        this.productoClickListener = productoClickListener;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);
        holder.bind(producto);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        private TextView nombreProducto;
        private TextView precioProducto;
        private ImageView imgProducto;
        private ImageView imgEliminar; // Usado para añadir al carrito

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProducto = itemView.findViewById(R.id.nombreProducto);
            precioProducto = itemView.findViewById(R.id.precioProducto);
            imgProducto = itemView.findViewById(R.id.imgProducto);
            imgEliminar = itemView.findViewById(R.id.imgEliminar);

            // Al pulsar la imagen del producto se abren los detalles
            imgProducto.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && productoClickListener != null) {
                    productoClickListener.onProductDetailsClick(listaProductos.get(pos));
                }
            });

            // Al pulsar el icono de "eliminar" se añade al carrito (según tu requerimiento)
            imgEliminar.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && productoClickListener != null) {
                    productoClickListener.onAddToCartClick(listaProductos.get(pos));
                }
            });
        }

        public void bind(Producto producto) {
            nombreProducto.setText(producto.getNombre());
            precioProducto.setText(String.format("%.2f €", producto.getPrecio()));
            imgProducto.setImageResource(producto.getImagenResId());
        }
    }
}
