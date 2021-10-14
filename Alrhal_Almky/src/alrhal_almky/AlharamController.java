/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alrhal_almky;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleAction;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    int test;

    ///////////////////////////////////
    @FXML
    private ImageView kaaba_1;

    @FXML
    private ImageView makam_1;

    @FXML
    private ImageView sahn_1;

    @FXML
    private ImageView zamzam_1;

    @FXML
    private ImageView methna_1;

    @FXML
    private ImageView taef_1;

    @FXML
    private ImageView kaaba_2;

    @FXML
    private ImageView sahn_2;

    @FXML
    private ImageView zamzam_2;

    @FXML
    private ImageView makam_2;

    @FXML
    private ImageView taef_2;

    @FXML
    private ImageView methna_2;
    @FXML
    private ImageView cursor;
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
    @FXML
    private Text helpPoints;

    public static int hearts = 0;

    public static int userPoints = 0;

    public static HashMap<String, Boolean> helpHashMap = new HashMap<String, Boolean>();
    
    private static MediaPlayer mediaPlayer;
    
    GameـmapController mapController = new GameـmapController();

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

        // This HashMap for help button to keep track of images not used
        helpHashMap.put("kaaba_1", false);
        helpHashMap.put("makam_1", false);
        helpHashMap.put("sahn_1", false);
        helpHashMap.put("zamzam_1", false);
        helpHashMap.put("methna_1", false);
        helpHashMap.put("taef_1", false);

        hearts = 0;

        // Read the user points from the userpints file
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

        // This timer to keep updating the user points and hearts int the interface continuously
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
                    test = 1;
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
                    //System.out.println("Loss Interface");         

                }

            }
        }, 0, 10);
        

        Timer timer = new Timer();

        TimerTask boytask = new TimerTask() {
            @Override
            public void run() {
                
if(mapController.userLevel.equalsIgnoreCase("1")){
                Media sound = new Media(new File("instructionInAlharam.mp3").toURI().toString());
                mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
}
                TranslateTransition Transition_science = new TranslateTransition();
                Transition_science.setNode(normal_boy);
                Transition_science.setDuration(Duration.seconds(1.2));
                Transition_science.setToY(680);
                Transition_science.play();
            }
        };
        timer.schedule(boytask, 1000l);

        if(mapController.userLevel.equalsIgnoreCase("1")){
        TimerTask instructionsTask = new TimerTask() {
            @Override
            public void run() {
                Polyline ployline = new Polyline();
                ployline.getPoints().addAll(new Double[]{
                    kaaba_1.getLayoutX() + 50, kaaba_1.getLayoutY() + 80,
                    kaaba_2.getLayoutX() + 60, kaaba_2.getLayoutY() + 100,});

                moveCursor(ployline);

            }   
        };
        timer.schedule(instructionsTask, 9500);

        TimerTask instructionsTask2 = new TimerTask() {
            @Override
            public void run() {
                int depth = 70;
                DropShadow borderGlow = new DropShadow();
                borderGlow.setOffsetY(0f);
                borderGlow.setOffsetX(0f);
                borderGlow.setColor(Color.RED);
                borderGlow.setWidth(depth);
                borderGlow.setHeight(depth);

                kaaba_2.setEffect(borderGlow);

                TimerTask disableGlowEffect = new TimerTask() {
                    @Override
                    public void run() {
                        int depth = 0;
                        DropShadow borderGlow = new DropShadow();
                        borderGlow.setOffsetY(0f);
                        borderGlow.setOffsetX(0f);
                        borderGlow.setColor(Color.TRANSPARENT);
                        borderGlow.setWidth(depth);
                        borderGlow.setHeight(depth);

                        kaaba_2.setEffect(borderGlow);

                    }
                };
                timer.schedule(disableGlowEffect, 9000);

            }
        };
        timer.schedule(instructionsTask2, 16000);
        }
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
            img1.setOnDragDone(dragDropHandler.myHandlerDone);

            ImageView img2 = (ImageView) scene.lookup(i.replace("_1", "_2"));
            img2.setOnMouseDragged(dragDropHandler.myHandlerDragged);
            img2.setOnDragOver(dragDropHandler.myHandlerOver);
            img2.setOnDragDropped(dragDropHandler.myHandlerDropped);

            // System.out.println("Hello there,.,.,.");
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

    // Each time the user earn points or loss points we call this function
    void pointsUpdater(int newPoints) {

        userPoints += newPoints;
        if (userPoints < 0) {
            userPoints = 0;
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("UserPoints.txt"))) {
            bufferedWriter.write(String.valueOf(userPoints));
        } catch (IOException e) {

        }

    }

    public void tempToInfo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AlharamInfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleHelpButton(ActionEvent event) throws IOException {

        Timer timer = new Timer();
        Polyline ployline = new Polyline();
        PathTransition pathTransition = new PathTransition();

        for (Map.Entry<String, Boolean> i : helpHashMap.entrySet()) {

            if (i.getValue() == false) {
                if ((userPoints - 5) >= 0) {
                    pointsUpdater(-5);

                    helpPoints.setVisible(true);

                    TranslateTransition Transition_helpPoints = new TranslateTransition();
                    Transition_helpPoints.setNode(helpPoints);
                    Transition_helpPoints.setDuration(Duration.seconds(1.2));
                    Transition_helpPoints.setToY(-70);
                    Transition_helpPoints.setCycleCount(1);
                    Transition_helpPoints.setAutoReverse(false);
                    Transition_helpPoints.play();

                    TimerTask minusPoints = new TimerTask() {
                        @Override
                        public void run() {
                            helpPoints.setVisible(false);
                            Transition_helpPoints.setToY(20);
                            Transition_helpPoints.play();
                        }
                    };
                    timer.schedule(minusPoints, 1500);
                    switch (i.getKey()) {
                        case "kaaba_1":
                            ployline = new Polyline();
                            ployline.getPoints().addAll(new Double[]{
                                kaaba_1.getLayoutX() + 50, kaaba_1.getLayoutY() + 80,
                                kaaba_2.getLayoutX() + 60, kaaba_2.getLayoutY() + 100,});

                             moveCursor(ployline);
                            break;
                        case "makam_1":
                            ployline = new Polyline();
                            ployline.getPoints().addAll(new Double[]{
                                makam_1.getLayoutX() + 50, makam_1.getLayoutY() + 80,
                                makam_2.getLayoutX() + 60, makam_2.getLayoutY() + 100,});

                              moveCursor(ployline);
                            break;
                        case "sahn_1":

                            ployline = new Polyline();
                            ployline.getPoints().addAll(new Double[]{
                                sahn_1.getLayoutX() + 50, sahn_1.getLayoutY() + 80,
                                sahn_2.getLayoutX() + 60, sahn_2.getLayoutY() + 100,});

                              moveCursor(ployline);

                            break;
                        case "zamzam_1":
                            ployline = new Polyline();
                            ployline.getPoints().addAll(new Double[]{
                                zamzam_1.getLayoutX() + 50, zamzam_1.getLayoutY() + 80,
                                zamzam_2.getLayoutX() + 60, zamzam_2.getLayoutY() + 100,});

                              moveCursor(ployline);

                            break;
                        case "methna_1":
                            ployline = new Polyline();
                            ployline.getPoints().addAll(new Double[]{
                                methna_1.getLayoutX() + 50, methna_1.getLayoutY() + 80,
                                methna_2.getLayoutX() + 60, methna_2.getLayoutY() + 100,});

                             moveCursor(ployline);
                            break;
                        case "taef_1":

                            ployline = new Polyline();
                            ployline.getPoints().addAll(new Double[]{
                                taef_1.getLayoutX() + 50, taef_1.getLayoutY() + 80,
                                taef_2.getLayoutX() + 60, taef_2.getLayoutY() + 100,});

                              moveCursor(ployline);
                            break;
                    }
                    break;
                }

            }
        }

    }
    
    private void moveCursor(Polyline ployline) {
         Timer timer = new Timer();
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.seconds(2));
                pathTransition.setNode(cursor);
                pathTransition.setPath(ployline);
                pathTransition.setCycleCount(-1);
                pathTransition.setAutoReverse(false);
                pathTransition.play();
                cursor.setVisible(true);

                TimerTask cursortask = new TimerTask() {
                    @Override
                    public void run() {
                        cursor.setVisible(false);

                    }
                };
                timer.schedule(cursortask, 4000);
            }

}
