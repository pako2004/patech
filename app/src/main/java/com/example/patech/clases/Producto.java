package com.example.patech.clases;

import java.io.Serializable;

public class Producto implements Serializable {

    private  int idProducto;
    private  double precio;
    private String descripcion;
    private int stock;
    private String imagen_url;
    private String nombre;
    private double valoracion;
    public Producto(){}

    public String getNombre() {
        return nombre;
    }
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagenUrl() {
        return imagen_url;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagen_url = imagenUrl;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }
}
