/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.xspin.controller;

import com.xspin.model.Articulo;
import com.xspin.model.ArticulosDao;
import com.xspin.model.ConexionWeb;
import com.xspin.model.CrearTablas;
import com.xspin.model.Proveedor;
import com.xspin.model.ProveedorDao;
import com.xspin.view.AddFrame;
import com.xspin.view.BuscarProveedorFrame;
import com.xspin.view.ModificarFrame;
import com.xspin.view.ProveedorFrame;
import com.xspin.view.SearchFrame;
import com.xspin.view.FrameInicio;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Usuario
 */
public class Controller{
    private ConexionWeb web;
    
    
    private FrameInicio webFrame = null;
    private AddFrame addFrame = null;
    private SearchFrame searchFrame = null;
    
    public Controller(){
        inicioPrograma();
    
    }
    //Se encarga de realizar la conexión a la BD y de crear el frame de inicio, además de comprobar si el programa tiene las
    //tablas de productos y proveedores creadas. En caso contrario las crea
    private void inicioPrograma(){
        FrameInicio webFrame = new FrameInicio(this);
        webFrame.setVisible(true);
        this.web = new ConexionWeb();
        CrearTablas crearTablas = new CrearTablas(this.web.getCon());
    
    }
    public void createAddFrame(){
    
        AddFrame addFrame = new AddFrame(this);
        addFrame.setVisible(true);
        this.addFrame = addFrame;
    }
    public void addProduct(){
        subidaBoton();
    }
    public void eliminarProducto(JTable tabla){
        
         int selectedRow = tabla.getSelectedRow();
         int selectedColumn = 0;
         if (selectedRow != -1 && selectedColumn != -1) {
             Object selectedData = tabla.getValueAt(selectedRow, selectedColumn);
             ArticulosDao dao = new ArticulosDao(this.web.getCon());
             dao.borrarProducto(selectedData.toString());
         }
         searchFrame.primerosDatos();
         
    }
    public void modificarProducto(Articulo articulo){
        ModificarFrame modificarFrame = new ModificarFrame(this,articulo);
        modificarFrame.setVisible(true);
        
    }
    public void gestionarSubidaProducto(Articulo articulo){
        
        
        ArticulosDao dao = new ArticulosDao(this.web.getCon());
        dao.modificarProducto(articulo);
        searchFrame.primerosDatos();
        
    }

    public void setWebFrame(FrameInicio frame){
        this.webFrame = frame;
    }
    public void setAddFrame(AddFrame frame){
        this.addFrame = frame;
    }
    public void setSearchFrame(SearchFrame frame){
        this.searchFrame = frame;
    }
    
    //Se encarga de subir el producto 
    public void subidaBoton(){
              JTextField nombre = addFrame.getNombre();
              JTextField cantidad = addFrame.getCantidad();
              JTextField precio = addFrame.getPrecio();
              JTextField sku = addFrame.getSku();
              JComboBox<String> proveedores = addFrame.getProveedores();
              
              
       
              
        if (!nombre.getText().equals(addFrame.getMensajePrimerDato()) && !nombre.getText().isEmpty()) {
            if (!cantidad.getText().equals(addFrame.getMensajeSegundoDato()) && !cantidad.getText().isEmpty()) {
                if (!precio.getText().equals(addFrame.getMensajeTercerDato()) && !precio.getText().isEmpty() ) {
                    precio.setText(precio.getText().replaceAll(",", "."));
                    
                    if(!sku.getText().equals(addFrame.getMensajeCuartoDato()) && !sku.getText().isEmpty()){
                        Articulo articulo = new Articulo();
                        articulo.setNombre(nombre.getText());
                        articulo.setSku(sku.getText());
                        articulo.setPrecio((Double.parseDouble(precio.getText())));
                        articulo.setCantidad((Integer.parseInt(cantidad.getText())));
                        articulo.setProveedor((String) proveedores.getSelectedItem());
                        ArticulosDao dao = new ArticulosDao(this.web.getCon());
                        
                        if(!dao.existeSKU(sku.getText())){
                        dao.insertarProductos(articulo);
                        addFrame.dispose();
                        searchFrame.primerosDatos();
                        }else{
                                  JOptionPane.showMessageDialog(null, "       SKU DUPLICADO",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                        }
                        
                    }
                }
            }
        }else{
        
        }
    
    
    }
    public LinkedList<Articulo> consultarProducto(){
        String sku = searchFrame.getSku().getText();
        ArticulosDao dao = new ArticulosDao(this.web.getCon());
        if(sku.isEmpty()){
             LinkedList<Articulo> articulos = dao.buscarProductosDefault();
             return articulos;
        }else if(!sku.equals("") && !sku.equals(searchFrame.getMensajePrimerDato())){
            
            LinkedList<Articulo> articulos = dao.buscarProductoSku(sku);
            return articulos;
            
        }
        return null;
        
    }
    
   
   public LinkedList<Articulo> obtenerArticulos(){
       ArticulosDao dao = new ArticulosDao(this.web.getCon());
       LinkedList<Articulo> listaArticulos = new LinkedList<>(dao.buscarProductosDefault());
       return listaArticulos;
   }
   public void subirProveedor(){
       ProveedorFrame proveedorFrame = new ProveedorFrame(this);
       proveedorFrame.setVisible(true);
   }
   public void subirProveedorDato(Proveedor proveedor){
        ProveedorDao dao = new ProveedorDao(this.web.getCon());
        dao.subirProveedor(proveedor);
   
   }
   
   public void modificarProveedor(){
       BuscarProveedorFrame buscarFrame = new BuscarProveedorFrame(this);
       buscarFrame.setVisible(true);
       
   }
   
   
   public LinkedList<Proveedor> obtenerProveedores(){
       ProveedorDao dao = new ProveedorDao(this.web.getCon());
       LinkedList<Proveedor> listaProveedores = dao.obtenerProveedores();
       return listaProveedores;
   }
   public void eliminarProveedor(JTable tabla){
         int selectedRow = tabla.getSelectedRow();
         int selectedColumn = 0;
         if (selectedRow != -1 && selectedColumn != -1) {
             Object selectedData = tabla.getValueAt(selectedRow, selectedColumn);
             ProveedorDao dao = new ProveedorDao(this.web.getCon());
             dao.borrarProducto(selectedData.toString());
         }
         
   }
}