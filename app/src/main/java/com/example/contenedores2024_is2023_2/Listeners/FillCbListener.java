package com.example.contenedores2024_is2023_2.Listeners;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.example.contenedores2024_is2023_2.Modelos.ItemCB;
import com.example.contenedores2024_is2023_2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import WebServices.Asynchtask;

public class FillCbListener implements Asynchtask {

    AutoCompleteTextView cb;
    String campoID, campoDesc;
    Boolean addAllItem;
    public FillCbListener(AutoCompleteTextView cb, String campoID, String campoDesc, Boolean addAllItem) {
        this.cb = cb;
        this.campoID = campoID;
        this.campoDesc = campoDesc;
        this.addAllItem = addAllItem;
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<ItemCB> datos = new ArrayList<ItemCB>();
        if(addAllItem) datos.add( new ItemCB(0, "Seleccione una opción"));
        JSONArray JSONlista =  new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject lugar=  JSONlista.getJSONObject(i);
            datos.add( new ItemCB(lugar.getInt(campoID),
                    lugar.getString(campoDesc)));
        }

        ArrayAdapter<ItemCB> adaptador =
                new ArrayAdapter<ItemCB>(cb.getContext(),
                        android.R.layout.simple_dropdown_item_1line, datos);
        cb.setAdapter(adaptador);
    }
}
