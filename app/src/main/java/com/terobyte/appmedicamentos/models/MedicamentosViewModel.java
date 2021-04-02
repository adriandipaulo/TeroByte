package com.terobyte.appmedicamentos.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.terobyte.appmedicamentos.entidades.Medicamentos;
import com.terobyte.appmedicamentos.repsitories.MedicamentoRepository;

import java.util.List;

public class MedicamentosViewModel extends AndroidViewModel {
    private MedicamentoRepository medicamentoRepository;
    private final LiveData<List<Medicamentos>> medicamentos;

    public MedicamentosViewModel (Application application){
        super(application);
        medicamentoRepository= new MedicamentoRepository(application);
        medicamentos= medicamentoRepository.VerMedicamentos();
    }

    public LiveData<List<Medicamentos>> verMedicamentos(){
        return medicamentos;
    }

    public void insert (Medicamentos medicamento){
        medicamentoRepository.agregarMedicamento(medicamento);
    }

    public void delete(Medicamentos medicamento){
        medicamentoRepository.eliminaMedicamento(medicamento);
    }

    public void update(Medicamentos medicamento){
        medicamentoRepository.editaMedicamento(medicamento);
    }

}
