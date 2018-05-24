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
public class InsertStatement extends Statement{
    public static final String INSERT_STATEMENT_TEMPLATE = "INSERT INTO $$TABLE_NAME ($$COLUMNS_NAME) VALUES ($$VALUES);";
    public static final String TABLE_NAME_VAR = "$$TABLE_NAME";
    public static final String TABLE_COLUMNS_VAR = "$$COLUMNS_NAME";
    public static final String COLUMNS_VALUE_VAR = "$$VALUES";
    private List<String> columns = new ArrayList<>();
    private List<String> values = new ArrayList<>();
    private String tableName = "";
    public InsertStatement() {
        
    }
    
    @Override
    public String toText() throws ErrorGenerateCodeException {
          String s = INSERT_STATEMENT_TEMPLATE;
          String cols = String.join(", ", getColumns());
          String vals = String.join(", ", getValues());
          s = s.replace(TABLE_NAME_VAR, getTableName());
          s = s.replace(TABLE_COLUMNS_VAR, cols);
          s = s.replace(COLUMNS_VALUE_VAR, vals);
          return s;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
}
