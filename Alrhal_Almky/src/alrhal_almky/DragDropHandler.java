/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alrhal_almky;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author argha
 */
public class DragDropHandler {

    ImageView source = new ImageView();
    ImageView target = new ImageView();

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    private double anchorX;
    private double anchorY;
    private double mouseOffsetFromNodeZeroX;
    private double mouseOffsetFromNodeZeroY;
    private static MediaPlayer mediaPlayer;

    AlharamController ac = new AlharamController();
    MenaController mc = new MenaController();

    GameـmapController mapController = new GameـmapController();
    List<String> soundslist = new ArrayList<String>();
    Random rand = new Random();

    final EventHandler<MouseEvent> myHandlerDetected = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            ////    System.out.println("DETECTED");
            source = (ImageView) event.getSource();

            System.out.println("img1 drag detected");

            //System.out.println(source.getId());
            Dragboard db = source.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putImage(source.getImage());
            db.setContent(content);
            event.consume();
        }

    };

    final EventHandler<MouseEvent> myHandlerDragged = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("img 2 dragged");
            event.setDragDetect(true);
        }
    };

    final EventHandler<DragEvent> myHandlerOver = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {
            target = (ImageView) event.getSource();
            //System.out.println("img1 drag over");

            System.out.println("img2.setOnDragOver");
            if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
            event.consume();

        }
    };

    public ArrayList<String> arr = new ArrayList<String>();
    final EventHandler<DragEvent> myHandlerDropped = new EventHandler<DragEvent>() {

        @Override
        public void handle(DragEvent event) {

            soundslist.add("great.mp3");
            soundslist.add("amazing.mp3");
            soundslist.add("good.mp3");
            soundslist.add("excellent.mp3");

            System.out.println("In drag dropped, before if");

            String source_id = source.getId().split("_")[0];

            String target_id = target.getId().split("_")[0];

            Dragboard db = event.getDragboard();
            if (source_id.equalsIgnoreCase(target_id)) {
                System.out.println("Dropped: " + db.getString());
                event.setDropCompleted(true);
                target.setImage(source.getImage());
                target.setOpacity(1);
                source.setImage(null);

                if (mapController.currentLevel.equalsIgnoreCase("haram")) {
                    ac.pointsUpdater(3);
                    ac.helpHashMap.replace(source.getId(), true);

                } else if (mapController.currentLevel.equalsIgnoreCase("mena")) {
                    mc.pointsUpdater(3);
                    mc.helpHashMap.replace(source.getId(), true);
                }

                System.out.println("Win interface" + source_id);
                arr.add(source_id);
                System.out.println(arr.size());

                if (ac.userPoints > 3 || mc.userPoints > 3) {
                    int upperbound = 5;
                    int int_random = rand.nextInt(upperbound);
                    Media sound = new Media(new File(soundslist.get(int_random)).toURI().toString());
                    mediaPlayer = new MediaPlayer(sound);
                    mediaPlayer.play();
                }
            } else {
                event.setDropCompleted(false);

                if (mapController.currentLevel.equalsIgnoreCase("haram")) {
                    ac.pointsUpdater(-3);
                    ac.hearts += 1;

                } else if (mapController.currentLevel.equalsIgnoreCase("mena")) {
                    mc.pointsUpdater(-3);
                    mc.hearts += 1;
                }

            }
            // }
            event.consume();

            if (ac.hearts == 3) {

                //  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                // stage.close();
                /*    System.out.println("Call loss function");
                
                System.out.println("You loss :(!");
            
            Stage lossStage = null; 
            Parent lossRoot = null;
            Scene sceneLoss = null;
               try {
            lossStage = new Stage();
            FXMLLoader lossLoader = new FXMLLoader();
            lossLoader.setLocation(getClass().getResource("Loss_interface.fxml"));
            lossRoot = lossLoader.load();
            sceneLoss = new Scene(lossRoot);
            sceneLoss.setFill(Color.TRANSPARENT);
            
            lossStage.setScene(sceneLoss);
            lossStage.initStyle(StageStyle.UNDECORATED);
            lossStage.initStyle(StageStyle.TRANSPARENT);
            lossStage.initModality(Modality.APPLICATION_MODAL);
            lossStage.setResizable(false);
            lossStage.showAndWait();
            
            
           //Stage stage = (Stage) .getScene().getWindow();
            //stage.initStyle(StageStyle.DECORATED);
            //stage.close();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
               stage.close();
            
               } catch (IOException ex) {
                   Logger.getLogger(Loss_interfaceController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
               }
                 */
            }
        }

    };

    final EventHandler<DragEvent> myHandlerDone = new EventHandler<DragEvent>() {

        @Override
        public void handle(DragEvent event) {
            event.consume();

            try {
                if (source.getId() != null && target.getId() != null) {
                    String source_id = source.getId().split("_")[0];

                    String target_id = target.getId().split("_")[0];

                    Dragboard db = event.getDragboard();
                    System.out.println("img 2 drag exited");
                    //  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    // stage.close();
                    if (ac.hearts == 3) {
                        //mc.hearts=0;

                        //To play loss sound
                        if (mapController.currentLevel.equalsIgnoreCase("haram")) {
                            Media sound = new Media(new File("lossFirstLevel.mp3").toURI().toString());
                            mediaPlayer = new MediaPlayer(sound);
                            mediaPlayer.play();
                        }
                        //else if (mapController.currentLevel.equalsIgnoreCase("mena")){
                        //Media sound = new Media(new File("lossSecondLevel.mp3").toURI().toString());
                        //MediaPlayer mediaPlayer = new MediaPlayer(sound);
                        //mediaPlayer.play();
                        //}

                        System.out.println("Call loss function");

                        System.out.println("You loss :(!");

                        Stage lossStage = null;
                        Parent lossRoot = null;
                        Scene sceneLoss = null;

                        lossStage = new Stage();
                        FXMLLoader lossLoader = new FXMLLoader();
                        lossLoader.setLocation(getClass().getResource("Loss_interface.fxml"));
                        lossRoot = lossLoader.load();
                        sceneLoss = new Scene(lossRoot);
                        sceneLoss.setFill(Color.TRANSPARENT);

                        lossStage.setScene(sceneLoss);
                        lossStage.initStyle(StageStyle.UNDECORATED);
                        lossStage.initStyle(StageStyle.TRANSPARENT);
                        lossStage.initModality(Modality.APPLICATION_MODAL);
                        lossStage.setResizable(false);
                        lossStage.showAndWait();

                        //Stage stage = (Stage) .getScene().getWindow();
                        //stage.initStyle(StageStyle.DECORATED);
                        //stage.close();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();
                        ac.hearts = 0;

                    }
                    if (mc.hearts == 3) {

                        if (mapController.currentLevel.equalsIgnoreCase("mena")) {
                            Media sound = new Media(new File("lossSecondLevel.mp3").toURI().toString());
                            mediaPlayer = new MediaPlayer(sound);
                            mediaPlayer.play();
                        }

                        System.out.println("Call Mena loss function");

                        System.out.println("You loss :(!");

                        Stage lossStage = null;
                        Parent lossRoot = null;
                        Scene sceneLoss = null;

                        lossStage = new Stage();
                        FXMLLoader lossLoader = new FXMLLoader();
                        lossLoader.setLocation(getClass().getResource("Mena_loss.fxml"));
                        lossRoot = lossLoader.load();
                        sceneLoss = new Scene(lossRoot);
                        sceneLoss.setFill(Color.TRANSPARENT);

                        lossStage.setScene(sceneLoss);
                        lossStage.initStyle(StageStyle.UNDECORATED);
                        lossStage.initStyle(StageStyle.TRANSPARENT);
                        lossStage.initModality(Modality.APPLICATION_MODAL);
                        lossStage.setResizable(false);
                        lossStage.showAndWait();

                        //Stage stage = (Stage) .getScene().getWindow();
                        //stage.initStyle(StageStyle.DECORATED);
                        //stage.close();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();
                        mc.hearts = 0;

                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Mena_lossController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            if (arr.size() == 6) {
                // Update user level
                mapController.userData("2");
                //To play win sound
                if (mapController.currentLevel.equalsIgnoreCase("haram")) {

                    Timer timer = new Timer();
                    TimerTask winSoundTask = new TimerTask() {
                        @Override
                        public void run() {
                            Media sound = new Media(new File("winFirstLevel.mp3").toURI().toString());
                            mediaPlayer = new MediaPlayer(sound);
                            mediaPlayer.play();
                        }
                    };
                    timer.schedule(winSoundTask, 2000);

                    System.out.println("CAAAAAAL Win interface");
                    System.out.println("You win :D!");

                    Stage WinStage = null;
                    Parent WinRoot = null;
                    Scene sceneLoss = null;
                    try {
                        WinStage = new Stage();
                        FXMLLoader Winloader = new FXMLLoader();
                        Winloader.setLocation(getClass().getResource("Win_interface.fxml"));
                        WinRoot = Winloader.load();
                        sceneLoss = new Scene(WinRoot);
                        sceneLoss.setFill(Color.TRANSPARENT);

                        WinStage.setScene(sceneLoss);
                        WinStage.initStyle(StageStyle.UNDECORATED);
                        WinStage.initStyle(StageStyle.TRANSPARENT);
                        WinStage.initModality(Modality.APPLICATION_MODAL);
                        WinStage.setResizable(false);
                        WinStage.showAndWait();

                        //Stage stage = (Stage) .getScene().getWindow();
                        //stage.initStyle(StageStyle.DECORATED);
                        //stage.close();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();

                    } catch (IOException ex) {
                        Logger.getLogger(Win_interfaceController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                
                
            }
            if (arr.size() == 7) {
                // Update user level
                mapController.userData("3");
                // To play win sound
                if (mapController.currentLevel.equalsIgnoreCase("mena")) {
                    Timer timer = new Timer();
                    TimerTask winSoundTask = new TimerTask() {
                        @Override
                        public void run() {
                            Media sound = new Media(new File("winSecondLevel.mp3").toURI().toString());
                            mediaPlayer = new MediaPlayer(sound);
                            mediaPlayer.play();
                        }
                    };
                    timer.schedule(winSoundTask, 2000);

                    System.out.println("CAAAAAAL Mena Win interface");
                    System.out.println("You win :D!");

                    Stage WinStage = null;
                    Parent WinRoot = null;
                    Scene sceneLoss = null;
                    try {
                        WinStage = new Stage();
                        FXMLLoader Winloader = new FXMLLoader();
                        Winloader.setLocation(getClass().getResource("Mena_win.fxml"));
                        WinRoot = Winloader.load();
                        sceneLoss = new Scene(WinRoot);
                        sceneLoss.setFill(Color.TRANSPARENT);

                        WinStage.setScene(sceneLoss);
                        WinStage.initStyle(StageStyle.UNDECORATED);
                        WinStage.initStyle(StageStyle.TRANSPARENT);
                        WinStage.initModality(Modality.APPLICATION_MODAL);
                        WinStage.setResizable(false);
                        WinStage.showAndWait();

                        //Stage stage = (Stage) .getScene().getWindow();
                        //stage.initStyle(StageStyle.DECORATED);
                        //stage.close();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();

                    } catch (IOException ex) {
                        Logger.getLogger(Mena_winController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

            }
        }

    };

    final EventHandler<MouseEvent> circleOnMousePressedEventHandler
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((ImageView) (t.getSource())).getTranslateX();
            orgTranslateY = ((ImageView) (t.getSource())).getTranslateY();
        }
    };

    final EventHandler<MouseEvent> circleOnMouseDraggedEventHandler
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            t.setDragDetect(true);

            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            ((ImageView) (t.getSource())).setTranslateX(newTranslateX);
            ((ImageView) (t.getSource())).setTranslateY(newTranslateY);
        }
    };

   
}
