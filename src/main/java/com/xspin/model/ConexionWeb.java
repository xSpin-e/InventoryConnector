/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.xspin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ConexionWeb {
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String baseDatos = "InventoryConnector";
    private final String usuario = "root";
    private final String password = "";
    private Connection con;
        public ConexionWeb(){
          DriverManager.setLoginTimeout(15);
        try {
            //Obtenemos conexion con la base de datos
            this.con = DriverManager.getConnection(url+baseDatos,usuario,password);
            System.out.println("Conexión establecida");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionWeb.class.getName()).log(Level.SEVERE, null, ex);
            
        }
         
        }

    public Connection getCon() {
        return con;
    }
        
        
        
        
}
