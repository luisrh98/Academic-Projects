package com.example.aplicacion1.ui.comentario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion1.R;
import com.example.aplicacion1.data.model.Comentario;

import java.util.List;

public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ComentarioViewHolder> {

    private List<Comentario> comentarios; // Cambia a una lista de Comentario

    public ComentariosAdapter(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @NonNull
    @Override
    public ComentarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentario, parent, false);
        return new ComentarioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioViewHolder holder, int position) {
        Comentario comentario = comentarios.get(position);
        holder.txtUsuario.setText(comentario.getUsuario());
        holder.txtMensaje.setText(comentario.getMensaje());
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    public static class ComentarioViewHolder extends RecyclerView.ViewHolder {

        private TextView txtUsuario;
        private TextView txtMensaje;

        public ComentarioViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsuario = itemView.findViewById(R.id.txtUsuario);
            txtMensaje = itemView.findViewById(R.id.txtMensaje);
        }
    }
}