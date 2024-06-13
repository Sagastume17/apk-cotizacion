package com.example.camaras;

public class Mostrar {


    private int id;
    private String cliente;

    private String telefono;
    private String fecha;
    private String direccion;
    private String descripcion;
    private String total;
    private int estado;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {


        return "CLIENTE :"+ cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTelefono() {
        return "TELEFONO = "+telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return "FECHA = "+fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return "DIRECCION = "+ direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return "DESCRIOCION = "+descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTotal() {

        return "TOTAL ="+ total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
