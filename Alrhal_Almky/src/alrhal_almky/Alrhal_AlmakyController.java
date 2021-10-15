/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alrhal_almky;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Nuha
 */
public class Alrhal_AlmakyController implements Initializable {
@FXML 
private MediaView mv;
@FXML
private ImageView homeButton;

private Parent root;
    private Scene scene;
    private Stage stage;
private static MediaPlayer mediaPlayer;
private MediaPlayer mp;
private Media me;

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
       String path = new File("Alrhal_scene.mp4").getAbsolutePath();
       me = new Media(new File(path).toURI().toString());
       mp = new MediaPlayer(me);
       mv.setMediaPlayer(mp);
       mp.setAutoPlay(true);
       
       Media sound = new Media(new File("congrats.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        
        Timer timer = new Timer();

        TimerTask homeButtonTask = new TimerTask() {
            @Override
            public void run() {
                homeButton.setVisible(true);

            }
        };
        timer.schedule(homeButtonTask, 15000);
       
       /**
       DoubleProperty width = mv.fitWidthProperty();
       DoubleProperty height = mv.fitHeightProperty();
       width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
       height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
       */
     }
}
