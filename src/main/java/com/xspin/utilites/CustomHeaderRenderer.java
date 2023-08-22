/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.xspin.utilites;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Usuario
 */
public class CustomHeaderRenderer extends DefaultTableCellRenderer {
    
    
    public CustomHeaderRenderer() {
        setOpaque(true);
        setHorizontalAlignment(CENTER); // Alinear el texto al centro del encabezado
        setForeground(Color.WHITE); // Color del texto del encabezado
        setBackground(Color.RED); // Color de fondo del encabezado
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Agregar un espacio alrededor del texto
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar líneas de rejilla horizontales
        g.setColor(Color.WHITE);
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }
    
}
