/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database.ui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Shoka
 */
public class ConnectDatabaseUi extends BorderPane {
    @FXML
    public TextField hostNameTextField;
    @FXML
    public TextField databaseNameTextField;
    @FXML
    public TextField uesrNameTextField;
    @FXML
    public TextField passwordTextField;
    @FXML
    public Button okButton;
    @FXML
    public Button cancelButton;
    
    public ConnectDatabaseUi() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConnectDatabaseUi.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
    }
    
    @FXML
    private void initialize() {
    }
}
