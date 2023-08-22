/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.xspin.utilites;

import com.xspin.model.Proveedor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author Usuario
 */
public class Utilities {
    
    public Utilities(){
    
    }
    
    
    
    //Se encarga de centrar el frame en el medio de la pantalla
    public static void centerFrame(JFrame frame){
        frame.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calcular el centro de la pantalla
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;

        // Calcular la posición del marco para que aparezca en el centro
        int frameX = centerX - (frame.getWidth() / 2);
        int frameY = centerY - (frame.getHeight() / 2);

        // Establecer la posición del marco
        frame.setLocation(frameX, frameY);
    }
    //Se encarga de añadir a un JComboBox los valores de los proveedores
    public static void insertarProveedores(JComboBox<String> proveedores,  LinkedList<Proveedor> listaProveedores){
    
        proveedores.addItem("SIN PROVEEDOR");
        for (Proveedor proveedor : listaProveedores) {
            proveedores.addItem(proveedor.getNombre());
        }
    
    
    }
    
    
    
    
    
    
    
    
}
