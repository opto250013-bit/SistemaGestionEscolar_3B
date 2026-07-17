/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;
import java.sql.*;
/**
 *
 * @author luisaanaeltovarcortez
 */
public class conexionDB {
    
    //MANEJO DE LAS CONSTANTES DE DATOS DE LA CONEXION
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_escolar";
    private static final String USER = "root";
    private static final String PASS = "";
    
    //Metodo para realizar la conexion
    public static Connection conexion(){
        
        Connection conn = null;
        
        
        //MANEJAR EL ERROR EN LA CONEXION CON LA BASE DE DATOS
        try{
            
            //GUARDAR CONEXIÓN EN EL OBJETO CONNECTION
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.print("Conexión exitosa: ");
            
        }catch(SQLException e){
            
            //MANEJAR EL ERROR DE SQL
            System.out.print("Error en la conexión: " + e.getMessage());
        }
     
        return conn;
    }
    
}
