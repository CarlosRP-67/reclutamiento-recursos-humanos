/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main.java.com.recursos.human.reclutamiento.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.com.recursos.human.reclutamiento.util.SceneManager;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class DashboardController{

    @FXML
    private AnchorPane contentArea;

    @FXML private Button btnUsuarios;
    @FXML private Button btnClientes;
    @FXML private Button btnEmpleados;
    @FXML private Button btnProductos;
    @FXML private Button btnServicios;
    @FXML private Button btnFacturas;
    @FXML private Button btnCitas;
    @FXML private Button btnCerrarSesion;

    private SceneManager sceneManager;

    @FXML
    public void initialize() {
        System.out.println("diagnostico de rol rapido xd");
        System.out.println("btnUsuarios es nulo?: " + (btnUsuarios == null));
        System.out.println("btnClientes es nulo?: " + (btnClientes == null));
        System.out.println("btnEmpleados es nulo?: " + (btnEmpleados == null));
        System.out.println("btnFacturas es nulo?: " + (btnFacturas == null));
    }


    public void setRolUsuario(String rol) {
        System.out.println("rol interceptado" + rol);
        
        if (rol != null && (rol.equals("2") || rol.toLowerCase().contains("User"))) {
            System.out.println(">>> Aplicando restricciones de Use");
            ocultarBoton(btnUsuarios);
            ocultarBoton(btnClientes);
            ocultarBoton(btnEmpleados);
            ocultarBoton(btnFacturas);
        } else {
            System.out.println(">>> Rol de Administrador detectado");
        }
    }

    private void ocultarBoton(Button boton) {
        if (boton != null) {
            boton.setVisible(false);
            boton.setManaged(false);
        }
    }

    private SceneManager getSceneManager() {
        if (sceneManager == null) {
            Stage stage = (Stage) contentArea.getScene().getWindow();
            sceneManager = new SceneManager(stage);
        }
        return sceneManager;
    }
}
