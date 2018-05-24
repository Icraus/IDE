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
public class DeleteStatement extends Statement {

    public static final String TABLE_VAR = "$$TABLE_NAME_VAR";
    public static final String CONDITION_VAR = "$$CONDITION_VAR";
    public static final String DELETE_TEMPLATE = "DELETE FROM "+TABLE_VAR +" WHERE "+CONDITION_VAR;
    private String condition = "";
    private String tableName;

    public void addCondtion(String col, String value) {
        addCondtion(col, value, "=", "AND");
    }

    public void addCondtion(String col, String value, String op, String bitOp) {
        condition += String.join(" ", bitOp, col, op, value);
    }

    public void changeStatment(String table, List<String> columns, List<String> values) {
        List<String> ops = new ArrayList<>();
        List<String> bitOps = new ArrayList<>();
        for(int i = 0; i < columns.size(); ++i){
            ops.add("=");
            bitOps.add("AND");
        }
        changeStatment(table, columns, values, ops, bitOps);
    }

    @Override
    public String toText() throws ErrorGenerateCodeException {
        String tmp = DELETE_TEMPLATE;
        tmp = tmp.replace(TABLE_VAR, getTableName().trim());
        tmp = tmp.replace(CONDITION_VAR, getCondition().trim());
        return tmp.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void changeStatment(String table, List<String> columns, List<String> values, List<String> ops, List<String> bitOps) {
        setCondition("");
        setTableName(table);
        bitOps.set(0, "");
        for (int i = 0; i < columns.size(); ++i) {
            addCondtion(columns.get(i), values.get(i), ops.get(i), bitOps.get(i));
        }
    }

}
