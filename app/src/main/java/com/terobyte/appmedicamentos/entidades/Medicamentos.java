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
    private double tomar_cada;
    private String presentacion;
    private String usuario;

    public void setId_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public double getTomar_cada() {
        return tomar_cada;
    }

    public void setTomar_cada(double tomar_cada) {
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
}
