package com.terobyte.appmedicamentos.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.terobyte.appmedicamentos.entidades.Medicamentos;
import java.util.List;

@Dao
public interface IMedicamentosDao {

    @Query("Select * from Medicamentos")
    LiveData<List<Medicamentos>> VerMedicamentos();

    @Insert
    void AgregarMedicamento(Medicamentos medicamentos);

    @Update
    void EditMedicamento(Medicamentos medicamentos);

    @Delete
    void EliminarMedicamento(Medicamentos medicamentos);
}
