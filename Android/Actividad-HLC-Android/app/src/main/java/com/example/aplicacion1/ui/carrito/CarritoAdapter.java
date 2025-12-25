package com.example.aplicacion1.ui.carrito;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion1.R;
import com.example.aplicacion1.data.model.Producto;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder> {
    private List<Producto> listaCarrito;
    private OnItemClickListener listener;

    public CarritoAdapter(List<Producto> listaCarrito, OnItemClickListener listener) {
        this.listaCarrito = listaCarrito;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarritoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new CarritoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoViewHolder holder, int position) {
        Producto producto = listaCarrito.get(position);
        holder.bind(producto);
    }

    @Override
    public int getItemCount() {
        return listaCarrito.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Producto producto);
    }

    public class CarritoViewHolder extends RecyclerView.ViewHolder {
        private TextView nombreProducto;
        private TextView precioProducto;
        private ImageView imgProducto, imgEliminar;

        public CarritoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProducto = itemView.findViewById(R.id.nombreProducto);
            precioProducto = itemView.findViewById(R.id.precioProducto);
            imgProducto = itemView.findViewById(R.id.imgProducto);
            imgEliminar = itemView.findViewById(R.id.imgEliminar);

            // Animación fade_out al eliminar un producto
            imgEliminar.setOnClickListener(v -> {
                Animation fadeOut = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.fade_out);
                fadeOut.setDuration(400); // Duración ajustada
                itemView.startAnimation(fadeOut);
                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) { }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // Al finalizar la animación se llama al callback para eliminar el producto
                        listener.onItemClick(listaCarrito.get(getAdapterPosition()));
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) { }
                });
            });
        }

        public void bind(Producto producto) {
            nombreProducto.setText(producto.getNombre());
            precioProducto.setText(String.format(" %.2f €", producto.getPrecio()));
            imgProducto.setImageResource(producto.getImagenResId());
        }
    }
}
