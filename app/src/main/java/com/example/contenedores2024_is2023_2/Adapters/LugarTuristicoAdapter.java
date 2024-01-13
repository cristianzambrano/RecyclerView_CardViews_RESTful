package com.example.contenedores2024_is2023_2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.contenedores2024_is2023_2.Modelos.LugarTuristico;
import com.example.contenedores2024_is2023_2.R;

import java.util.List;


public class LugarTuristicoAdapter extends RecyclerView.Adapter<LugarTuristicoAdapter.LugarTuristicoViewHolder> {

    private Context Ctx;
    private List<LugarTuristico> lstLugares;

    public LugarTuristicoAdapter(Context mCtx, List<LugarTuristico> lstLugaresT) {
        this.lstLugares = lstLugaresT;
        Ctx = mCtx;
    }

    @Override
    public LugarTuristicoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.lyitem, null);
        return new LugarTuristicoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LugarTuristicoViewHolder holder, int position) {

        LugarTuristico Lugar = lstLugares.get(position);

        holder.textViewName.setText(Lugar.getNombre());
        holder.textViewDireccion.setText(Lugar.getDireccion());
        holder.textViewTelefono.setText(Lugar.getTelefono());

        Glide.with(Ctx)
                .load(Lugar.getUrlLOgo())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return lstLugares.size();
    }

    class LugarTuristicoViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewDireccion, textViewTelefono;
        ImageView imageView;

        public LugarTuristicoViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.txtName);
            textViewDireccion = itemView.findViewById(R.id.txtDireccion);
            textViewTelefono = itemView.findViewById(R.id.txtTelefono);
            imageView = itemView.findViewById(R.id.imgLogo);
        }
    }
}

