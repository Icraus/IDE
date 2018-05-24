/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database;

import icraus.Components.DraggableComponent;
import javafx.scene.control.Button;

/**
 *
 * @author Shoka
 */
public class ConnectDatabaseButton extends Button implements DraggableComponent{

    public ConnectDatabaseButton() {
    }

//    ConnectDatabaseButton(ConnectDatabaseComponent aThis) {
//        setOnAction(e -> {
//            aThis.getTab().getTabPane().getSelectionModel().select(aThis.getTab());
//        });
//        ContextMenu m = new ContextMenu();
//        MenuItem mi = new MenuItem("Edit Connection");
//        m.getItems().add(mi);
//        mi.setOnAction(e ->{
//            Stage s = new Stage();
//            ConnectDatabaseUi ui = new ConnectDatabaseUi();
//            Scene sc = new Scene(ui);
//            s.setScene(sc);
//            ui.okButton.setOnAction(v ->{
//                aTHisdatabaseName.setValue(ui.databaseNameTextField.getText());
//                databaseUserName.setValue(ui.uesrNameTextField.getText());
//                databasePassword.setValue(ui.passwordTextField.getText());
//                databaseUrl.setValue(ui.hostNameTextField.getText());
//                s.close();
//            });
//            ui.cancelButton.setOnAction(v ->{
//                s.close();
//            });
//            s.showAndWait();
//        });    }
    
}
