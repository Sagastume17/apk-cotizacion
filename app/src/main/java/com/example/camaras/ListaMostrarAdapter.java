package com.example.camaras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections; // Importa esta clase para utilizar el método reverse() en la lista


public class ListaMostrarAdapter extends RecyclerView.Adapter<ListaMostrarAdapter.MostrarViewHolder> {
    ArrayList<Mostrar>listaMostrar;

    public ListaMostrarAdapter(ArrayList<Mostrar>listaMostrar){
        this.listaMostrar = listaMostrar;
    }

    @NonNull
    @Override
    public MostrarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_mostrar,null,false);
        return new MostrarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MostrarViewHolder holder, int position) {
        holder.view_cliente.setText(listaMostrar.get(position).getCliente());
        holder.view_telefono.setText(listaMostrar.get(position).getTelefono());
        holder.view_fecha.setText(listaMostrar.get(position).getFecha());
        holder.view_direccion.setText(listaMostrar.get(position).getDireccion());
        holder.view_descripcion.setText(listaMostrar.get(position).getDescripcion());
        holder.view_total.setText(listaMostrar.get(position).getTotal());


    }

    @Override
    public int getItemCount() {

        return listaMostrar.size();
    }

    public class MostrarViewHolder extends RecyclerView.ViewHolder {
        TextView view_cliente,view_telefono,view_fecha,view_direccion,view_descripcion,view_total;
        public MostrarViewHolder(@NonNull View itemView) {
            super(itemView);

            view_cliente =  itemView.findViewById(R.id.view_cliente);
            view_telefono = itemView.findViewById(R.id.view_telefono);
            view_fecha = itemView.findViewById(R.id.view_fecha);
            view_direccion = itemView.findViewById(R.id.view_direccion);
            view_descripcion = itemView.findViewById(R.id.view_descripcion);
            view_total = itemView.findViewById(R.id.view_total);


        }
    }
}
