/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.EstudianteDB;
import modelo.estudiante;
import vista.FrmGestionEstudiantes;

/**
 *
 * @author luisaanaeltovarcortez
 */
public class GestionEstudiantesController implements ActionListener{
    
    private FrmGestionEstudiantes ventana;
    private EstudianteDB estudiantedb;
    private JTable tabla;
    private int idSeleccionado = -1;
    
    //CONSTRUCTOR
    public GestionEstudiantesController(FrmGestionEstudiantes ventana,EstudianteDB estudiantedb){
        
        this.ventana = ventana;
        this.estudiantedb = estudiantedb;
        this.ventana.btnActualizar.addActionListener(this);
        this.ventana.btnEliminar.addActionListener(this);
        this.ventana.btnLimpiar.addActionListener(this);
        
         mostrarEstudiantes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == ventana.btnActualizar){
            actualizarEstudiante();
        }
        
        if(e.getSource() == ventana.btnEliminar){
            eliminarEstudiante();
        }
        
        if(e.getSource() == ventana.btnLimpiar){
            limpiarCampos();
        }
        
       
    }
    
    // METODO PARA DETECTAR EL CLIC EN LA TABLA
    private void seleccionTabla(){
        tabla.getSelectionModel().addListSelectionListener(evento -> {
        
            if(!evento.getValueIsAdjusting()){
                
                //TODO: AGREGAR LA CRGA DE ESTUDIANTES
                cargarEstudiantes();
            }
            
        });
    }
    
    // METODO PARA CARGAR LOS ESTUDIANTES
    private void cargarEstudiantes(){
        int fila = tabla.getSelectedRow();
        
        if(fila == -1){
            return;
        }
        
        //  OBTENER LOS VALORES DESDE LA TABLA
        
        idSeleccionado = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
        String nombre = tabla.getValueAt(fila, 1).toString();
        String matricula = tabla.getValueAt(fila, 2).toString();
        int edad = Integer.parseInt(tabla.getValueAt(fila, 3).toString());
        String carrera = tabla.getValueAt(fila,4).toString();
        
        
        //MOSTRAR DATOS AL USUARIO
        ventana.txtNombre.setText(nombre);
        ventana.txtMatricula.setText(matricula);
        ventana.spnEdad.setValue(edad);
        ventana.cmbCarrera.setSelectedItem(carrera);
        
    }
    
    
    
    //METODO PARA CREAR LA TABLA
        private void mostrarEstudiantes(){
            DefaultTableModel modelo = new DefaultTableModel();
            
            //AÑADIR COLUMNAS DE LA TABLA
            modelo.addColumn("ID");
            modelo.addColumn("Nombre");
            modelo.addColumn("Matricula");
            modelo.addColumn("Edad");
            modelo.addColumn("Carrera");
            
            List<estudiante> listaEstudiantes = estudiantedb.consultarEstudiante();
            
            //CICLO PARA RECORRER LA LISTA DE ESTUDIANTES
            for(estudiante estudiante : listaEstudiantes){
                
                //GUARDAR LOS OBJETOS EN UN ARREGLO PARA USARLOS EN LA TABLA
                Object[] fila = {
                  
                    estudiante.getId(),
                    estudiante.getNombre(),
                    estudiante.getMatricula(),
                    estudiante.getEdad(),
                    estudiante.getCarrera(),
                };
                
                modelo.addRow(fila);
                
            }  
            
            //CREAR TABLA
           tabla = new JTable(modelo);
            
            
            ventana.paneTabla.setViewportView(tabla);
            
            seleccionTabla();
        }
        
    //Metodo para actualizar registro
    private void actualizarEstudiante(){
        //Validar si un ID fue seleccionado
        if(idSeleccionado==-1){
        JOptionPane.showMessageDialog(ventana, "Seleciona un registro");
        return;
        }
        
        //Obtener los valores desde la ventana
        String nombre = ventana.txtNombre.getText().trim();
        String matricula = ventana.txtMatricula.getText().trim();
        int edad=Integer.parseInt(ventana.spnEdad.getValue().toString());
        String carrera = ventana.cmbCarrera.getSelectedItem().toString();
        
        if(nombre.isEmpty() || matricula.isEmpty()){
        
            JOptionPane.showMessageDialog(ventana,"Completa todos los campos");
            return;
        }
        
        estudiante estudiante = new estudiante(idSeleccionado, nombre, matricula, edad, carrera);
        
        boolean actualizar = estudiantedb.actualizar(estudiante);
        
        if(actualizar){
        
            JOptionPane.showMessageDialog(ventana, "Actualizacion exitosa");
            mostrarEstudiantes();
            limpiarCampos();
            
        }else{
          
            JOptionPane.showMessageDialog(ventana, "Error al actualizar");

        }
    }
    
    
    //MÉTODO PARA ELIMINAR REGISTROS
    public void eliminarEstudiante(){
        
        //Validar si hay un ID seleccionado
        if(idSeleccionado == -1){
            JOptionPane.showMessageDialog(ventana, "Selecciona un registro");
            return;
        }
        
        //Hacer confirmación de la eliminación del registro
        int confirmacion = JOptionPane.showConfirmDialog(
                ventana,
                "¿Deseas eliminar al estudiante?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        
        //SI LA CONFIRMACIÓN ES DIFERENTE DE "SI", entonces no hace nada
        if(confirmacion != JOptionPane.YES_OPTION){
            return;
        }
        
        boolean eliminacion = estudiantedb.eliminar(idSeleccionado);
        if(eliminacion){
            JOptionPane.showMessageDialog(ventana, "Eliminación Exitosa");
            mostrarEstudiantes();
            limpiarCampos();
        }else{
            JOptionPane.showMessageDialog(ventana, "Error al eliminar");
        }
    }
    
    
        //METODO PARA LIMPIAR CAMPOS
        private void limpiarCampos(){
            idSeleccionado=-1;
            
            ventana.txtNombre.setText("");
            ventana.txtMatricula.setText("");
            ventana.spnEdad.setValue(0);
            ventana.cmbCarrera.setSelectedIndex(0);
            
            if(tabla != null){
                tabla.clearSelection();
            }
        }
    
}
