/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.recursos.human.reclutamiento.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.com.recursos.human.reclutamiento.controller.DashboardController;


public class SceneManager {
    
   private final Stage stage;
    private static int idClienteActual;
    private static String rolUsuarioActual = "1"; 

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public static void setIdClienteActual(int idCliente) {
        idClienteActual = idCliente;
    }

    public static int getIdClienteActual() {
        return idClienteActual;
    }

    public static void setRolUsuarioActual(String rol) {
        rolUsuarioActual = rol;
    }

    public static String getRolUsuarioActual() {
        return rolUsuarioActual;
    }

    public void showLoginView() {
        cargarVistaFisica("src/main/resources/view/login-view.fxml", "src/main/resources/css/login-view.css", "recursos humanos - Iniciar Sesión");
    }

    public void showMainDashboard() {
        String rutaFxml = "src/main/resources/view/dashboard-view.fxml";
        String rutaCss = "src/main/resources/css/dashboard-styles.fxml";
        
        File archivoFxml = new File(rutaFxml);
        if (!archivoFxml.exists()) {
            System.err.println("No existe el archivo físico en la ruta: " + archivoFxml.getAbsolutePath());
            return;
        }

        try (FileInputStream fis = new FileInputStream(archivoFxml)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(archivoFxml.getParentFile().toURI().toURL());
            Parent root = loader.load(fis);

            DashboardController dashboardController = loader.getController();
            if (dashboardController != null) {
                dashboardController.setRolUsuario(rolUsuarioActual);
            }

            Scene scene = new Scene(root, 880, 550);

            if (rutaCss != null) {
                File cssFile = new File(rutaCss);
                if (cssFile.exists()) {
                    scene.getStylesheets().add(cssFile.toURI().toString());
                }
            }

            stage.setScene(scene);
            stage.setTitle("Recursos Humanos");
            stage.setMinWidth(750);
            stage.setMinHeight(450);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            System.err.println("¡ERROR CRÍTICO! Falló la carga del Dashboard: " + rutaFxml);
            e.printStackTrace();
        }
    }

    
    public void cargarVistaClasspath(String rutaFxml, String rutaCss, String titulo) {
        String fxmlFisico = "src/main/resources" + (rutaFxml.startsWith("/") ? rutaFxml : "/" + rutaFxml);
        String cssFisico = (rutaCss != null) ? "src/main/resources" + (rutaCss.startsWith("/") ? rutaCss : "/" + rutaCss) : null;
        cargarVistaFisica(fxmlFisico, cssFisico, titulo);
    }

    public void cargarVistaFisica(String rutaFxml, String rutaCss, String titulo) {
        File archivoFxml = new File(rutaFxml);
        if (!archivoFxml.exists()) {
            System.err.println("¡ERROR! No existe el archivo físico en la ruta: " + archivoFxml.getAbsolutePath());
            return;
        }

        try (FileInputStream fis = new FileInputStream(archivoFxml)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(archivoFxml.getParentFile().toURI().toURL());
            Parent root = loader.load(fis);

            Scene scene = new Scene(root, 880, 550);

            if (rutaCss != null) {
                File cssFile = new File(rutaCss);
                if (cssFile.exists()) {
                    scene.getStylesheets().add(cssFile.toURI().toString());
                }
            }

            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.setMinWidth(750);
            stage.setMinHeight(450);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            System.err.println("¡ERROR CRÍTICO! Falló la carga de la vista: " + rutaFxml);
            e.printStackTrace();
        }
    }

    public void cargarVistaEnCentro(AnchorPane contentArea, String rutaFxml, String rutaCss) {
        File archivoFxml = new File(rutaFxml);
        if (!archivoFxml.exists()) {
            System.err.println("¡ERROR! No existe el archivo físico en la ruta: " + archivoFxml.getAbsolutePath());
            return;
        }

        try (FileInputStream fis = new FileInputStream(archivoFxml)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(archivoFxml.getParentFile().toURI().toURL());
            Parent vista = loader.load(fis);

            if (rutaCss != null && !rutaCss.isEmpty()) {
                File cssFile = new File(rutaCss);
                if (cssFile.exists()) {
                    String cssUrl = cssFile.toURI().toString();
                    if (!vista.getStylesheets().contains(cssUrl)) {
                        vista.getStylesheets().add(cssUrl);
                    }
                }
            }

            contentArea.getChildren().setAll(vista);

            if (vista instanceof AnchorPane) {
                AnchorPane.setTopAnchor(vista, 0.0);
                AnchorPane.setBottomAnchor(vista, 0.0);
                AnchorPane.setLeftAnchor(vista, 0.0);
                AnchorPane.setRightAnchor(vista, 0.0);
            }

        } catch (IOException e) {
            System.err.println("error a la carga de la vista en el centro: " + rutaFxml);
            e.printStackTrace();
        }
    }
}
