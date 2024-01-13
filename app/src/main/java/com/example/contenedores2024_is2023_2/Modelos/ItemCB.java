package com.example.contenedores2024_is2023_2.Modelos;

import androidx.annotation.NonNull;

public class ItemCB {

    int ID;
    String Descripcion;

    @NonNull
    @Override
    public String toString() {       return Descripcion;    }

    public ItemCB(int ID, String descripcion) {
        this.ID = ID;
        Descripcion = descripcion;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }



}
