/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database.ui;

import com.sun.javafx.collections.ObservableListWrapper;
import icraus.Components.Component;
import icraus.Components.ComponentsModel;
import icraus.database.DatabaseColumnComponent;
import icraus.database.DatabaseTableComponent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

/**
 *
 * @author Shoka
 */
public class InsertDialog extends BorderPane {

    @FXML
    public Button okButton;
    @FXML
    public Button cancelButton;
    @FXML
    protected ComboBox<DatabaseTableComponent> databaseTablesComboBox;
    @FXML
    protected VBox columnsVBox;
    private final ComponentsModel model;
    protected Map<StringProperty, StringProperty> map = new HashMap<>();
    private List<StringProperty> values = new ArrayList<>();
    private List<StringProperty> columns = new ArrayList<>();
    public InsertDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InsertDialog.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        model = ComponentsModel.getInstance();
        fillComboBox();
        databaseTablesComboBox.valueProperty().addListener(e -> {
            currentItemChanged();
        });
    }
    public List<String> getColumns(){
        List<String> cols = new ArrayList<>();
        for(StringProperty s : this.columns){
            cols.add(s.getValue());
        }
        return cols;
    }
    public String getTableName(){
        return databaseTablesComboBox.getValue().getTableName();
    }
    
    public List<String> getValues(){
        List<String> values = new ArrayList<>();
        for(StringProperty s : this.values){
            values.add(s.getValue());
        }
        return values;
    }
    
    protected void fillComboBox() {
        ObservableList<DatabaseTableComponent> columnsList = new ObservableListWrapper<>(new ArrayList<>());
        List<Component> temp = model.getComponentsByType(DatabaseTableComponent.DATABAASE_TABLE_TYPE);
        for (Component c : temp) {
            columnsList.add((DatabaseTableComponent) c);
        }
        databaseTablesComboBox.setItems(columnsList);
    }


    protected void currentItemChanged() {
        DatabaseTableComponent value = databaseTablesComboBox.getValue();
        ObservableList<DatabaseColumnComponent> cols = value.getColumns();
        columnsVBox.getChildren().clear();
        map.clear();
        values.clear();
        columns.clear();
        for (DatabaseColumnComponent c : cols) {
            HBox h = databaseColumnMapper(c);
            columnsVBox.getChildren().add(h);
        }
    }

    protected HBox databaseColumnMapper(DatabaseColumnComponent c) {
        Label colName = new Label(c.getColumnName());
        Label colType = new Label(c.getColumnType());
        TextField colValue = new TextField();
        values.add(colValue.textProperty());
        columns.add(colName.textProperty());
        HBox h = new HBox(20, colName, colType, colValue);
        return h;
         
    }
}
