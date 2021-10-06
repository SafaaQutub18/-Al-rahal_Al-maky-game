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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MenaController implements Initializable {

    private Parent root;
    private Scene scene;
    private Stage stage;
    private Scene menaScene;

    @FXML
    private void handleMaptButton(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("gameـmap.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
    public void setScene(Scene scene) {
        
        this.menaScene = scene;

                
        String[] imgs = {"#khema_1", "#kobra_1", "#wosta_1", "#soghra_1", "#haj_1", "#hajar_1", "#rami_1"};
        
        DragDropHandler dragDropHandler = new DragDropHandler();
        
        
        for(String i : imgs) {
          ImageView img1 = (ImageView) scene.lookup(i);
          img1.setCursor(Cursor.HAND);
          img1.setOnDragDetected(dragDropHandler.myHandlerDetected);
          img1.setOnMousePressed(dragDropHandler.circleOnMousePressedEventHandler);
          img1.setOnMouseDragged(dragDropHandler.circleOnMouseDraggedEventHandler);
          
          ImageView img2 = (ImageView) scene.lookup(i.replace("_1", "_2"));
          img2.setOnMouseDragged(dragDropHandler.myHandlerDragged);
          img2.setOnDragOver(dragDropHandler.myHandlerOver);
          img2.setOnDragDropped(dragDropHandler.myHandlerDropped);
        }
        
  
    }
    
}
