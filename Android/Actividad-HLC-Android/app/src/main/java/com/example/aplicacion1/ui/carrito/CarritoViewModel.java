package com.example.aplicacion1.ui.carrito;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.aplicacion1.data.model.Producto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CarritoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Producto>> carritoLiveData;
    private static final String PREFERENCES_NAME = "UserPrefs";
    private static final String CARRITO_KEY = "carrito";

    public CarritoViewModel(@NonNull Application application) {
        super(application);
        carritoLiveData = new MutableLiveData<>();
        cargarCarritoDesdeSharedPreferences();
    }

    public LiveData<List<Producto>> getCarrito() {
        return carritoLiveData;
    }

    public void agregarProducto(Producto producto) {
        List<Producto> carrito = new ArrayList<>(carritoLiveData.getValue());
        carrito.add(producto);
        carritoLiveData.setValue(carrito);
        guardarCarritoEnSharedPreferences(carrito);
    }

    public void eliminarProducto(Producto producto) {
        List<Producto> carrito = new ArrayList<>(carritoLiveData.getValue());
        carrito.remove(producto);
        carritoLiveData.setValue(carrito);
        guardarCarritoEnSharedPreferences(carrito);
    }

    public void vaciarCarrito() {
        carritoLiveData.setValue(new ArrayList<>());
        guardarCarritoEnSharedPreferences(new ArrayList<>());
    }

    private void cargarCarritoDesdeSharedPreferences() {
        SharedPreferences preferences = getApplication().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonCarrito = preferences.getString(CARRITO_KEY, null);
        Type type = new TypeToken<ArrayList<Producto>>() {}.getType();
        List<Producto> carrito = jsonCarrito != null ? gson.fromJson(jsonCarrito, type) : new ArrayList<>();
        carritoLiveData.setValue(carrito);
    }

    private void guardarCarritoEnSharedPreferences(List<Producto> carrito) {
        SharedPreferences preferences = getApplication().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String jsonCarrito = gson.toJson(carrito);
        editor.putString(CARRITO_KEY, jsonCarrito);
        editor.apply();
    }
}
