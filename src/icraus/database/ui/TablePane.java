/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database.ui;

import icraus.Components.Component;
import icraus.Components.DraggableComponent;
import icraus.Components.Selectable;
import icraus.database.DatabaseColumnComponent;
import icraus.database.DatabaseTableComponent;
import static ide.UiManager.GUI_QUALIFIER;
import java.io.IOException;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Shoka
 */
public class TablePane extends BorderPane implements Selectable, DraggableComponent{
    @FXML
    private Label tableNameLabel;
    @FXML
    private VBox tableColumnsNameVBox;
    @FXML
    private VBox tableColumnsTypeVBox;
    
    @FXML
    private VBox tablePropertiesVBox;
    private DatabaseTableComponent parent;
    public TablePane(DatabaseTableComponent comp) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TablePane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        parent = comp;
        setId(GUI_QUALIFIER + parent.getUUID());
        tableNameLabel.textProperty().bind(parent.tableNameProperty());
        parent.getChildern().addListener((Observable e)-> {
            columnsChanged();
        });
        
    }
    
    @FXML
    private void initialize() {
        
    }

    @Override
    public String getParentComponentUuid() {
        return parent.getUUID();
    }

    private void columnsChanged() {
        ObservableList<Component> childern = parent.getChildern();
        tableColumnsNameVBox.getChildren().clear();
        tablePropertiesVBox.getChildren().clear();
        tableColumnsTypeVBox.getChildren().clear();
        
        for(Component c : childern){
            DatabaseColumnComponent temp = (DatabaseColumnComponent) c;
            Label type = new Label(temp.getColumnType());
            Label properties = new Label(temp.getColumnProperties());
            Label name = new Label(temp.getColumnName());
            tableColumnsNameVBox.getChildren().add(name);
            tableColumnsTypeVBox.getChildren().add(type);
            tablePropertiesVBox.getChildren().add(properties);
        }
    }
}
