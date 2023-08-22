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
 * @author adrian
 */
public class ProveedorDao {
    private Connection con;
    
    public ProveedorDao(Connection con){
        this.con = con;
    }
    public void subirProveedor(Proveedor proveedor){
    String consulta = "INSERT INTO " + CrearTablas.NOMBRE_TABLA_PROVEEDORES + " (nombre) VALUES (?)";
                PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(consulta);
            pstmt.setString(1, proveedor.getNombre());
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Proveedor subido con éxito");
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public LinkedList<Proveedor> obtenerProveedores(){
        String consulta = "SELECT * FROM " + CrearTablas.NOMBRE_TABLA_PROVEEDORES + " ORDER BY nombre";
        LinkedList<Proveedor> listaProveedores = new LinkedList<>();
            PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
                 
                 Proveedor proveedor = new Proveedor();
                 proveedor.setNombre(rs.getString("nombre"));
                 
                 listaProveedores.add(proveedor);
            }
            return listaProveedores;
        }catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
            
    }
        public void borrarProducto(String nombre){
        String consulta = "DELETE FROM " + CrearTablas.NOMBRE_TABLA_PROVEEDORES + " WHERE nombre = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(consulta);
            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
    }
}

