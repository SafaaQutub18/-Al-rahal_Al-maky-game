/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alrhal_almky;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Loss_interfaceController implements Initializable {
     private Parent root;
     private Scene scene;
     private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
      public void back2(MouseEvent event) throws IOException{ 
      if(event.getButton()== MouseButton.PRIMARY){
    FXMLLoader loader2 = new FXMLLoader();
     ((Stage)(((ImageView)event.getSource()).getScene().getWindow())).close();
     //
        root = FXMLLoader.load(getClass().getResource("Alharam.fxml"));
        //stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        scene = new Scene(root);
        AlharamController alharam = new AlharamController();
        alharam.setScene(scene);
        stage.setScene(scene);
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
        stage.show();
    }
        }
}
