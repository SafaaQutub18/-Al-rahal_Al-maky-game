/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alrhal_almky;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import javafx.scene.text.Text;
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
    @FXML
    private ImageView tears;

    public static int hearts = 0;

    private static int userPoints = 0;

    ///////////////////////////////////
    @FXML
    private void handleMaptButton(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("gameـmap.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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
                    // الدمعة
                    tears.setVisible(true);
                TranslateTransition Transition_tears = new TranslateTransition();
                Transition_tears.setNode(tears);
                Transition_tears.setDuration(Duration.seconds(1.5));
                Transition_tears.setToY(200);
                Transition_tears.setToX(0);
                Transition_tears.setCycleCount(-1);
               //Transition_tears.setAutoReverse(true);
                Transition_tears.play();
                    
                    
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
                Transition_science.setToY(680);
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
                Transition_bird.setDuration(Duration.seconds(7.2));
                Transition_bird.setToX(1090);
                Transition_bird.play();

                //bird2
                TranslateTransition Transition_bird2 = new TranslateTransition();
                Transition_bird2.setNode(bird2);
                Transition_bird2.setDuration(Duration.seconds(5.2));
                Transition_bird2.setToX(1090);
                Transition_bird2.play();

            }
        };
        timer.schedule(birdtask, 10490);

    }

    public void setScene(Scene scene) {

        this.alharamScene = scene;

        //ArrayList<ImageView> imgs = new ArrayList<>();
        String[] imgs = {"#kaaba_1", "#makam_1", "#sahn_1", "#zamzam_1", "#methna_1", "#taef_1"};
        //String[] imgs_target = {"kaaba_2"};

        DragDropHandler dragDropHandler = new DragDropHandler();

        for (String i : imgs) {
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
