package test;

import com.icraus.vpl.codegenerator.CodeBlock;
import com.icraus.vpl.codegenerator.CodeBlockHead;
import com.icraus.vpl.codegenerator.ConditionExpression;
import com.icraus.vpl.codegenerator.ConditionExpressionBuilder;
import com.icraus.vpl.codegenerator.Expression;
import com.icraus.vpl.codegenerator.IfCodeBlockHead;
import com.icraus.vpl.codegenerator.SimpleStatement;
import com.icraus.vpl.codegenerator.Statement;
import icraus.Components.Component;
import icraus.Components.SimpleComponent;
import icraus.Components.SimpleComponentTabbed;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class IfStatement extends Item {

    StringProperty condition;
    ConditionExpressionBuilder builder;
//    String condition;
    public ArrayList<Item> conditionTrue = new ArrayList<>(5);
    public ArrayList<Item> loopContentFalse = new ArrayList<>(5);

    public IfStatement(SimpleComponentTabbed parent) {
        super(parent);
        this.builder = new ConditionExpressionBuilder();
        this.condition = new SimpleStringProperty();

        setText("If");
        setMinHeight(80);
        setMinWidth(80);
//        setStyle("-fx-background-color: 'red'");
        setStyle("-fx-background-color: #ff0000;\n"
                + "-fx-font: 20px System;\n"
                + "-fx-font-weight: Bold;\n"
                + "-fx-shape: \"M0 50 L50 0 L100 50 L50 100 Z\";");
//        getStylesheets().add(getClass().getResource("MainCSS.css").toExternalForm());
        createContextMenu();
    }

    public void setCondition(String condition) {
        this.condition.setValue(condition);
    }

    public String getCondition() {
        return condition.getValue();
    }

    private void createContextMenu() {
        MenuItem i = new MenuItem("edit");
        i.setOnAction(e -> {
            TextInputDialog d = new TextInputDialog(condition.getValue());
            d.showAndWait().ifPresent(c -> {
                parametersChanged(c);
            });
        });
        ContextMenu m = new ContextMenu(i);
        setContextMenu(m);
    }

    private void parametersChanged(String c) {
        CodeBlock get = (CodeBlock) getParentComponent().getStatement().get();
        IfCodeBlockHead head = (IfCodeBlockHead) get.getHead();
        head.setCondition(new ConditionExpression(c));
    }

}
