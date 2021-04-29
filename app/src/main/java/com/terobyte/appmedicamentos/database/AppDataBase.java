package com.terobyte.appmedicamentos.database;

import android.content.Context;

import androidx.core.util.Pools;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.terobyte.appmedicamentos.dao.IMedicamentosDao;
import com.terobyte.appmedicamentos.dao.IUsuariosDao;
import com.terobyte.appmedicamentos.entidades.Medicamentos;
import com.terobyte.appmedicamentos.entidades.Usuarios;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Usuarios.class, Medicamentos.class},version = 7,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract IUsuariosDao iUsuariosDao();
    public abstract IMedicamentosDao iMedicamentosDao();

    private static volatile AppDataBase instance;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static AppDataBase getInstance(final Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"MedicamentosApp")
                    .fallbackToDestructiveMigration().build();
            }
        return  instance;
    }
}
