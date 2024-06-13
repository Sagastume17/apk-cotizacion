package com.example.camaras;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class ListaMostrarAdapter2 extends RecyclerView.Adapter<ListaMostrarAdapter2.MostrarViewHolder> {
    private ArrayList<Mostrar> listaMostrar2;
    private Helper dbHelper;

    private ArrayList<Mostrar> listaCompleta2;

    public ListaMostrarAdapter2(ArrayList<Mostrar> listaMostrar, Helper dbHelper) {
        this.listaMostrar2 = listaMostrar.stream()
                .filter(mostrar -> mostrar.getEstado() == 0)
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.reverse(this.listaMostrar2);
        this.dbHelper = dbHelper;
        this.listaCompleta2 = new ArrayList<>(listaMostrar);
    }

    @NonNull
    @Override
    public MostrarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_mostrar2, parent, false);
        return new MostrarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MostrarViewHolder holder, int position) {
        Mostrar mostrar = listaMostrar2.get(position);
        holder.view_cliente.setText(mostrar.getCliente());
        holder.view_telefono.setText(mostrar.getTelefono());
        holder.view_fecha.setText(mostrar.getFecha());
        holder.view_direccion.setText(mostrar.getDireccion());
        holder.view_descripcion.setText(mostrar.getDescripcion());
        holder.view_total.setText(mostrar.getTotal());
    }


    @Override
    public int getItemCount() {
        return listaMostrar2.size();
    }

    public class MostrarViewHolder extends RecyclerView.ViewHolder {
        TextView view_cliente, view_telefono, view_fecha, view_direccion, view_descripcion, view_total;
        Button btn_confirmar, btn_eliminar;

        public MostrarViewHolder(@NonNull View itemView) {
            super(itemView);

            view_cliente = itemView.findViewById(R.id.view_cliente);
            view_telefono = itemView.findViewById(R.id.view_telefono);
            view_fecha = itemView.findViewById(R.id.view_fecha);
            view_direccion = itemView.findViewById(R.id.view_direccion);
            view_descripcion = itemView.findViewById(R.id.view_descripcion);
            view_total = itemView.findViewById(R.id.view_total);
            btn_confirmar = itemView.findViewById(R.id.btn_confirmar);
            btn_eliminar = itemView.findViewById(R.id.btn_eliminar);

            btn_confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Mostrar mostrar = listaMostrar2.get(position);
                        if (mostrar.getEstado() == 0) {
                            dbHelper.actualizarEstado(mostrar.getId(), 1);
                            // Remover el elemento de la lista
                            listaMostrar2.remove(position);
                            // Notificar al adaptador sobre el cambio en los datos
                            notifyItemRemoved(position);
                            // Notificar al RecyclerView que los datos han cambiado
                            notifyDataSetChanged();
                        }
                    }
                }
            });

            btn_eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Confirmar antes de eliminar
                        new AlertDialog.Builder(itemView.getContext())
                                .setTitle("Confirmar eliminación")
                                .setMessage("¿Estás seguro de que deseas eliminar este elemento?")
                                .setPositiveButton("Eliminar", (dialog, which) -> {
                                    Mostrar mostrar = listaMostrar2.get(position);
                                    if (dbHelper != null) {
                                        dbHelper.eliminarCotizacion(mostrar.getId());
                                        listaMostrar2.remove(position);
                                        notifyItemRemoved(position);
                                    } else {
                                        Toast.makeText(itemView.getContext(), "Error: dbHelper no inicializado", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Cancelar", null)
                                .show();
                    }
                }
            });
        }
    }
    public void filtrarPorCliente(String cliente) {
        if (cliente.isEmpty()) {
            listaMostrar2 = new ArrayList<>(listaCompleta2); // Usa la lista original completa
        } else {
            listaMostrar2 = listaCompleta2.stream()
                    .filter(mostrar -> mostrar.getCliente() != null &&
                            mostrar.getCliente().toLowerCase().contains(cliente.toLowerCase()) &&
                            mostrar.getEstado() == 0) // Filtra por cliente y estado igual a 1
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
    }
}
