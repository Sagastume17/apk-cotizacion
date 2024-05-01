package com.example.camaras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections; // Importa esta clase para utilizar el método reverse() en la lista
import java.util.stream.Collectors;

public class ListaMostrarAdapter extends RecyclerView.Adapter<ListaMostrarAdapter.MostrarViewHolder> {
    ArrayList<Mostrar>listaMostrar;

    public ListaMostrarAdapter(ArrayList<Mostrar> listaMostrar){
        this.listaMostrar = listaMostrar.stream()
                .filter(mostrar -> mostrar.getEstado() == 1)
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.reverse(this.listaMostrar);
    }

    public void updateList(ArrayList<Mostrar> nuevaLista) {
        this.listaMostrar = nuevaLista.stream()
                .filter(mostrar -> mostrar.getEstado() == 1)
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.reverse(this.listaMostrar);
        notifyDataSetChanged();  // Notifica al adaptador que los datos han cambiado
    }

    @NonNull
    @Override
    public MostrarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_mostrar,null,false);
        return new MostrarViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MostrarViewHolder holder, int position) {
        Mostrar item = listaMostrar.get(position);
        if (item.getCliente() != null) {
            holder.view_cliente.setText(item.getCliente());
        }
        if (item.getTelefono() != null) {
            holder.view_telefono.setText(item.getTelefono());
        }
        if (item.getFecha() != null) {
            holder.view_fecha.setText(item.getFecha());
        }
        if (item.getDireccion() != null) {
            holder.view_direccion.setText(item.getDireccion());
        }
        if (item.getDescripcion() != null) {
            holder.view_descripcion.setText(item.getDescripcion());
        }
        if (item.getTotal() != null) {
            holder.view_total.setText(item.getTotal());
        }
    }



// Repetir para otros campos...

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
