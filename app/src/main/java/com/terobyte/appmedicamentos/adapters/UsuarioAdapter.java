package com.terobyte.appmedicamentos.adapters;

import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.terobyte.appmedicamentos.R;
import com.terobyte.appmedicamentos.entidades.Medicamentos;
import com.terobyte.appmedicamentos.entidades.Usuarios;

public class UsuarioAdapter extends ListAdapter<Usuarios,UsuarioViewHolder> {
    private OnItemClickListener listener;

    public UsuarioAdapter(@NonNull DiffUtil.ItemCallback<Usuarios> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return UsuarioViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuarios usuarioActual = getItem(position);
        holder.Bind(usuarioActual.getNombreApellido(),
                usuarioActual.getPeso(),
                usuarioActual.getAltura(),
                usuarioActual.getPresion());

        ImageButton deleteButton= holder.itemView.findViewById(R.id.btn_eliminarUsuarios);
        deleteButton.setOnClickListener(view -> {
            if(listener != null){
                listener.onItemDelete(usuarioActual);
            }
        });

        ImageButton editButton= holder.itemView.findViewById(R.id.btn_editarUsuarios);
        editButton.setOnClickListener(view ->{
            if (listener != null){
                listener.onEditUsuario(usuarioActual);
            }
        });
    }
    public static class usuarioDiff extends DiffUtil.ItemCallback<Usuarios>{
        @Override
        public boolean areItemsTheSame(@NonNull Usuarios oldItem, @NonNull Usuarios newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Usuarios oldItem, @NonNull Usuarios newItem) {
            return false;
        }
    }
    public interface OnItemClickListener{
        void onItemDelete(Usuarios usuarios);
        void onEditUsuario(Usuarios usuarios);
    }

    public void setUsuarioListener(OnItemClickListener listener){
        this.listener= listener;
    }
}
