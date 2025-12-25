package com.example.aplicacion1.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Producto implements Parcelable {
    private String nombre;
    private double precio;
    private int imagenResId;
    private String descripcion;
    private List<Comentario> comentarios; // Cambia a una lista de Comentario

    public Producto(String nombre, double precio, int imagenResId, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagenResId = imagenResId;
        this.descripcion = descripcion;
        this.comentarios = new ArrayList<>();
    }

    // Getters y setters...
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    // Implementación de Parcelable...

    protected Producto(Parcel in) {
        nombre = in.readString();
        precio = in.readDouble();
        imagenResId = in.readInt();
        descripcion = in.readString();
        comentarios = in.createTypedArrayList(Comentario.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeDouble(precio);
        dest.writeInt(imagenResId);
        dest.writeString(descripcion);
        dest.writeTypedList(comentarios);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };
}