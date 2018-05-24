/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database.ui;

import icraus.Components.Component;
import icraus.Components.DraggableComponent;
import icraus.database.InsertStatement;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Shoka
 */
public class InsertComponentUi extends Button implements DraggableComponent{
    private Component parent;
    public InsertComponentUi(Component _parent) {
        parent = _parent;
        setText("Insert");
        setOnAction((ActionEvent e) -> {
            Stage s = new Stage();
            InsertDialog dialog = new InsertDialog();
            s.setScene(new Scene(dialog));
            dialog.okButton.setOnAction(v -> {
                changeStatment(dialog.getTableName(), dialog.getColumns(), dialog.getValues());
                s.close();
            });
            dialog.cancelButton.setOnAction(v -> {
                s.close();
            });
            s.show();
            
        });
    }

    private void changeStatment(String table, List<String> columns, List<String> values) {
        InsertStatement value = (InsertStatement) getParentComponent().getStatement().getValue();
        value.setColumns(columns);
        value.setValues(values);
        value.setTableName(table);
    }

    private Component getParentComponent() {
        return parent;
    }
    
}
