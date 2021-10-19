/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alrhal_almky;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Mena_winController implements Initializable {
    
     private Parent root;
     private Scene scene;
     private Stage stage;

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label win_point;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
         try (BufferedReader bufferedReader = new BufferedReader(new FileReader("UserPoints.txt"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                int userPoints = Integer.parseInt(line);
                System.out.println(userPoints);
                win_point.setText(Integer.toString(userPoints));
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            // Exception handling
        } catch (IOException e) {
            // Exception handling
        };
    
    }
    
            public void handleMaptButton(MouseEvent event) throws IOException {
            if(event.getButton()== MouseButton.PRIMARY){
          ((Stage)(((ImageView)event.getSource()).getScene().getWindow())).close(); 
        root = FXMLLoader.load(getClass().getResource("gameـmap.fxml"));
        //((Stage)(((ImageView)event.getSource()).getScene().getWindow())).close();
       // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       Stage stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
stage.setResizable(false);
        stage.show();
    }
        }
                    public void tempToInfo(MouseEvent event) throws IOException {
            if(event.getButton()== MouseButton.PRIMARY){
                ((Stage)(((ImageView)event.getSource()).getScene().getWindow())).close(); 
        root = FXMLLoader.load(getClass().getResource("MenaInfo.fxml"));
       // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       Stage stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
            }
    }  
            
            

    
}
