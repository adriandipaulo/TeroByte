package com.terobyte.appmedicamentos.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.terobyte.appmedicamentos.entidades.Usuarios;
import com.terobyte.appmedicamentos.repsitories.UsuarioRepository;

import java.util.List;

public class UsuarioViewModel extends AndroidViewModel {
    private UsuarioRepository usuarioRepository;
    private final LiveData<List<Usuarios>> usuarios;

    public UsuarioViewModel(Application application) {
        super(application);
        usuarioRepository = new UsuarioRepository(application);
        usuarios = usuarioRepository.VerUsuarios();
    }

    public LiveData<List<Usuarios>> verUsuarios() {
        return usuarios;
    }

    public void insert(Usuarios usuario) {
        usuarioRepository.agregarUsuario(usuario);
    }

    public void delete(Usuarios usuario){
        usuarioRepository.eliminarUsuario(usuario);
    }

    public void update(Usuarios usuario){
        usuarioRepository.editarUsuario(usuario);
    }
}
