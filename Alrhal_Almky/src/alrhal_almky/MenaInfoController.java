/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alrhal_almky;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author argha
 */
public class MenaInfoController implements Initializable {

    
    HashMap<String, Image> alharamInfoImages = new HashMap<>();
    ImageView source = new ImageView();
    ImageView target = new ImageView();
    
    private Parent root;
    private Scene scene;
    private Stage stage;
    
    public void showInfo(MouseEvent event) throws IOException{
  
        source = (ImageView) event.getSource();
        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setCursor(Cursor.HAND); //Change cursor to hand
        target = (ImageView)scene.lookup("#infoRectAlharam");
        System.out.println(source.getId()+".png");
        Image image = new Image(new File("/images/info/"+source.getId()+".png").toURI().toString(),
                149, 149, false, false);
        //target.setPreserveRatio(true);
        //target.setFitWidth(149);
        //target.setFitHeight(149);
        System.out.println(image.heightProperty());
        System.out.println(image.widthProperty());
        target.setImage(image);    
        System.out.println(target.getImage().heightProperty());
        System.out.println(target.getImage().widthProperty());

    }
    
    
    public void chageCurser(MouseEvent event) throws IOException{
        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setCursor(Cursor.HAND); //Change cursor to hand
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
