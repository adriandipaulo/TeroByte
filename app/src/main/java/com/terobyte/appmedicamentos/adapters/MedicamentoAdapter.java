package com.terobyte.appmedicamentos.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.terobyte.appmedicamentos.R;
import com.terobyte.appmedicamentos.entidades.Medicamentos;

public class MedicamentoAdapter extends ListAdapter<Medicamentos,MedicamentosViewHolder> {

    private OnItemClickListener listener;

    public MedicamentoAdapter(@NonNull DiffUtil.ItemCallback<Medicamentos> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MedicamentosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MedicamentosViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicamentosViewHolder holder, int position) {
        Medicamentos medicamentoActual= getItem(position);
        holder.Bind(medicamentoActual.getNombreMedicamento(),
                medicamentoActual.getPresentacion(),
                medicamentoActual.getUsuario(),medicamentoActual.getTomar_cada(),medicamentoActual.getHoraMinuto());

        ImageButton delteteButton= holder.itemView.findViewById(R.id.eliminar);
        delteteButton.setOnClickListener(view ->{
            if (listener != null){
                listener.onItemDelete(medicamentoActual);
            }
        });

        ImageButton editButton= holder.itemView.findViewById(R.id.editar);
        editButton.setOnClickListener(view ->{
            if (listener != null){
                listener.onEditMedicamento(medicamentoActual);
            }
        });

    }
    public static class medicamentoDiff extends DiffUtil.ItemCallback<Medicamentos>{
        @Override
        public boolean areItemsTheSame(@NonNull Medicamentos oldItem, @NonNull Medicamentos newItem) {
            return oldItem.getId_medicamento() == newItem.getId_medicamento() && oldItem.getTomar_cada()==newItem.getTomar_cada();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Medicamentos oldItem, @NonNull Medicamentos newItem) {
            return oldItem.getNombreMedicamento().equals(newItem.getNombreMedicamento()) &&
                    oldItem.getPresentacion().equals(newItem.getPresentacion()) &&
                    oldItem.getUsuario().equals(newItem.getUsuario()) &&
                    oldItem.getHoraMinuto().equals(newItem.getHoraMinuto());
        }
    }

    public interface OnItemClickListener{
        void onItemDelete(Medicamentos medicamento);
        void onEditMedicamento(Medicamentos medicamento);
    }

    public void setMeicamentoListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
