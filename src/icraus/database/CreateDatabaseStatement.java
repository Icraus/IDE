/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database;

import com.icraus.vpl.codegenerator.ErrorGenerateCodeException;
import com.icraus.vpl.codegenerator.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shoka
 */
public class CreateDatabaseStatement extends Statement {

    public static final String CREATE_DATABASE_TEMPLATE = "CREATE TABLE IF NOT EXISTS $$TABLE_NAME ($$COLUMN_NAME);";
    public static final String COLUMN_NAME_VAR = "$$COLUMN_NAME";
    public static final String TABLE_NAME_VAR = "$$TABLE_NAME";

    private String tableName;
    private List<String> columns;

    public CreateDatabaseStatement() {
        this.tableName = "";
        this.columns = new ArrayList<>();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void addColumn(String col) {
        columns.add(col);
    }

    @Override
    public String toText() throws ErrorGenerateCodeException {
        String temp = CREATE_DATABASE_TEMPLATE;
        String s = String.join(", ", getColumns());
        temp = temp.replace(TABLE_NAME_VAR, getTableName());
        temp = temp.replace(COLUMN_NAME_VAR, s);
        return temp;
    }

}
