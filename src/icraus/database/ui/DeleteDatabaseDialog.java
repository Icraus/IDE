/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database.ui;

import icraus.database.DatabaseColumnComponent;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author Shoka
 */
public class DeleteDatabaseDialog extends InsertDialog {
    private List<StringProperty> bitOps = new ArrayList<>();
    private List<StringProperty> ops = new ArrayList<>();
    public DeleteDatabaseDialog() {
        super();
    }

    @Override
    protected void currentItemChanged() {
        ops.clear();
        bitOps.clear();
        super.currentItemChanged();
    }
    public List<String> getOperators(){
        List<String> values = new ArrayList<>();
        for(StringProperty s : this.ops){
            values.add(s.getValue());
        }
        return values;
    }
    
     public List<String> getBitOperators(){
        List<String> values = new ArrayList<>();
        for(StringProperty s : this.bitOps){
            values.add(s.getValue());
        }
        return values;
    }
    
    protected HBox databaseColumnMapper(DatabaseColumnComponent c) {
        TextField colOperator = new TextField("=");
        TextField colBitOperator = new TextField("AND");
        HBox h = super.databaseColumnMapper(c);
        ops.add(colOperator.textProperty());
        bitOps.add(colBitOperator.textProperty());
        h.getChildren().add(colBitOperator);
        h.getChildren().add(colOperator);
        return h;

    }
}
