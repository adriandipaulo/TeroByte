package com.terobyte.appmedicamentos;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.terobyte.appmedicamentos.dao.IMedicamentosDao;
import com.terobyte.appmedicamentos.dao.IUsuariosDao;
import com.terobyte.appmedicamentos.database.AppDataBase;
import com.terobyte.appmedicamentos.entidades.Medicamentos;
import com.terobyte.appmedicamentos.entidades.Usuarios;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class Mtest {
    private Mi_cel_dao mi_cel_dao;
    private IMedicamentosDao iMedicamentosDao;
    private IUsuariosDao iUsuariosDao;
    private AppDataBase appDataBase;

    @Before
    public void createDB(){
        Context context = ApplicationProvider.getApplicationContext();
        appDataBase = Room.inMemoryDatabaseBuilder(context,AppDataBase.class).build();
        mi_cel_dao = appDataBase.mi_cel_dao();
        iMedicamentosDao=appDataBase.iMedicamentosDao();
        iUsuariosDao=appDataBase.iUsuariosDao();

    }
    @After
    public void closeDB() throws IOException{
        appDataBase.close();
    }

    @Test
    public void creoCel() throws Exception{
        Mi_cel mi_cel = new Mi_cel();
        mi_cel.setId(1);
        mi_cel.setHora_ini("2");
        mi_cel.setHora_ap("4");
        mi_cel_dao.AgregarH(mi_cel);
        Mi_cel x=mi_cel_dao.BuscaCel(1);
        Mi_cel j=mi_cel_dao.BuscaPorDatos("4","2");
        assertTrue("Error No encontrado",mi_cel.getId()==j.getId());

        Usuarios usuarios= new Usuarios();
        usuarios.setAltura(1.78);
        usuarios.setNombreApellido("Jose lopez");
        usuarios.setPeso(68);
        usuarios.setPresion("14 / 12");
        iUsuariosDao.AgregarUsuario(usuarios);

        Medicamentos medicamentos=new Medicamentos();
        medicamentos.setNombreMedicamento("Aspirina");
        medicamentos.setPresentacion("ml");
        medicamentos.setTomar_cada(8);
        medicamentos.setUsuario(usuarios.getNombreApellido());
    }
}