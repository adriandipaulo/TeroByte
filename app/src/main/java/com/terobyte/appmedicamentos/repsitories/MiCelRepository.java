package com.terobyte.appmedicamentos.repsitories;

import android.app.Application;
import com.terobyte.appmedicamentos.dao.Mi_cel_dao;
import com.terobyte.appmedicamentos.database.AppDataBase;
import com.terobyte.appmedicamentos.entidades.Mi_cel;

import java.util.List;

public class MiCelRepository {
    private Mi_cel_dao mi_cel_dao;

    public MiCelRepository(Application application){
        AppDataBase db= AppDataBase.getInstance(application);
        mi_cel_dao = db.mi_cel_dao();
    }
    public List<Mi_cel> getMICEL(){
        return mi_cel_dao.getAll();
    }
    public void insertMicel(Mi_cel mi_cel){
        mi_cel_dao.AgregarH(mi_cel);
    }
    public Mi_cel buscaCel(int id){
        return mi_cel_dao.BuscaCel(id);
    }

}

