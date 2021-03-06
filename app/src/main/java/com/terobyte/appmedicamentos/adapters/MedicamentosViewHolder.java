package com.terobyte.appmedicamentos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.terobyte.appmedicamentos.R;

public class MedicamentosViewHolder extends RecyclerView.ViewHolder{
    private final TextView nombreMedicamento;
    private final TextView hora;
    private final TextView dosis;
    private final TextView usuario;
    private final TextView horaMinuto;


    private MedicamentosViewHolder(View ItemView){
        super(ItemView);
        nombreMedicamento = ItemView.findViewById(R.id.nombreMedicamento);
        hora = ItemView.findViewById(R.id.tomarCada);
        dosis = ItemView.findViewById(R.id.dosisATomar);
        usuario = ItemView.findViewById(R.id.usuario);
        horaMinuto = ItemView.findViewById(R.id.horaMinuto);

    }
    public void Bind(String texto,String b,String c,Integer x,String hm){
        nombreMedicamento.setText(texto);
        dosis.setText(b);
        usuario.setText(c);
        hora.setText(x.toString());
        horaMinuto.setText(hm);


        }

    static MedicamentosViewHolder create(ViewGroup parent){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.medicamentos,parent,false);
        return new MedicamentosViewHolder(view);
    }

}
