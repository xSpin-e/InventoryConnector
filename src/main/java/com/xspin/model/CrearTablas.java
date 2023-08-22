/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.xspin.model;

import com.xspin.main.Proyect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adrian
 */
public class CrearTablas {
    private Connection con;
    
    public static final String NOMBRE_BASE_DE_DATOS = "InventoryConnector";
    public static final String NOMBRE_TABLA_PRODUCTOS = "PRODUCTO";
    public static final String NOMBRE_TABLA_PROVEEDORES = "PROVEEDOR";
    
    
    public CrearTablas(Connection con){
       this.con = con;
       gestionarTablas();
    }
    public CrearTablas(){
        this(null);
    }
    
    private void crearProductos() {
        String crearProductoTabla = "CREATE TABLE " + NOMBRE_TABLA_PRODUCTOS + " (" +
                    "sku VARCHAR(255) PRIMARY KEY," +
                    "nombre VARCHAR(255)," +
                    "precio DOUBLE," +
                    "cantidad INT," +
                    "proveedor VARCHAR(255)," +
                    "FOREIGN KEY (proveedor) REFERENCES PROVEEDOR(nombre))";
        
        
         try (Statement statement = con.createStatement()) {
                statement.executeUpdate(crearProductoTabla);
                System.out.println("Tabla 'PRODUCTO' creada exitosamente.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
    
    private void crearProveedor(){
        String crearProveedorTabla = "CREATE TABLE " + NOMBRE_TABLA_PROVEEDORES + "(" +
                    "nombre VARCHAR(255) PRIMARY KEY)";
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate(crearProveedorTabla);
                System.out.println("Tabla 'PROVEEDOR' creada exitosamente.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private boolean comprobarProducto(){
    String consulta = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
            try {
            PreparedStatement pstmt = con.prepareStatement(consulta);
            pstmt.setString(1, NOMBRE_BASE_DE_DATOS); //poner constante
            pstmt.setString(2, NOMBRE_TABLA_PRODUCTOS);
            ResultSet resultado = pstmt.executeQuery();
            if (resultado.next()) {
            // La tabla 'PRODUCTO' existe en la base de datos 'InventoryConnector'
            System.out.println("La tabla " + NOMBRE_TABLA_PRODUCTOS + " existe.");
            return true;
            } else {
            // La tabla 'PRODUCTO' no existe en la base de datos 'InventoryConnector'
            System.out.println("La tabla " + NOMBRE_TABLA_PRODUCTOS + " no existe.");

            }
            pstmt.close();
            return false;
            } catch (SQLException ex) {
            Logger.getLogger(ArticulosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            return false;
    
    }
    private boolean comprobarProveedor(){
    String consulta = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
            try {
            PreparedStatement pstmt = con.prepareStatement(consulta);
            pstmt.setString(1, NOMBRE_BASE_DE_DATOS); //poner constante
            pstmt.setString(2, NOMBRE_TABLA_PROVEEDORES);
            ResultSet resultado = pstmt.executeQuery();
            if (resultado.next()) {
            // La tabla 'PRODUCTO' existe en la base de datos 'InventoryConnector'
            System.out.println("La tabla " + NOMBRE_TABLA_PROVEEDORES + " existe.");
            return true;
            } else {
            // La tabla 'PRODUCTO' no existe en la base de datos 'InventoryConnector'
            System.out.println("La tabla " + NOMBRE_TABLA_PROVEEDORES + " no existe.");

            }
            pstmt.close();
            return false;
            } catch (SQLException ex) {
            Logger.getLogger(ArticulosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            return false;
    
    }
    private void gestionarTablas(){
        if(this.comprobarProveedor() && this.comprobarProducto()){
            //No generamos nada

        }else{
         
        this.crearProveedor();//Creamos primero la entidad Padre dado que depende de la hija
        this.crearProductos();
        }
    
    }
}
