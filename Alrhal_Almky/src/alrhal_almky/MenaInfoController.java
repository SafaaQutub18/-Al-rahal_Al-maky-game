/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alrhal_almky;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    private static MediaPlayer mediaPlayer;
    
    double glowVal = 0.5;

   // Create initial effects and transforms.
   Glow glow = new Glow(0.5);
    
    String[] imgs = new String[]{"khema", "kobra", "wosta", "soghra", "haj", "hajar"};
    
    @FXML
    private void handleMaptButton(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("gameـmap.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void showInfo(MouseEvent event) throws IOException{
  
        source = (ImageView) event.getSource();
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = stage.getScene();
        //scene.setCursor(Cursor.HAND); //Change cursor to hand
        target = (ImageView)scene.lookup("#infoRectAlharam");
        
                for (int i = 0; i < imgs.length; i++) {
            if(!(imgs[i].equalsIgnoreCase(source.getId()))) {
                System.out.println("#"+imgs[i]);
                ImageView temp = (ImageView)scene.lookup("#"+imgs[i]);
                temp.setEffect(null);
            }
        }
        
        //ImageView temp = (ImageView)scene.lookup("#kaaba");
        //temp.setImage(null);
        System.out.println(source.getId()+".png");
        InputStream stream = getClass().getResourceAsStream("images/info/"+source.getId()+".png");
        
        Media sound = new Media(new File(source.getId()+".mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        
        //"images/" is the a local directory where all my images are located
        Image newImage = new Image(stream); 
        target.setImage(newImage);
        source.setEffect(glow);
        //target.setEffect(glow);

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
       Media sound = new Media(new File("menaInfo.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }    
    
}
