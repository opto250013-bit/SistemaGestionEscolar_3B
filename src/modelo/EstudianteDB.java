/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author luisaanaeltovarcortez
 */
import conexion.conexionDB;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

// CLASE QUE PERMITE MANEJAR LOS OBJETOS DEL SISTEMA Y ENVIARLOS A LA BD
public class EstudianteDB {
    
    //METODO PARA REGISTRAR EN LA BASE DE DATOS
    public boolean insertar(estudiante estudiante){
        
        //CREAR SENTENCIA SQL
        String sql_query = "INSERT INTO estudiantes(nombre, matricula, edad, carrera) VALUES (?, ?, ?, ?)";
        
       
        try{
            //CONEXION EN LA BASE DE DATOS
            Connection conn = conexionDB.conexion();
            
            //CREAR EL PREPAREDsTATEMENT PARA MANDARLO A LA BD
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            
            //ENVIAR LOS DATOS DEL MODELO
            stmt.setString(1,estudiante.getNombre());
            stmt.setString(2,estudiante.getMatricula());
            stmt.setInt(3,estudiante.getEdad());
            stmt.setString(4,estudiante.getCarrera());
            
            //EJECUTAR QUERY EN LA BD
            
            stmt.executeUpdate();
            
            //CERRAR Statement y conexion a la BD
            stmt.close();
            conn.close();
            
            return true;
            
            
        }catch(SQLException e){
            
            //EN CASO DE ERROR NOTIFICAR AL USUARIO
            System.out.print("Error al insertar: " + e.getMessage());
            return false;
        }
        
    }
    
    //METODO PARA CONSULTAR TODOS LOS ESTUDIANTES
    public List<estudiante> consultarEstudiante(){
        
        List<estudiante> listaEstudiantes = new ArrayList<>();
        
        String query_sql = "SELECT * FROM estudiantes";
        
        try{
            
            Connection conn = conexionDB.conexion();     
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            ResultSet result = stmt.executeQuery();
            
            while(result.next()){
                
                int id = result.getInt("id_estudiante");
                String nombre = result.getString("nombre");
                String matricula = result.getString("matricula");
                int edad = result.getInt("edad");
                String carrera = result.getString("carrera");
                
                //CREAR OBJETO ESTUDIANTES Y GUARDARLOS EN LA LISTA
                estudiante estudiante = new estudiante(id, nombre, matricula, edad, carrera);
                
                //GUARDAR EL OBJETO EN LA LISTA 
                listaEstudiantes.add(estudiante);
                
            }
            
        }catch(SQLException e){
            
            System.out.println("Error de consulta: " + e.getMessage());
            
        }
        
        return listaEstudiantes;
    }
    
    //Metodo para actualizar estudiantes
     public boolean actualizar(estudiante estudiante){
         String query_sql = "UPDATE estudiantes SET nombre=?, matricula=?, edad=?, carrera=? WHERE id_estudiante=?";
         
         try{
         Connection conn = conexionDB.conexion();
            
            //CREAR EL PREPAREDsTATEMENT PARA MANDARLO A LA BD
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            
            //ENVIAR LOS DATOS DEL MODELO
            stmt.setString(1,estudiante.getNombre());
            stmt.setString(2,estudiante.getMatricula());
            stmt.setInt(3,estudiante.getEdad());
            stmt.setString(4,estudiante.getCarrera());
            stmt.setInt(5,estudiante.getId());
            
            //Verificar el no. de filas que cambiaron
            int filas_cambiadas = stmt.executeUpdate();
            return filas_cambiadas>0;
            
         }catch(SQLException e){
             
         System.out.println("Error al actualizar en la BD" + e.getMessage());
         return false;
         
         }
     }
     //MÉTODO PARA ELIMINAR UN REGISTRO
     public boolean eliminar(int idEstudiante){
         
         String query_sql = "DELETE FROM estudiantes WHERE id_estudiante = ?";
         
         try{
            Connection conn = conexionDB.conexion();
            //CREAR EL PREPAREDsTATEMENT PARA MANDARLO A LA BD
            PreparedStatement stmt = conn.prepareStatement(query_sql);  
            
            stmt.setInt(1, idEstudiante);
            
            //Valor de las filas afectadas
            int filas_cambiadas = stmt.executeUpdate();
            return filas_cambiadas > 0;
             
         }catch(SQLException e){
             System.out.println("Error en la eliminación: " + e.getMessage());
             return false;
         }
         
     }
         
         
}
