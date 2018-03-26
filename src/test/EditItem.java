
package test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class EditItem {
    
    static EditWindowController editHandler;
    FXMLLoader loader;
    
    EditItem(int type, String uuid){
        Item.setCurrentElement(type, uuid);
        showEditWindow(type);
        editHandler.dispCurrentValues();
    }
    
    void showEditWindow(int type){
        Stage editStage = new Stage();
        Parent root = null;
        switch(type){
            case 1:
                loader = new FXMLLoader(getClass().getResource("EditForLoopWindow.fxml"));
                editStage.setMinWidth(400);
                editStage.setMinHeight(350);
                break;
            case 2:
                loader = new FXMLLoader(getClass().getResource("EditIfStatementWindow.fxml"));
                editStage.setMinWidth(600);
                editStage.setMinHeight(180);
                break;
            case 3:
                loader = new FXMLLoader(getClass().getResource("EditWhileLoopWindow.fxml"));
                editStage.setMinWidth(600);
                editStage.setMinHeight(180);
                break;
            case 4:
                loader = new FXMLLoader(getClass().getResource("EditDoLoopWindow.fxml"));
                editStage.setMinWidth(600);
                editStage.setMinHeight(180);
                break; 
            case 5:
                loader = new FXMLLoader(getClass().getResource("EditDeclareVariableWindow.fxml"));
                editStage.setMinWidth(400);
                editStage.setMinHeight(300);
                break;  
            case 6:
                loader = new FXMLLoader(getClass().getResource("EditAssignValueWindow.fxml"));
                editStage.setMinWidth(400);
                editStage.setMinHeight(250);
                break;    
            case 7:
                loader = new FXMLLoader(getClass().getResource("EditInputValueWindow.fxml"));
                editStage.setMinWidth(400);
                editStage.setMinHeight(170);
                break;     
            case 8:
                loader = new FXMLLoader(getClass().getResource("EditOutputExpressionWindow.fxml"));
                editStage.setMinWidth(600);
                editStage.setMinHeight(180);
                break;      
            case 9:
                loader = new FXMLLoader(getClass().getResource("EditCallMethodWindow.fxml"));
                editStage.setMinWidth(600);
                editStage.setMinHeight(180);
                break;     
            case 10:
                loader = new FXMLLoader(getClass().getResource("EditCommentWindow.fxml"));
                editStage.setMinWidth(600);
                editStage.setMinHeight(180);
                break;           
            default:
                break;
        }        
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ForLoop.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("MainCSS.css").toExternalForm());    
        editStage.setScene(scene);
        editStage.show();
        editHandler = (EditWindowController)loader.getController();
    }
}
