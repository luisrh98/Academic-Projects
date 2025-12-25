package com.example.aplicacion1.ui.producto;

import com.example.aplicacion1.data.model.Producto;

public interface ProductoClickListener {
    void onAddToCartClick(Producto producto);
    void onProductDetailsClick(Producto producto);
}
