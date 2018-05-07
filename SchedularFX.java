/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedularfx;

import java.util.Timer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author GeorgPersen
 */
public class SchedularFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Start request-metode for faktura");
        Timer timer = new Timer();
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Tråd startet");
                SchedularMain scMain = new SchedularMain();
                // Hver 24. time
                //timer.scheduleAtFixedRate(scMain, 0, 1000*60*60*24);
                // Hvert minutt
                timer.scheduleAtFixedRate(scMain, 0, 1000*60);
            }
        });
        
        Button btnExit = new Button();
        btnExit.setText("Avslutt programmet");
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Program avsluttet");
                System.exit(0);
            }
        });
        
        VBox root = new VBox();
        //StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.getChildren().add(btnExit);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("HTTP-request generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
