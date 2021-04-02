package com.terobyte.appmedicamentos.entidades;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Mi_cel {

    @PrimaryKey
    private int id;
    private long  hora_ap;
    private long hora_ini;


    public long getHora_ap() {
        return hora_ap;
    }

    public void setHora_ap(long hora_ap) {
        this.hora_ap = hora_ap;
    }

    public long getHora_ini() {
        return hora_ini;
    }

    public void setHora_ini(long hora_ini) {
        this.hora_ini = hora_ini;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
