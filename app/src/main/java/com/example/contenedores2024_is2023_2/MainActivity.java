package com.example.contenedores2024_is2023_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.contenedores2024_is2023_2.Adapters.LugarTuristicoAdapter;
import com.example.contenedores2024_is2023_2.Listeners.FillCbListener;
import com.example.contenedores2024_is2023_2.Modelos.ItemCB;
import com.example.contenedores2024_is2023_2.Modelos.LugarTuristico;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity
        extends AppCompatActivity
        implements Asynchtask, AdapterView.OnItemSelectedListener{

    Spinner cbCategoria, cbSUbCategoria;
    RecyclerView rvListaLugares;
    Map<String, String> datosWS = new HashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbCategoria = findViewById(R.id.cbCategoria);
        cbSUbCategoria = findViewById(R.id.cbSubCategoria);
        rvListaLugares = findViewById(R.id.rvLista);
        cbCategoria.setOnItemSelectedListener(this);
        cbSUbCategoria.setOnItemSelectedListener(this);


        WebService ws= new WebService(
                "https://uealecpeterson.net/turismo/categoria/getlistadoCB",
                datosWS, MainActivity.this,
                new FillCbListener(cbCategoria, "id", "descripcion", true) );
        ws.execute("GET");

        rvListaLugares.setHasFixedSize(true);
        rvListaLugares.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int IDCat=0, IDSubCat=0;
        int IDItemSeleccionado = ((ItemCB)parent.getItemAtPosition(position)).getID();
        if(parent == cbCategoria) {
            if (IDItemSeleccionado>0){
                IDCat = IDItemSeleccionado;
                WebService ws2= new WebService(
                        "https://uealecpeterson.net/turismo/subcategoria/getlistadoCB/" + IDCat,
                        datosWS, MainActivity.this,
                        new FillCbListener(cbSUbCategoria, "id", "descripcion", true) );
                ws2.execute("GET");
            }

        }else if (parent == cbSUbCategoria){
            if (cbCategoria.getSelectedItemPosition()!=AdapterView.INVALID_POSITION){
                IDCat = ((ItemCB)cbCategoria.getSelectedItem()).getID();
                IDSubCat = IDItemSeleccionado;
            }
        }

        WebService ws3 = new WebService(
                "https://uealecpeterson.net/turismo/lugar_turistico/json_getlistadoGridLT/" +
                        IDCat + "/" + IDSubCat,
                datosWS, MainActivity.this, MainActivity.this);
        ws3.execute("GET");


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<LugarTuristico> lstLugares;

        JSONObject JSONlista =  new JSONObject(result);
        JSONArray JSONlistaLugares=  JSONlista.getJSONArray("data");
        lstLugares = LugarTuristico.JsonObjectsBuild(JSONlistaLugares);

        LugarTuristicoAdapter adaptadorLugaresT = new LugarTuristicoAdapter(this, lstLugares);
        rvListaLugares.setAdapter(adaptadorLugaresT);

    }
}