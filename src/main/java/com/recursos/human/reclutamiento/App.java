/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main.java.com.recursos.human.reclutamiento;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import main.java.com.recursos.human.reclutamiento.util.SceneManager;

/**
 *
 * @author informatica
 */
public class App  extends Application{
    
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception{
        this.stage = stage;
        SceneManager sceneManager = new SceneManager(stage);
        sceneManager.showLoginView();
        stage.show();
    }    

    public static void main(String[] args) {
        
        launch();
        
    }
}