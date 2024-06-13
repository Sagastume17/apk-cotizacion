package com.example.camaras;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class ListaMostrarAdapter extends RecyclerView.Adapter<ListaMostrarAdapter.MostrarViewHolder> {
    ArrayList<Mostrar> listaMostrar;
    private ArrayList<Mostrar> listaCompleta; // Lista original completa
    private Helper dbHelper;

    // Constructor
    public ListaMostrarAdapter(ArrayList<Mostrar> listaMostrar, Helper dbHelper) {
        this.listaMostrar = listaMostrar.stream()
                .filter(mostrar -> mostrar.getEstado() == 1)
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.reverse(this.listaMostrar);
        this.dbHelper = dbHelper;
        this.listaCompleta = new ArrayList<>(listaMostrar);
    }

    // Método para actualizar la lista
    public void updateList(ArrayList<Mostrar> nuevaLista) {
        this.listaMostrar = nuevaLista.stream()
                .filter(mostrar -> mostrar.getEstado() == 1)
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.reverse(this.listaMostrar);
        notifyDataSetChanged();
    }

    // ViewHolder
    @NonNull
    @Override
    public MostrarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_mostrar, null, false);
        return new MostrarViewHolder(view);
    }

    // Binding data
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

    @Override
    public int getItemCount() {
        return listaMostrar.size();
    }

    // ViewHolder class definition
    public class MostrarViewHolder extends RecyclerView.ViewHolder {
        TextView view_cliente, view_telefono, view_fecha, view_direccion, view_descripcion, view_total;
        Button btn_eliminar;

        public MostrarViewHolder(@NonNull View itemView) {
            super(itemView);
            view_cliente = itemView.findViewById(R.id.view_cliente);
            view_telefono = itemView.findViewById(R.id.view_telefono);
            view_fecha = itemView.findViewById(R.id.view_fecha);
            view_direccion = itemView.findViewById(R.id.view_direccion);
            view_descripcion = itemView.findViewById(R.id.view_descripcion);
            view_total = itemView.findViewById(R.id.view_total);
            btn_eliminar = itemView.findViewById(R.id.btn_eliminar);

            btn_eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        new AlertDialog.Builder(itemView.getContext())
                                .setTitle("Confirmar eliminación")
                                .setMessage("¿Estás seguro de que deseas eliminar este elemento?")
                                .setPositiveButton("Eliminar", (dialog, which) -> {
                                    Mostrar mostrar = listaMostrar.get(position);
                                    if (dbHelper != null) {
                                        dbHelper.eliminarCotizacion(mostrar.getId());
                                        listaMostrar.remove(position);
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

    // Método de filtrado añadido correctamente en el cuerpo de la clase, fuera de cualquier otro método
    public void filtrarPorCliente(String cliente) {
        if (cliente.isEmpty()) {
            listaMostrar = new ArrayList<>(listaCompleta); // Usa la lista original completa
        } else {
            listaMostrar = listaCompleta.stream()
                    .filter(mostrar -> mostrar.getCliente() != null &&
                            mostrar.getCliente().toLowerCase().contains(cliente.toLowerCase()) &&
                            mostrar.getEstado() == 1) // Filtra por cliente y estado igual a 1
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
    }


}
