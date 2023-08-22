/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.xspin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ArticulosDao {
    private Connection con = null;
    
    public ArticulosDao(Connection con){
        this.con = con;
    }
    
    public void insertarProductos(Articulo articulo){
            String consulta = "INSERT INTO " + CrearTablas.NOMBRE_TABLA_PRODUCTOS + " (sku, nombre, cantidad, precio, proveedor) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(consulta);
            pstmt.setString(1, articulo.getSku());
            pstmt.setString(2, articulo.getNombre());
            pstmt.setInt(3, articulo.getCantidad());
            pstmt.setDouble(4, articulo.getPrecio());
            pstmt.setString(5, articulo.getProveedor());

            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Producto aceptado");
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public LinkedList<Articulo> buscarProductosDefault(){
        LinkedList<Articulo> listaArticulos = new LinkedList<>();
       // String consulta = "SELECT TOP 10 * FROM dbo.Productos ORDER BY nombre";
       String consulta = "SELECT * FROM " + CrearTablas.NOMBRE_TABLA_PRODUCTOS + " ORDER BY nombre";
        try {
            PreparedStatement pstmt = con.prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                 Articulo articulo = new Articulo();
                 articulo.setSku(rs.getString("sku"));
                 articulo.setNombre(rs.getString("nombre"));
                 articulo.setCantidad(rs.getInt("cantidad"));
                 articulo.setPrecio(rs.getDouble("precio"));
                 articulo.setProveedor(rs.getString("proveedor"));
                 
                 listaArticulos.add(articulo);
            }
            pstmt.close();
            return listaArticulos;
            
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public LinkedList<Articulo> buscarProductoSku(String sku){
        //Si pasa 3 digitos tambien mostrar el resto de codigo de barras que coinciden
        LinkedList<Articulo> listaArticulos = new LinkedList<>();
        String consulta = "SELECT * FROM " + CrearTablas.NOMBRE_TABLA_PRODUCTOS + " WHERE LEFT(sku, " + sku.length() + ") = '" + sku + "'";
        
        
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                 Articulo articulo = new Articulo();
                 articulo.setSku(rs.getString("sku"));
                 articulo.setNombre(rs.getString("nombre"));
                 articulo.setCantidad(rs.getInt("cantidad"));
                 articulo.setPrecio(rs.getDouble("precio"));
                 articulo.setProveedor(rs.getString("proveedor"));
                 listaArticulos.add(articulo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaArticulos;
    }
    public void borrarProducto(String sku){
        String consulta = "DELETE FROM " + CrearTablas.NOMBRE_TABLA_PRODUCTOS + " WHERE SKU = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(consulta);
            pstmt.setString(1, sku);
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
    }
    public void modificarProducto(Articulo articulo){
        System.out.println(articulo.getProveedor());
         String consulta = "UPDATE " + CrearTablas.NOMBRE_TABLA_PRODUCTOS + " SET nombre = ?, cantidad = ?, precio = ?, proveedor = ? WHERE SKU = ?";
         PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(consulta);
            pstmt.setString(1,articulo.getNombre());
            pstmt.setString(2,String.valueOf(articulo.getCantidad()));
            pstmt.setString(3, String.valueOf(articulo.getPrecio()));
            pstmt.setString(4, articulo.getProveedor());
            pstmt.setString(5, articulo.getSku());
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Producto modificado");
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    public boolean existeSKU(String sku){
        String consulta = "SELECT SKU FROM " + CrearTablas.NOMBRE_TABLA_PRODUCTOS + " WHERE SKU = ?";
        boolean respuesta = false;
        try {
            PreparedStatement pstmt = con.prepareStatement(consulta);
            pstmt.setString(1, sku);
            
            
            ResultSet resultSet = pstmt.executeQuery();
        
        if (resultSet.next()) {
            respuesta = true; // SKU encontrado
        }
                
        pstmt.close();
        return respuesta;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    
    }
    
    
}
