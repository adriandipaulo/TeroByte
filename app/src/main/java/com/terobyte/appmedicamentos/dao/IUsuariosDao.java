package com.terobyte.appmedicamentos.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.terobyte.appmedicamentos.entidades.Usuarios;

import java.util.List;

@Dao
public interface IUsuariosDao {

    @Query("select * from Usuarios")
    LiveData<List<Usuarios>> VerUsuarios();

    @Insert
    void AgregarUsuario(Usuarios usuarios);

    @Update
    void EditUsuario(Usuarios usuarios);

    @Delete
    void EliminarUsuario(Usuarios usuarios);
}
