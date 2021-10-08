/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alrhal_almky;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Nuha
 */
public class AlharamController implements Initializable {

    private Parent root;
    private Scene scene;
    private Stage stage;
    private Scene alharamScene;
    
    ///////////////////////////////////
    @FXML
    private ImageView normal_boy;
        @FXML
    private ImageView bird1;

    @FXML
    private ImageView bird2;
    
    ///////////////////////////////////

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
        
        Timer timer = new Timer();
        
        TimerTask boytask = new TimerTask() {
            @Override
            public void run() {

                TranslateTransition Transition_science = new TranslateTransition();
                Transition_science.setNode(normal_boy);
                Transition_science.setDuration(Duration.seconds(1.2));
                Transition_science.setToY(610);
                Transition_science.play();
            }
        };
        timer.schedule(boytask, 1000l);
        
        TimerTask birdtask = new TimerTask() {
            @Override
            public void run() {
                //bird 1
                TranslateTransition Transition_bird = new TranslateTransition();
                Transition_bird.setNode(bird1);
                Transition_bird.setDuration(Duration.seconds(1.2));
                Transition_bird.setToX(610);
                Transition_bird.play();
                
                //bird2
                TranslateTransition Transition_bird2 = new TranslateTransition();
                Transition_bird2.setNode(bird2);
                Transition_bird2.setDuration(Duration.seconds(1.2));
                Transition_bird2.setToX(610);
                Transition_bird2.play();
                
            }
        };
        timer.schedule(birdtask, 1001l);
        
        
    }
    
    
    public void setScene(Scene scene) {
        
        this.alharamScene = scene;

                
        //ArrayList<ImageView> imgs = new ArrayList<>();
        String[] imgs = {"#kaaba_1", "#makam_1", "#sahn_1", "#zamzam_1", "#methna_1", "#taef_1"};
        //String[] imgs_target = {"kaaba_2"};
        
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
        
        //ImageView img1 = (ImageView) scene.lookup("#kaaba_1");
        
        //ImageView img2 = (ImageView) scene.lookup("#kaaba_2");
        
        
        //img1.setCursor(Cursor.HAND);
        
        //img1.setOnDragDetected(dragDropHandler.myHandlerDetected);
        
        //img2.setOnMouseDragged(dragDropHandler.myHandlerDragged);
        
        //img2.setOnDragOver(dragDropHandler.myHandlerOver);
        
        //img2.setOnDragDropped(dragDropHandler.myHandlerDropped);
        
        //img1.setOnMousePressed(dragDropHandler.circleOnMousePressedEventHandler);
        
        //img1.setOnMouseDragged(dragDropHandler.circleOnMouseDraggedEventHandler);
        
        /*ImageView img3 = (ImageView) scene.lookup("#makam_1");
        
        ImageView img4 = (ImageView) scene.lookup("#makam_2");
        
        
        img3.setCursor(Cursor.HAND);
        
        img3.setOnDragDetected(dragDropHandler.myHandlerDetected);
        
        img4.setOnMouseDragged(dragDropHandler.myHandlerDragged);
        
        img4.setOnDragOver(dragDropHandler.myHandlerOver);
        
        img4.setOnDragDropped(dragDropHandler.myHandlerDropped);
        
        img3.setOnMousePressed(dragDropHandler.circleOnMousePressedEventHandler);
        
        img3.setOnMouseDragged(dragDropHandler.circleOnMouseDraggedEventHandler);*/
    }

}
