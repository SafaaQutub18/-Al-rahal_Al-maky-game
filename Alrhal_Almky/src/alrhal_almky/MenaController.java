/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alrhal_almky;

import static alrhal_almky.AlharamController.hearts;
import static alrhal_almky.AlharamController.helpHashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.PathTransition;
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
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
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
    private ImageView rami_1;

    @FXML
    private ImageView hajar_1;

    @FXML
    private ImageView haj_1;

    @FXML
    private ImageView soghra_1;

    @FXML
    private ImageView wosta_1;

    @FXML
    private ImageView khema_1;

    @FXML
    private ImageView kobra_1;

    @FXML
    private ImageView khema_2;

    @FXML
    private ImageView soghra_2;

    @FXML
    private ImageView haj_2;

    @FXML
    private ImageView kobra_2;

    @FXML
    private ImageView rami_2;

    @FXML
    private ImageView wosta_2;
    @FXML
    private ImageView hajar_2;

    @FXML
    private Text helpPoints;

    @FXML
    private ImageView cursor;

    @FXML
    private ImageView shootingStar;
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

    public static HashMap<String, Boolean> helpHashMap = new HashMap<String, Boolean>();

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

        // This HashMap for help button to keep track of images not used
        helpHashMap.put("khema_1", false);
        helpHashMap.put("kobra_1", false);
        helpHashMap.put("wosta_1", false);
        helpHashMap.put("soghra_1", false);
        helpHashMap.put("haj_1", false);
        helpHashMap.put("hajar_1", false);
        helpHashMap.put("rami_1", false);

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
                Transition_science.setToY(680);
                Transition_science.play();
            }
        };
        timer.schedule(boytask, 1000l);
        // حركة الشهاب
        TimerTask shotingStarTask = new TimerTask() {
            @Override
            public void run() {
                shootingStar.setVisible(true);

                Path path = new Path();
                MoveTo moveTo = new MoveTo(100, 10);
                // LineTo line1 = new LineTo(490, 791);
                LineTo line1 = new LineTo(-490, 200);
                // path.setRotate( 30);
                path.getElements().add(moveTo);
                path.getElements().addAll(line1);
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(1000));
                pathTransition.setNode(shootingStar);
                pathTransition.setPath(path);
                pathTransition.setOrientation(PathTransition.OrientationType.NONE);
                pathTransition.setCycleCount(1);
                pathTransition.setAutoReverse(false);
                pathTransition.play();
            }
        };
        timer.schedule(shotingStarTask, 10011);

        // اختفاء الشهاب
        TimerTask shotingStarHidden = new TimerTask() {
            @Override
            public void run() {
                shootingStar.setVisible(false);
            }
        };
        timer.schedule(shotingStarHidden, 11001);
    }

    public void setScene(Scene scene) {

        this.menaScene = scene;

        String[] imgs = {"#khema_1", "#kobra_1", "#wosta_1", "#soghra_1", "#haj_1", "#hajar_1", "#rami_1"};

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
                        case "khema_1":
                            ployline = new Polyline();
                            ployline.getPoints().addAll(new Double[]{
                                khema_1.getLayoutX() + 50, khema_1.getLayoutY() + 80,
                                khema_2.getLayoutX() + 60, khema_2.getLayoutY() + 100,});

                            pathTransition = new PathTransition();
                            pathTransition.setDuration(Duration.seconds(2));
                            pathTransition.setNode(cursor);
                            pathTransition.setPath(ployline);
                            pathTransition.setCycleCount(-1);
                            pathTransition.setAutoReverse(false);
                            pathTransition.play();
                            cursor.setVisible(true);

                            TimerTask cursortask_khema = new TimerTask() {
                                @Override
                                public void run() {
                                    cursor.setVisible(false);
                                }
                            };
                            timer.schedule(cursortask_khema, 4000);
                            break;
                        case "kobra_1":
                            ployline = new Polyline();
                            ployline.getPoints().addAll(new Double[]{
                                kobra_1.getLayoutX() + 50, kobra_1.getLayoutY() + 80,
                                kobra_2.getLayoutX() + 80, kobra_2.getLayoutY() + 100,});

                            pathTransition = new PathTransition();
                            pathTransition.setDuration(Duration.seconds(2));
                            pathTransition.setNode(cursor);
                            pathTransition.setPath(ployline);
                            pathTransition.setCycleCount(-1);
                            pathTransition.setAutoReverse(false);
                            pathTransition.play();
                            cursor.setVisible(true);

                            TimerTask cursortask_kobra = new TimerTask() {
                                @Override
                                public void run() {
                                    cursor.setVisible(false);
                                }
                            };
                            timer.schedule(cursortask_kobra, 4000);
                            break;
                        case "wosta_1":

                            ployline = new Polyline();
                            ployline.getPoints().addAll(new Double[]{
                                wosta_1.getLayoutX() + 50, wosta_1.getLayoutY() + 80,
                                wosta_2.getLayoutX() + 80, wosta_2.getLayoutY() + 100,});

                            pathTransition = new PathTransition();
                            pathTransition.setDuration(Duration.seconds(2));
                            pathTransition.setNode(cursor);
                            pathTransition.setPath(ployline);
                            pathTransition.setCycleCount(2);
                            pathTransition.setAutoReverse(false);
                            pathTransition.play();
                            cursor.setVisible(true);

                            TimerTask cursortask_wosta = new TimerTask() {
                                @Override
                                public void run() {
                                    cursor.setVisible(false);
                                }
                            };
                            timer.schedule(cursortask_wosta, 4000);

                            break;
                        case "soghra_1":
                            ployline = new Polyline();
                            ployline.getPoints().addAll(new Double[]{
                                soghra_1.getLayoutX() + 50, soghra_1.getLayoutY() + 80,
                                soghra_2.getLayoutX() + 80, soghra_2.getLayoutY() + 100,});

                            pathTransition = new PathTransition();
                            pathTransition.setDuration(Duration.seconds(2));
                            pathTransition.setNode(cursor);
                            pathTransition.setPath(ployline);
                            pathTransition.setCycleCount(-1);
                            pathTransition.setAutoReverse(false);
                            pathTransition.play();
                            cursor.setVisible(true);

                            TimerTask cursortask_soghra = new TimerTask() {
                                @Override
                                public void run() {
                                    cursor.setVisible(false);

                                }
                            };
                            timer.schedule(cursortask_soghra, 4000);

                            break;
                        case "haj_1":
                            ployline = new Polyline();
                            ployline.getPoints().addAll(new Double[]{
                                haj_1.getLayoutX() + 50, haj_1.getLayoutY() + 80,
                                haj_2.getLayoutX() + 60, haj_2.getLayoutY() + 80,});

                            pathTransition = new PathTransition();
                            pathTransition.setDuration(Duration.seconds(2));
                            pathTransition.setNode(cursor);
                            pathTransition.setPath(ployline);
                            pathTransition.setCycleCount(-1);
                            pathTransition.setAutoReverse(false);
                            pathTransition.play();
                            cursor.setVisible(true);

                            TimerTask cursortask_haj = new TimerTask() {
                                @Override
                                public void run() {
                                    cursor.setVisible(false);
                                }
                            };
                            timer.schedule(cursortask_haj, 4000);
                            break;
                        case "hajar_1":

                            ployline = new Polyline();
                            ployline.getPoints().addAll(new Double[]{
                                hajar_1.getLayoutX() + 50, hajar_1.getLayoutY() + 80,
                                hajar_2.getLayoutX() + 10, hajar_2.getLayoutY() + 30,});

                            pathTransition = new PathTransition();
                            pathTransition.setDuration(Duration.seconds(2));
                            pathTransition.setNode(cursor);
                            pathTransition.setPath(ployline);
                            pathTransition.setCycleCount(-1);
                            pathTransition.setAutoReverse(false);
                            pathTransition.play();
                            cursor.setVisible(true);

                            TimerTask cursortask_hajar = new TimerTask() {
                                @Override
                                public void run() {
                                    cursor.setVisible(false);
                                }
                            };
                            timer.schedule(cursortask_hajar, 4000);
                            break;
                        case "rami_1":

                            ployline = new Polyline();
                            ployline.getPoints().addAll(new Double[]{
                                rami_1.getLayoutX() + 50, rami_1.getLayoutY() + 80,
                                rami_2.getLayoutX() + 60, rami_2.getLayoutY() + 100,});

                            pathTransition = new PathTransition();
                            pathTransition.setDuration(Duration.seconds(2));
                            pathTransition.setNode(cursor);
                            pathTransition.setPath(ployline);
                            pathTransition.setCycleCount(-1);
                            pathTransition.setAutoReverse(false);
                            pathTransition.play();
                            cursor.setVisible(true);

                            TimerTask cursortask_rami = new TimerTask() {
                                @Override
                                public void run() {
                                    cursor.setVisible(false);
                                }
                            };
                            timer.schedule(cursortask_rami, 4000);
                            break;
                    }

                }

            }
        }

    }

}
