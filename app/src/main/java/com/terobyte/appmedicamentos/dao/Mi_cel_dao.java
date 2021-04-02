package com.terobyte.appmedicamentos.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.terobyte.appmedicamentos.entidades.Mi_cel;

import java.util.List;

@Dao
public interface Mi_cel_dao {

    @Query("Select * from Mi_cel")
    List<Mi_cel> getAll();

    @Insert
    void AgregarH(Mi_cel mi_cel);

    @Query("Select * from Mi_cel where id= :id")
    Mi_cel BuscaCel(int id);
}
