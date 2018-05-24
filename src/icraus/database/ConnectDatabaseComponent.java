/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database;

import com.sun.javafx.collections.ObservableListWrapper;
import icraus.Components.Component;
import icraus.Components.IllegalComponent;
import icraus.Components.Pageable;
import icraus.Components.ScrollAnchorPane;
import static icraus.database.DatabaseTableComponent.DATABAASE_TABLE_TYPE;
import icraus.database.ui.ConnectDatabaseUi;
import java.util.ArrayList;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

/**
 *
 * @author Shoka
 */
public class ConnectDatabaseComponent extends Component implements Pageable {

    public static final String DATABAASE_CREATE_TYPE = "DATABAASE_CREATE";
    private StringProperty databaseName;
    private StringProperty databasePassword;
    private StringProperty databaseUserName;
    private StringProperty databaseUrl;
    private Tab tab;

    public ConnectDatabaseComponent() {
        super();
        this.tab = new Tab();
        this.databaseUrl = new SimpleStringProperty("");
        this.databaseUserName = new SimpleStringProperty("");
        this.databasePassword = new SimpleStringProperty("");
        this.databaseName = new SimpleStringProperty("");
        this.tab.setContent(new ScrollAnchorPane(this));
        this.tab.setText("Connect Database");
        createUiDelegate();
        createStatment();
    }

    private void createStatment() {
        SimpleListStatement stmnt = new SimpleListStatement("Hello World");//TODO change to statment 
        
        getChildern().addListener((Observable e) -> {
            stmnt.getChildern().clear();
            ObservableList<Component> ch = getChildern();
            for(Component c : ch){
                stmnt.getChildern().add(c.getStatement().get());
            }
        });
        setStatement(stmnt);
    }

    private void createUiDelegate() {
        Button button = new Button();
        button.setOnAction(e -> {
            tab.getTabPane().getSelectionModel().select(tab);
        });
        ContextMenu m = new ContextMenu();
        MenuItem mi = new MenuItem("Edit Connection");
        m.getItems().add(mi);
        mi.setOnAction(e ->{
            Stage s = new Stage();
            ConnectDatabaseUi ui = new ConnectDatabaseUi();
            Scene sc = new Scene(ui);
            s.setScene(sc);
            ui.okButton.setOnAction(v ->{
                databaseName.setValue(ui.databaseNameTextField.getText());
                databaseUserName.setValue(ui.uesrNameTextField.getText());
                databasePassword.setValue(ui.passwordTextField.getText());
                databaseUrl.setValue(ui.hostNameTextField.getText());
                s.close();
            });
            ui.cancelButton.setOnAction(v ->{
                s.close();
            });
            s.showAndWait();
        });
        
        button.setContextMenu(m);
        button.setText("Connect Database");
        setUiDelegate(button);

//TODO add connect Statemnt 
    }

    @Override
    public String addComponent(Component c) throws IllegalComponent {
        if (c.getType() != DATABAASE_TABLE_TYPE) {
            throw new IllegalComponent("Can't Add Component isn't Table");
        }
        return super.addComponent(c); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return getDatabaseUrl() + getDatabaseName();
    }

    @Override
    public String getType() {
        return DATABAASE_CREATE_TYPE;
    }

    public StringProperty dataBaseNameProperty() {
        return databaseName;
    }

    public String getDatabaseName() {
        return databaseName.getValue();
    }

    public void setDatabaseName(String name) {
        databaseName.setValue(name);
    }

    public StringProperty databaseUrlProperty() {
        return databaseUrl;
    }

    public String getDatabaseUrl() {
        return databaseUrl.getValue();
    }

    public void setDatabaseUrl(String name) {
        databaseUrl.setValue(name);
    }

    public StringProperty databasePasswordProperty() {
        return databasePassword;
    }

    public String getDatabasePassword() {
        return databasePassword.getValue();
    }

    public void setDatabasePassword(String name) {
        databasePassword.setValue(name);
    }

    public StringProperty databaseUserNameProperty() {
        return databaseUserName;
    }

    public String getDatabaseUserName() {
        return databaseUserName.getValue();
    }

    public void setDatabaseUserName(String name) {
        databaseUserName.setValue(name);
    }

    public ObservableList<DatabaseTableComponent> getTables() {
        ObservableList<Component> childern = getChildern();
        ObservableList<DatabaseTableComponent> lst = new ObservableListWrapper<>(new ArrayList<>());
        for (Component c : childern) {
            lst.add((DatabaseTableComponent) c);
        }
        return lst;
    }

    @Override
    public Tab getTab() {
        return tab;
    }

}
