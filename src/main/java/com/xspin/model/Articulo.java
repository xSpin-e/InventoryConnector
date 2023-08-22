/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.xspin.model;

/**
 *
 * @author Usuario
 */


public class Articulo {
        private String sku;
        private String nombre;
        private int cantidad;
        private double precio;
        private String proveedor;
        
    
        public Articulo(){
            this.sku = "";
            this.nombre = "";
            this.cantidad = 0;
            this.precio = 0.0;
            this.proveedor = "";
        }

    public String getSku() {
        return sku;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "Articulos{" + "sku=" + sku + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio + ", proveedor=" + proveedor + '}';
    }
    
}
