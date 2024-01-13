package com.example.contenedores2024_is2023_2.Modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LugarTuristico {
    String Nombre, Direccion, Telefono, UrlLogo;

    public LugarTuristico(JSONObject a) throws JSONException {
        Nombre =  a.getString("nombre_lugar").toString() ;

        Direccion =  a.getString("direccion").toString() ;
        Telefono =  a.getString("telefono").toString() ;
        UrlLogo = "https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/" + a.getString("logo").toString() ;

    }

    public static ArrayList<LugarTuristico> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<LugarTuristico> listalugares = new ArrayList<>();

        for (int i = 0; i < datos.length(); i++) {
            listalugares.add(new LugarTuristico(datos.getJSONObject(i)));
        }
        return listalugares;
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getUrlLOgo() {
        return UrlLogo;
    }

    public void setUrlLOgo(String urlLOgo) {
        UrlLogo = urlLOgo;
    }
}
