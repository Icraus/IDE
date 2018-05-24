/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database.ui;

import icraus.Components.Component;
import icraus.Components.DraggableComponent;
import icraus.database.DeleteComponent;
import icraus.database.DeleteStatement;
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
public class DeleteUi extends Button implements DraggableComponent {

    private DeleteComponent parent;

    public DeleteUi(DeleteComponent aThis) {
        parent = aThis;
        setText("Delete");
        setOnAction((ActionEvent e) -> {
            Stage s = new Stage();
            DeleteDatabaseDialog dialog = new DeleteDatabaseDialog();
            s.setScene(new Scene(dialog));
            dialog.okButton.setOnAction(v -> {
                changeStatment(dialog.getTableName(), dialog.getColumns(), dialog.getValues(), dialog.getOperators(), dialog.getBitOperators());
                s.close();
            });
            dialog.cancelButton.setOnAction(v -> {
                s.close();
            });
            s.show();

        });
    }

    private void changeStatment(String table, List<String> columns, List<String> values) {
        DeleteStatement value = (DeleteStatement) getParentComponent().getStatement().getValue();
        changeStatment(table, columns, values);
    }
    
    private void changeStatment(String table, List<String> columns, List<String> values, List<String> ops, List<String> bitOps) {
        DeleteStatement value = (DeleteStatement) getParentComponent().getStatement().getValue();
        value.changeStatment(table, columns, values, ops, bitOps);
    }
    private Component getParentComponent() {
        return parent;
    }

}

