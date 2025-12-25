package com.example.aplicacion1.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.aplicacion1.data.model.Comentario;
import com.example.aplicacion1.data.model.Producto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaUtils {
    private static final String PREFERENCES_NAME = "UserPrefs";
    private static final String CARRITO_KEY = "carrito";
    private static final String PREFS_COMMENTS = "comentarios";
    private static final String TOTAL_CARRITO_KEY = "totalCarrito";

    public static void guardarCarrito(Context context, List<Producto> carritoProductos) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String jsonCarrito = gson.toJson(carritoProductos);
        editor.putString(CARRITO_KEY, jsonCarrito);
        editor.apply();
    }

    public static List<Producto> cargarCarrito(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonCarrito = preferences.getString(CARRITO_KEY, null);
        Type type = new TypeToken<ArrayList<Producto>>() {}.getType();
        return jsonCarrito != null ? gson.fromJson(jsonCarrito, type) : new ArrayList<>();
    }

    // Guardar comentarios específicos de un producto
    public static void guardarComentarios(Context context, String productoNombre, List<Comentario> comentarios) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_COMMENTS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(comentarios);
        editor.putString(productoNombre, json); // Usa el nombre del producto como clave
        editor.apply();
    }

    // Cargar comentarios específicos de un producto
    public static List<Comentario> cargarComentarios(Context context, String productoNombre) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_COMMENTS, Context.MODE_PRIVATE);
        String json = preferences.getString(productoNombre, null); // Recupera los comentarios usando el nombre del producto
        if (json != null) {
            Type type = new TypeToken<List<Comentario>>() {}.getType();
            return new Gson().fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }

    public static void guardarTotalCarrito(Context context, double total) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(TOTAL_CARRITO_KEY, (float) total); // Guardar como float para mayor precisión
        editor.apply();
    }

    public static double cargarTotalCarrito(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return preferences.getFloat(TOTAL_CARRITO_KEY, 0); // Valor predeterminado: 0
    }

}
