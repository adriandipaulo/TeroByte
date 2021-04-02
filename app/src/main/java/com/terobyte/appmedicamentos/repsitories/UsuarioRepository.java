package com.terobyte.appmedicamentos.repsitories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.terobyte.appmedicamentos.dao.IUsuariosDao;
import com.terobyte.appmedicamentos.database.AppDataBase;
import com.terobyte.appmedicamentos.entidades.Medicamentos;
import com.terobyte.appmedicamentos.entidades.Usuarios;

import java.util.List;

public class UsuarioRepository {
    private IUsuariosDao iUsuariosDao;
    private LiveData<List<Usuarios>> usuarios;

    public UsuarioRepository(Application application) {
        AppDataBase db= AppDataBase.getInstance(application);
        iUsuariosDao= db.iUsuariosDao();
        usuarios= iUsuariosDao.VerUsuarios();
    }
    public LiveData<List<Usuarios>> VerUsuarios(){
        return usuarios;
    }

    public void agregarUsuario(Usuarios usuario){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            iUsuariosDao.AgregarUsuario(usuario);
        });
    }
}
