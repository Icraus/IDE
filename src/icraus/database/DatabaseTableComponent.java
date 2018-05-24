/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database;

import com.sun.javafx.collections.ObservableListWrapper;
import icraus.Components.Component;
import icraus.Components.IllegalComponent;
import icraus.database.ui.TablePane;
import java.util.ArrayList;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Shoka
 */
public class DatabaseTableComponent extends Component {

    public static final String DATABAASE_TABLE_TYPE = "DATABAASE_TABLE_TYPE";
    private StringProperty tableName;

    public DatabaseTableComponent() {
        super();
        this.tableName = new SimpleStringProperty();
        setStatement(new CreateDatabaseStatement());
        setUiDelegate(new TablePane(this)); //TODO 0.0
        createBindings();
    }

    @Override
    public String addComponent(Component c) throws IllegalComponent {
        if (c.getType() != DatabaseColumnComponent.DATABASE_CLOUMN_TYPE) {
            throw new IllegalComponent("");
        }
        return super.addComponent(c);
    }

    @Override
    public String toString() {
        return getTableName();
    }

    @Override
    public String getType() {
        return DATABAASE_TABLE_TYPE;
    }

    public StringProperty tableNameProperty() {
        return tableName;
    }

    public String getTableName() {
        return tableName.getValue();
    }

    public void setTableName(String tableName) {
        this.tableName.setValue(tableName);
    }

    public ObservableList<DatabaseColumnComponent> getColumns() {
        ObservableList<Component> childern = getChildern();
        ObservableList<DatabaseColumnComponent> lst = new ObservableListWrapper<>(new ArrayList<>());
        for(Component c : childern){
            lst.add((DatabaseColumnComponent)c);
        }
        return lst;
    }

    private void createBindings() {
        getChildern().addListener((Observable e) -> {
            columnsChanged();
        });
        tableNameProperty().addListener(e -> {
            tableNameChanged();
        });
    }

    private void tableNameChanged() {
        CreateDatabaseStatement stat = (CreateDatabaseStatement) getStatement().get();
        stat.setTableName(getTableName());
    }

    private void columnsChanged() {
        CreateDatabaseStatement stat = (CreateDatabaseStatement) getStatement().get();
        stat.getColumns().clear();
        for (DatabaseColumnComponent c : getColumns()) {
            stat.addColumn(c.toTableString());
        }
    }

}
