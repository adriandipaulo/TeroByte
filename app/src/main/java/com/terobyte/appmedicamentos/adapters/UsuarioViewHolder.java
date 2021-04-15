package com.terobyte.appmedicamentos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.terobyte.appmedicamentos.R;

public class UsuarioViewHolder extends RecyclerView.ViewHolder {
    private final TextView nombreApellido;
    private final TextView altura;
    private final TextView peso;
    private final TextView presion;

    private UsuarioViewHolder(View ItemView){
        super(ItemView);
        nombreApellido = ItemView.findViewById(R.id.nombreApellido_usuario);
        altura = ItemView.findViewById(R.id.altura_usuario);
        peso = ItemView.findViewById(R.id.peso_usuario);
        presion = ItemView.findViewById(R.id.presion_usuario);
    }

    public void Bind(String texto,Integer pe ,Double al, String pr){
        nombreApellido.setText(texto);
        peso.setText(pe.toString());
        altura.setText(al.toString());
        presion.setText(pr);
    }

    static UsuarioViewHolder create(ViewGroup parent){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.usuario_cardview,parent,false);
        return new UsuarioViewHolder(view);
    }
}
