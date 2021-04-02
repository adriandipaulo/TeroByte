package com.terobyte.appmedicamentos.database;

import android.content.Context;

import androidx.core.util.Pools;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.terobyte.appmedicamentos.dao.IMedicamentosDao;
import com.terobyte.appmedicamentos.dao.IUsuariosDao;
import com.terobyte.appmedicamentos.dao.Mi_cel_dao;
import com.terobyte.appmedicamentos.entidades.Medicamentos;
import com.terobyte.appmedicamentos.entidades.Mi_cel;
import com.terobyte.appmedicamentos.entidades.Usuarios;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Mi_cel.class, Usuarios.class, Medicamentos.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract Mi_cel_dao mi_cel_dao();
    public abstract IUsuariosDao iUsuariosDao();
    public abstract IMedicamentosDao iMedicamentosDao();

    private static volatile AppDataBase instance;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static AppDataBase getInstance(final Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"MedicamentosApp").build();
            }
        return  instance;
    }
}
