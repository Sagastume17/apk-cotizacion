package com.example.camaras;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
<<<<<<< HEAD
import android.widget.Button;
import android.widget.Toast;

=======
>>>>>>> 01098a21fae09ba0befe41ec547def696cdd4dea
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
<<<<<<< HEAD
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
=======
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
>>>>>>> 01098a21fae09ba0befe41ec547def696cdd4dea
    }


    @NonNull
    @Override
    public MostrarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_mostrar2, parent, false);
        return new MostrarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MostrarViewHolder holder, int position) {
<<<<<<< HEAD
        Mostrar mostrar = listaMostrar2.get(position);
        holder.view_cliente.setText(mostrar.getCliente());
        holder.view_telefono.setText(mostrar.getTelefono());
        holder.view_fecha.setText(mostrar.getFecha());
        holder.view_direccion.setText(mostrar.getDireccion());
        holder.view_descripcion.setText(mostrar.getDescripcion());
        holder.view_total.setText(mostrar.getTotal());
=======
        holder.view_cliente.setText(listaMostrar2.get(position).getCliente());
        holder.view_telefono.setText(listaMostrar2.get(position).getTelefono());
        holder.view_fecha.setText(listaMostrar2.get(position).getFecha());
        holder.view_direccion.setText(listaMostrar2.get(position).getDireccion());
        holder.view_descripcion.setText(listaMostrar2.get(position).getDescripcion());
        holder.view_total.setText(listaMostrar2.get(position).getTotal());
>>>>>>> 01098a21fae09ba0befe41ec547def696cdd4dea
    }


    @Override
    public int getItemCount() {
        return listaMostrar2.size();
    }

    public class MostrarViewHolder extends RecyclerView.ViewHolder {
        TextView view_cliente, view_telefono, view_fecha, view_direccion, view_descripcion, view_total;
<<<<<<< HEAD
        Button btn_confirmar, btn_eliminar;
=======
        Button btn_confirmar;
>>>>>>> 01098a21fae09ba0befe41ec547def696cdd4dea

        public MostrarViewHolder(@NonNull View itemView) {
            super(itemView);

            view_cliente = itemView.findViewById(R.id.view_cliente);
            view_telefono = itemView.findViewById(R.id.view_telefono);
            view_fecha = itemView.findViewById(R.id.view_fecha);
            view_direccion = itemView.findViewById(R.id.view_direccion);
            view_descripcion = itemView.findViewById(R.id.view_descripcion);
            view_total = itemView.findViewById(R.id.view_total);
            btn_confirmar = itemView.findViewById(R.id.btn_confirmar);
<<<<<<< HEAD
            btn_eliminar = itemView.findViewById(R.id.btn_eliminar);
=======

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
>>>>>>> 01098a21fae09ba0befe41ec547def696cdd4dea

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
