/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alrhal_almky;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

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
            event.setDragDetect(true);

        }
    };
        
        
    final EventHandler<DragEvent> myHandlerOver = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {
            target = (ImageView) event.getSource();
             System.out.println("img1 drag over");
   
            
            System.out.println("img2.setOnDragOver");
                if (event.getGestureSource() != target && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();

        }
    };
    
    
         final EventHandler<DragEvent> myHandlerDropped = new EventHandler<DragEvent>() {
        
        @Override
        public void handle(DragEvent event) {
            
            String source_id = source.getId().split("_")[0];
            
            String target_id = target.getId().split("_")[0];
            
            Dragboard db = event.getDragboard();
            if (db.hasString() && source_id.equalsIgnoreCase(target_id)) {
                System.out.println("Dropped: " + db.getString());
                event.setDropCompleted(true);
                target.setImage(source.getImage());
                target.setOpacity(1);
                source.setImage(null);
                
                //counter ++
                
            } else {
                event.setDropCompleted(false);
                //
                // heart.isheddin == false
                
                // if heart == 0 
                // call loss interface(interface name)
                
            }
            event.consume();
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
