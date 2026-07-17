/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author luisaanaeltovarcortez
 */


//CLASE PARA MANEJAR LA INFORMACION DEL ESTUDIANTE (SIN BASE DE DATOS)
public class estudiante {
    
    private int id;
    private String nombre;
    private String matricula;
    private int edad;
    private String carrera;
    
    // 1. CONSTRUCTOR PARA INICIALIZAR UN OBJETO VACIO, ES EQUIVALENTE A:
    // Estudiante estudiante = new Estudiante();
    public estudiante(){
        
    }
    
    
    // 2. CONSTRUCTOR PARA CREAR UN OBJETO DENTRO DEL SISTEMA (SIN BD)
    // Estudiante estudiante = new Estudiante(Anael, "tclo250531", 19, "ds");
    public estudiante(String nombre, String matricula, int edad, String carrera) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.edad = edad;
        this.carrera = carrera;
    }
    
    
    //3. CONSTRUCTOR PARA CREAR UN OBJETO QUE OCUPE LA BD

    public estudiante(int id, String nombre, String matricula, int edad, String carrera) {
        this.id = id;
        this.nombre = nombre;
        this.matricula = matricula;
        this.edad = edad;
        this.carrera = carrera;
    }
    
    //USAR GETTERS Y SETTERS PARA LAS OTRAS CLASES DEL SISTEMA

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    
    
    
    
    
    
    
    
}
