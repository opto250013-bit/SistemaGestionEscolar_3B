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
import vista.FrmMenu;

/**
 *
 * @author luisaanaeltovarcortez
 */
public class EstudianteController implements ActionListener {
    
    //CREAR OBJETOS PARA EL MODELO Y LA VISTA
    private FrmMenu ventana;
    private EstudianteDB estudiantedb;
    
    // CONSTRUCTOR PARA INICIALIZAR LOS OBJETOS
    public EstudianteController(FrmMenu ventana,EstudianteDB estudiantedb){
        
        this.ventana = ventana;
        this.estudiantedb = estudiantedb;
        
        this.ventana.btnGuardar.addActionListener(this);
        
        this.ventana.btnGestion.addActionListener(this);
        
        //MOSTRAR USUARIOS CUANDO SE CREA LA VENTANA
        mostrarEstudiantes();
        
        
    }

    // METODO OBLIGATORIO PARA LA CLASE ABSTRACTA
    // OVERRIDE ES SOBRESCRIBIR EL CODIGO DE UN METODO EXISTENTE (actionperformed)
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // VALIDAR SI EL BOTON FUE PRESIONADO
        if(e.getSource() == ventana.btnGuardar){
            
         //CODIGO PARA GUARDAR LOS ESTUDIANTES  
         
            guardarEstudiantes();
        }    
        
        if(e.getSource() == ventana.btnGestion){
            
            irGestionEstudiantes();
        }
        
    }
    
    //METODO PARA GUARDAR ESTUDIANTE
    private void guardarEstudiantes(){
        
        //OBTENER LOS DATOS DESDE LA VENTANA
        String nombre = ventana.txtNombre.getText().trim();
        String matricula = ventana.txtMatricula.getText().trim();
        int edad = Integer.parseInt(ventana.spnEdad.getValue().toString());
        String carrera = ventana.cmbCarrera.getSelectedItem().toString();
        
        //CREAR OBJETO PARA EL ESTUDIANTE
        estudiante estudiante = new estudiante(nombre, matricula, edad, carrera);
        
        //ENVIAR EL OBJETO DE ESTUDIANTE ANTES GUARDADO PARA QUE EL MODELO LO RECIBA
        boolean resultado = estudiantedb.insertar(estudiante);
        
        //EVALUAR SI EL RESULTADO DE LA INSERCION FUE EXITOSO
        if(resultado){
            JOptionPane.showMessageDialog(ventana, "Registro exitoso");
            limpiarCampos();
            
            //MOSTRAR ESTUDIANTES DESPUES DE GUARDAR EL USUARIO
            mostrarEstudiantes();
        }else{
            JOptionPane.showMessageDialog(ventana, "Error al registrar");   
        }
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
            JTable tablaEst = new JTable(modelo);
            
            
            ventana.paneTabla.setViewportView(tablaEst);
        }
        
        
        
        // METODO PARA IR A LA GESTION DE ESTUDIANTES
        private void irGestionEstudiantes(){
            
            FrmGestionEstudiantes ventanaGestion = new FrmGestionEstudiantes();
            EstudianteDB estudiantesdb = new EstudianteDB();
            GestionEstudiantesController controladorGestion = new GestionEstudiantesController(ventanaGestion, estudiantedb);
            
            ventanaGestion.setLocationRelativeTo(null);
            ventanaGestion.setVisible((true));
            
            // CERRAR LA VENTANA ACTUAL
            ventana.dispose();
        }
    
    
        //METODO PARA LIMPIAR CAMPOS
        private void limpiarCampos(){
            ventana.txtNombre.setText("");
            ventana.txtMatricula.setText("");
            ventana.spnEdad.setValue(0);
            ventana.cmbCarrera.setSelectedIndex(0);
        }
    
        
        
}
