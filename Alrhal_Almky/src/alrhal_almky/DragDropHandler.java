/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alrhal_almky;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
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
    
    AlharamController ac = new AlharamController();
    MenaController mc = new MenaController();
    
    
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
    
    
         final EventHandler<DragEvent> myHandlerDropped = new EventHandler<DragEvent>() {
        
        @Override
        public void handle(DragEvent event) {
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
                
                ac.pointsUpdater(3);
                mc.pointsUpdater(3);
                //counter ++
                ac.helpHashMap.replace(source.getId(), true);
                mc.helpHashMap.replace(source.getId(), true);
                
            } else {
                event.setDropCompleted(false);
                
                ac.pointsUpdater(-3);
                ac.hearts += 1; 
                
                mc.pointsUpdater(-3);
                mc.hearts += 1; 
                // heart.isheddin == false
                

                // call loss interface(interface name)
            }
           // }
            event.consume();
            
             if(ac.hearts == 3){ 
             //  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              // stage.close();
                System.out.println("Call loss function");
                
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
                 
                 }
        }

    };
    
          final EventHandler<MouseEvent> circleOnMousePressedEventHandler = 
        new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
                
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((ImageView)(t.getSource())).getTranslateX();
            orgTranslateY = ((ImageView)(t.getSource())).getTranslateY();
        }
    };
    
    final EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = 
        new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            t.setDragDetect(true);
            
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            
            ((ImageView)(t.getSource())).setTranslateX(newTranslateX);
            ((ImageView)(t.getSource())).setTranslateY(newTranslateY);
        }
    };
   
    public void won(){
        //hgyjfyhfv
    }
}
