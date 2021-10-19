/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alrhal_almky;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Mena_lossController implements Initializable {
     private Parent root;
     private Scene scene;
     private Stage stage;

    /**
     * Initializes the controller class.
     */
     
    @FXML
    private Label loss_point;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("UserPoints.txt"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                int userPoints = Integer.parseInt(line);
                System.out.println(userPoints);
                loss_point.setText(Integer.toString(userPoints));
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            // Exception handling
        } catch (IOException e) {
            // Exception handling
        };
    }   
    
      public void back2(MouseEvent event) throws IOException{ 
      if(event.getButton()== MouseButton.PRIMARY){
    FXMLLoader loader2 = new FXMLLoader();
     ((Stage)(((ImageView)event.getSource()).getScene().getWindow())).close();
     //
        root = FXMLLoader.load(getClass().getResource("Mena.fxml"));
        //stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        scene = new Scene(root);
        MenaController mena = new MenaController();
        mena.setScene(scene);
        stage.setScene(scene);
stage.setResizable(false);
            stage.setTitle("الرحـــال المـكــــي");
            stage.getIcons().add(new Image(Alrhal_Almky.class.getResourceAsStream("images/icon.png")));
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        stage.show();
     //
        //root = FXMLLoader.load(getClass().getResource("Alharam.fxml"));
        
       // stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
       // Stage stage = new Stage();
       // stage.close(); //colce the old window
        
        
       // scene = new Scene(root);
        
      // stage.setScene(scene);
       
        
        //stage.setScene(scene);
       // stage.show();

        }
      //*/
    //}
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
            stage.setTitle("الرحـــال المـكــــي");
            stage.getIcons().add(new Image(Alrhal_Almky.class.getResourceAsStream("images/icon.png")));
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        stage.show();
    }
        }
}
