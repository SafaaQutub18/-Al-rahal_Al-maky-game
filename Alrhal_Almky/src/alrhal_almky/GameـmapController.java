/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alrhal_almky;

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
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hudaalandijani
 */
public class GameـmapController implements Initializable {
     private Parent root;
     private Scene scene;
     private Stage stage;
     private static String userLevel;
     
     @FXML
    private Text WarningText;
     
     //Receive data from start scene
    public void userData(String userData) {
        userLevel = userData;
    }
    
    @FXML
    private void handleAlharamButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Alharam.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleMenaButton(ActionEvent event) throws IOException {
        if(userLevel.equalsIgnoreCase("0")){
        WarningText.setText("عذرًا يجب عليك اولًا الانتهاء من مرحلة الحرم المكي حتى تستطيع إكمال الطريق إلى منى");
        } else {
        root = FXMLLoader.load(getClass().getResource("Mena.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
