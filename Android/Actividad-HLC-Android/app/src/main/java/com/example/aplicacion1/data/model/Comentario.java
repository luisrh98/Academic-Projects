package com.example.aplicacion1.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Comentario implements Parcelable {
    private String usuario;
    private String mensaje;

    // Constructor
    public Comentario(String usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    // Getters
    public String getUsuario() {
        return usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    // Implementación de Parcelable

    protected Comentario(Parcel in) {
        usuario = in.readString();
        mensaje = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(usuario);
        dest.writeString(mensaje);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Comentario> CREATOR = new Creator<Comentario>() {
        @Override
        public Comentario createFromParcel(Parcel in) {
            return new Comentario(in);
        }

        @Override
        public Comentario[] newArray(int size) {
            return new Comentario[size];
        }
    };
}