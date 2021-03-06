package test;

import com.icraus.vpl.codegenerator.GrammerConstants;
import com.icraus.vpl.codegenerator.SimpleStatement;
import icraus.Components.SimpleComponent;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class InputValue extends Item {

    private StringProperty variable;
    private EditInputValueWindowBase d;

    public InputValue(SimpleComponent parent) {
        super(parent);
        this.variable = new SimpleStringProperty();
        setText("Input");
        d = new EditInputValueWindowBase();
        setMinWidth(120);
        setMinHeight(40);
        String css = getClass().getResource("inputStyle.css").toExternalForm();
        getStylesheets().add(css);
        getStyleClass().add("inputValue");
        setOnAction(e -> {
            Stage stg = createDialog(d);
            d.txtVariable.textProperty().bindBidirectional(variable);
            d.btnApply.setOnAction(c -> {
                parametersChanged(d.txtVariable.getText());
                stg.close();
            });
            stg.showAndWait();
        });
//        createContextMenu();

    }

    public void setVariable(String variable) {
        this.variable.setValue(variable);
    }

    public String getVariable() {
        return variable.getValue();
    }

    private void createContextMenu() {
        MenuItem itm = new MenuItem("Edit");
        ContextMenu mnu = new ContextMenu(itm);
        setContextMenu(mnu);
//        itm.
    }

    private void parametersChanged(String varName) {
        SimpleStatement statement = (SimpleStatement) getParentComponent().getStatement().getValue();
        statement.setStatementString("String" + " " + varName + " = " + GrammerConstants.STAT_INPUT_OP + GrammerConstants.OP_END_LINE);
    }

}
