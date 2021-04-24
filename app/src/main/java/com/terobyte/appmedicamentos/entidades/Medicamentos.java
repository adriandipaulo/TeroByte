package com.terobyte.appmedicamentos.entidades;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
///foreignKeys = @ForeignKey(entity = Usuarios.class,parentColumns = "id",childColumns = "id_medicamento",onDelete = ForeignKey.CASCADE)

@Entity
public class Medicamentos {
    @PrimaryKey(autoGenerate = true)
    private int id_medicamento;
    private String nombreMedicamento;
    private int tomar_cada;
    private String presentacion;
    private String usuario;
    private String horaMinuto;

    public void setId_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public int getTomar_cada() {
        return tomar_cada;
    }

    public void setTomar_cada(int tomar_cada) {
        this.tomar_cada = tomar_cada;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getId_medicamento() {
        return id_medicamento;
    }

    public String getHoraMinuto() {
        return horaMinuto;
    }

    public void setHoraMinuto(String horaMinuto) {
        this.horaMinuto = horaMinuto;
    }
}
