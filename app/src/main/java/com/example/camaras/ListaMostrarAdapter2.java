package com.example.camaras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import android.widget.Button;

public class ListaMostrarAdapter2 extends RecyclerView.Adapter<ListaMostrarAdapter2.MostrarViewHolder> {
    ArrayList<Mostrar> listaMostrar2;


    public ListaMostrarAdapter2(ArrayList<Mostrar> listaMostrar2) {
        this.listaMostrar2 = new ArrayList<>();
        for (Mostrar mostrar : listaMostrar2) {
            if (mostrar.getEstado() == 0) {
                this.listaMostrar2.add(mostrar);
            }
        }
        Collections.reverse(this.listaMostrar2);  // Opcional: invierte el orden si es necesario
    }


    @NonNull
    @Override
    public MostrarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_mostrar2, parent, false);
        return new MostrarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MostrarViewHolder holder, int position) {
        holder.view_cliente.setText(listaMostrar2.get(position).getCliente());
        holder.view_telefono.setText(listaMostrar2.get(position).getTelefono());
        holder.view_fecha.setText(listaMostrar2.get(position).getFecha());
        holder.view_direccion.setText(listaMostrar2.get(position).getDireccion());
        holder.view_descripcion.setText(listaMostrar2.get(position).getDescripcion());
        holder.view_total.setText(listaMostrar2.get(position).getTotal());
    }

    @Override
    public int getItemCount() {
        return listaMostrar2.size();
    }

    public class MostrarViewHolder extends RecyclerView.ViewHolder {
        TextView view_cliente, view_telefono, view_fecha, view_direccion, view_descripcion, view_total;
        Button btn_confirmar;

        public MostrarViewHolder(@NonNull View itemView) {
            super(itemView);

            view_cliente = itemView.findViewById(R.id.view_cliente);
            view_telefono = itemView.findViewById(R.id.view_telefono);
            view_fecha = itemView.findViewById(R.id.view_fecha);
            view_direccion = itemView.findViewById(R.id.view_direccion);
            view_descripcion = itemView.findViewById(R.id.view_descripcion);
            view_total = itemView.findViewById(R.id.view_total);
            btn_confirmar = itemView.findViewById(R.id.btn_confirmar);

            btn_confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Mostrar mostrar = listaMostrar2.get(position);
                        if (mostrar.getEstado() == 0) {
                            Helper dbHelper = new Helper(v.getContext());
                            dbHelper.actualizarEstado(mostrar.getId(), 1);
                            listaMostrar2.remove(position);
                            notifyItemRemoved(position);

                        }
                    }
                }
            });


        }
    }
}
