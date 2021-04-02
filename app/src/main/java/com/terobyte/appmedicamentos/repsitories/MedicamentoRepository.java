package com.terobyte.appmedicamentos.repsitories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.terobyte.appmedicamentos.dao.IMedicamentosDao;
import com.terobyte.appmedicamentos.database.AppDataBase;
import com.terobyte.appmedicamentos.entidades.Medicamentos;

import java.util.List;

public class MedicamentoRepository {
    private IMedicamentosDao iMedicamentosDao;
    private LiveData<List<Medicamentos>> medicamentos;

    public MedicamentoRepository(Application application) {
        AppDataBase db= AppDataBase.getInstance(application);
        iMedicamentosDao=db.iMedicamentosDao();
        medicamentos=iMedicamentosDao.VerMedicamentos();
    }
    public LiveData<List<Medicamentos>> VerMedicamentos(){
        return medicamentos;
    }

    public void agregarMedicamento(Medicamentos medicamento){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            iMedicamentosDao.AgregarMedicamento(medicamento);
        });
    }

    public void eliminaMedicamento(Medicamentos medicamento){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            iMedicamentosDao.EliminarMedicamento(medicamento);
        });
    }
    public void editaMedicamento(Medicamentos medicamento){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            iMedicamentosDao.EditMedicamento(medicamento);
        });
    }
}
