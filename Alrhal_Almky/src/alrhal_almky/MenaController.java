/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alrhal_almky;

import static alrhal_almky.AlharamController.hearts;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private ImageView normal_boy;
        @FXML
    private ImageView heart3;

    @FXML
    private ImageView heart2;

    @FXML
    private ImageView heart1;

    @FXML
    private Text points;
    @FXML
    private ImageView sad_boy;
    @FXML
    private ImageView sad_boy2;

    public static int hearts = 0;

    private static int userPoints = 0;

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
         userPoints = 0;
        hearts = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("UserPoints.txt"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                userPoints = Integer.parseInt(line);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            // Exception handling
        } catch (IOException e) {
            // Exception handling
        }
        Timer timer2 = new Timer();
        timer2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                points.setText(String.valueOf(userPoints));
                if (hearts == 1) {
                    heart1.setVisible(false);
                } else if (hearts == 2) {
                    normal_boy.setVisible(false);
                    sad_boy2.setVisible(true);
                    heart2.setVisible(false);
                } else if (hearts == 3) {
                    heart3.setVisible(false);
                    sad_boy2.setVisible(false);
                    sad_boy.setVisible(true);
                }
            }
        }, 0, 1000);
        
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

        
    }

    
    public void setScene(Scene scene) {
        
        this.menaScene = scene;

                
        String[] imgs = {"#khema_1", "#kobra_1", "#wosta_1", "#soghra_1", "#haj_1", "#hajar_1", "#rami_1"};
        
        DragDropHandler dragDropHandler = new DragDropHandler();
        
        
        for(String i : imgs) {
          ImageView img1 = (ImageView) scene.lookup(i);
          img1.setCursor(Cursor.HAND);
          img1.setOnDragDetected(dragDropHandler.myHandlerDetected);
          //img1.setOnMousePressed(dragDropHandler.circleOnMousePressedEventHandler);
          //img1.setOnMouseDragged(dragDropHandler.circleOnMouseDraggedEventHandler);
          
          ImageView img2 = (ImageView) scene.lookup(i.replace("_1", "_2"));
          img2.setOnMouseDragged(dragDropHandler.myHandlerDragged);
          img2.setOnDragOver(dragDropHandler.myHandlerOver);
          img2.setOnDragDropped(dragDropHandler.myHandlerDropped);
        }
        
  
    }
    
    void pointsUpdater(int newPoints) {

        userPoints += newPoints;
        if (userPoints < 0) {
            userPoints = 0;
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("UserPoints.txt"))) {
            bufferedWriter.write(String.valueOf(userPoints));
        } catch (IOException e) {
            // Exception handling
        }

    }
    
}
