/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controlador.EstudianteController;
import modelo.EstudianteDB;
import vista.FrmMenu;

/**
 *
 * @author luisaanaeltovarcortez
 */
public class main {
    public static void main(String[] args){
        
        FrmMenu ventana = new FrmMenu();
        EstudianteDB estudiantedb = new EstudianteDB();
        
        //CREAR OBJETO DEL CONTROLADOR
        EstudianteController controlador = new  EstudianteController(ventana, estudiantedb);
        
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
    }
    
}
