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
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.TranslateTransition;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hudaalandijani
 */
public class GameـmapController implements Initializable {
    @FXML
    private ImageView cloud_Harm2;
    
    @FXML
    private ImageView cloud_Harm1;
        @FXML
    private ImageView cloud_Mena1;

    @FXML
    private ImageView cloud_Mena2;
    
    @FXML
    private ImageView closed_lock;

    @FXML
    private ImageView open_lock;

    
     private Parent root;
     private Scene scene;
     private Stage stage;
     public static String userLevel;
     public static String currentLevel;
     private Scene homeScene;
     private static MediaPlayer mediaPlayer;
     @FXML
    private Text WarningText;
     
     //Receive data from start scene
    public void userData(String userData) {
        userLevel = userData;
        
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("UserLevel.txt"))) {
                    bufferedWriter.write(userLevel);
                } catch (IOException e) {
                    // Exception handling
                }
    }
    
    @FXML
    private void handleAlharamButton(ActionEvent event) throws IOException {
        currentLevel = "haram";
        root = FXMLLoader.load(getClass().getResource("Alharam.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        AlharamController alharam = new AlharamController();
        alharam.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleMenaButton(ActionEvent event) throws IOException {
        currentLevel = "mena";
        if(userLevel.equalsIgnoreCase("1")){
        WarningText.setText("عذرًا يجب عليك اولًا الانتهاء من مرحلة الحرم المكي حتى تستطيع إكمال الطريق إلى منى");
        } else {
        root = FXMLLoader.load(getClass().getResource("Mena.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        MenaController mena = new MenaController();
        mena.setScene(scene);
        stage.setScene(scene);
        stage.show();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Create file for user level
        File userLevelFile = new File("UserLevel.txt");

        
        try {

            // If the user is new
            if (userLevelFile.createNewFile()) {

                // Initialize the new user's level in the UserLevel file
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("UserLevel.txt"))) {
                    String fileContent = "1";
                    userLevel = fileContent;
                    bufferedWriter.write(fileContent);
                } catch (IOException e) {
                    // Exception handling
                }

                // If the user already has a file
            } else {

                // Read the stored level of the user
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader("UserLevel.txt"))) {
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        userLevel = line;
                        line = bufferedReader.readLine();
                    }
                } catch (FileNotFoundException e) {
                    // Exception handling
                } catch (IOException e) {
                    // Exception handling
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        // Create file for user level
        File userPointFile = new File("UserPoints.txt");

        try {

            // If the user is new
            if (userLevelFile.createNewFile()) {

                // Initialize the new user's level in the UserLevel file
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("UserPoints.txt"))) {
                    String fileContent = "0";
                    bufferedWriter.write(fileContent);
                } catch (IOException e) {
                    // Exception handling
                }

              
            } 
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        if(userLevel.equalsIgnoreCase("1")){
        Media sound = new Media(new File("instructionInMap.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        }
        
         Timer timer = new Timer();
// اخذ رقم المستوى من الملف وتخزينه ك انتجر
        int levelNum = Integer.parseInt(userLevel);
        TimerTask level1CloudTask = new TimerTask() {
            
            @Override
            public void run() {
                
 
               TranslateTransition Transition_Harm1Cloud = new TranslateTransition();
                Transition_Harm1Cloud.setNode(cloud_Harm1);
                Transition_Harm1Cloud.setDuration(Duration.seconds(6.2));
                Transition_Harm1Cloud.setToX(680);
                Transition_Harm1Cloud.play();
                
                TranslateTransition Transition_Harm2Cloud = new TranslateTransition();
                Transition_Harm2Cloud.setNode(cloud_Harm2);
                Transition_Harm2Cloud.setDuration(Duration.seconds(6.2));
                Transition_Harm2Cloud.setToX(-680);
                Transition_Harm2Cloud.play();

                
                if(levelNum > 1){
                TranslateTransition Transition_Mena1Cloud = new TranslateTransition();
                Transition_Mena1Cloud.setNode(cloud_Mena1);
                Transition_Mena1Cloud.setDuration(Duration.seconds(6.2));
                Transition_Mena1Cloud.setToX(680);
                Transition_Mena1Cloud.play();
                
                TranslateTransition Transition_Mena2Cloud = new TranslateTransition();
                Transition_Mena2Cloud.setNode(cloud_Mena2);
                Transition_Mena2Cloud.setDuration(Duration.seconds(6.2));
                Transition_Mena2Cloud.setToX(-680);
                Transition_Mena2Cloud.play();
                closed_lock.setVisible(false);
                open_lock.setVisible(true);
                }

            }
        };
         if(levelNum == 1)
        timer.schedule(level1CloudTask, 10101);
         else if(levelNum >1) 
        timer.schedule(level1CloudTask, 1000);
       
         //////// اختفاء القفل
           TimerTask lockerTask = new TimerTask() {
            
            @Override
            public void run() {
            open_lock.setVisible(false);
            }
          };    
        timer.schedule(lockerTask, 4800);
        
        
    }
   public void setScene(Scene scene) {

        this.homeScene = scene;
   }    
    
}
