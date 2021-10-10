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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hudaalandijani
 */
public class StartController implements Initializable {

    private Parent root;
    private Scene scene;
    private Stage stage;
    private String userLevel = "0";
    private String userPoints = "0";

    /**
     * Navigate to the map
     */
    @FXML
    private void handleStartButton(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameـmap.fxml"));
        root = loader.load();

        //Get controller of game map scene
        GameـmapController mapController = loader.getController();
        //Pass the user data
        mapController.userData(userLevel);

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

        // Create file for user level
        File userLevelFile = new File("UserLevel.txt");

        try {

            // If the user is new
            if (userLevelFile.createNewFile()) {

                // Initialize the new user's level in the UserLevel file
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("UserLevel.txt"))) {
                    String fileContent = "0";
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
                    userPoints = fileContent;
                    bufferedWriter.write(fileContent);
                } catch (IOException e) {
                    // Exception handling
                }

                // If the user already has a file
            } else {

                // Read the stored level of the user
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader("UserPoints.txt"))) {
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        userPoints = line;
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

    }

}
