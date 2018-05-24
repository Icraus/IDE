/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database;

import com.icraus.vpl.codegenerator.SimpleStatement;
import icraus.Components.Component;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Shoka
 */
public class DatabaseColumnComponent extends Component {

    public static final String DATABASE_CLOUMN_TYPE = "DATABASE_CLOUMN";

    private StringProperty columnName;
    private StringProperty columnType;
    private StringProperty columnProperties;
    private IntegerProperty columnSize;

    public DatabaseColumnComponent() {
        this.columnSize = new SimpleIntegerProperty();
        this.columnProperties = new SimpleStringProperty();
        this.columnType = new SimpleStringProperty();
        this.columnName = new SimpleStringProperty();
        setStatement(new SimpleStatement(""));
        createBinding();
    }

    public StringProperty columnNameProperty() {
        return columnName;
    }

    public String getColumnName() {
        return columnName.getValue();
    }

    public void setColumnName(String column) {
        this.columnName.setValue(column);
    }

    public StringProperty typeColumnProperty() {
        return columnType;
    }

    public String getColumnType() {
        return columnType.getValue();
    }

    public void setColumnType(String type) {
        this.columnType.set(type);
    }

    public StringProperty columnPropertiesProperty() {
        return columnProperties;
    }

    public String getColumnProperties() {
        return columnProperties.getValue();
    }

    public void setColumnProperties(String constraint) {
        this.columnProperties.setValue(constraint);
    }

    public IntegerProperty sizeProperty() {
        return columnSize;
    }

    public int getSize() {
        return columnSize.get();
    }

    public void setSize(int size) {
        this.columnSize.set(size);
    }

    public String toTableString() {
        String temp = getColumnName() + " " + getColumnType() + " (" + getSize() + ") " + getColumnProperties();
        return temp;
    }

    @Override
    public String toString() {
        return getColumnName();
    }

    @Override
    public String getType() {
        return DATABASE_CLOUMN_TYPE;
    }

    private void createBinding() {
        columnName.addListener(e -> {
            columnChanged();
        });
        columnProperties.addListener(e -> {
            columnChanged();
        });
        columnSize.addListener(e -> {
            columnChanged();
        });
        columnType.addListener(e -> {
            columnChanged();
        });
    }

    private void columnChanged() {
        getStatement().get().setStatementString(toTableString());
    }
}
