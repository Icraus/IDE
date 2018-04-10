package test;

import com.icraus.vpl.codegenerator.CodeBlock;
import com.icraus.vpl.codegenerator.CodeBlockHead;
import com.icraus.vpl.codegenerator.ConditionExpression;
import com.icraus.vpl.codegenerator.ConditionExpressionBuilder;
import com.icraus.vpl.codegenerator.DeclareExpression;
import com.icraus.vpl.codegenerator.ForCodeBlockHead;
import com.icraus.vpl.codegenerator.OperationExpression;
import icraus.Components.Component;
import icraus.Components.SimpleComponentTabbed;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ForLoop extends Item {

    private StringProperty variable;
    private StringProperty start;
    private StringProperty condition;
    private StringProperty step;
    public ArrayList<Item> loopContent;
    private EditForLoopWindowBase d = new EditForLoopWindowBase();

    public ForLoop(SimpleComponentTabbed parent) {
        super(parent);
        this.loopContent = new ArrayList<>(5);
        this.step = new SimpleStringProperty("");
        this.condition = new SimpleStringProperty("");
        this.start = new SimpleStringProperty("");
        this.variable = new SimpleStringProperty("");
        setText("For");
        setMinWidth(120);
        setMinHeight(40);
        String css = getClass().getResource("forStyle.css").toExternalForm();
        getStylesheets().add(css);
        getStyleClass().add("forLoop");
        createContextMenu();
        createListeners();
    }

    private void createContextMenu() {
        MenuItem i = new MenuItem("Set Parameters");
        i.setOnAction((e) -> {
            Stage stg = new Stage();
            Scene s = new Scene(d);
            stg.setScene(s);
            d.btnApply.setOnAction((c) -> stg.close());
            stg.showAndWait();
            condition.bindBidirectional(d.txtCondition.textProperty());
            start.bindBidirectional(d.txtStart.textProperty());
            step.bindBidirectional(d.txtStep.textProperty());
            variable.bindBidirectional(d.txtVariable.textProperty());
        });
        ContextMenu m = new ContextMenu(i);
        setContextMenu(m);
    }

    public void parametersChanged() {
        String cond = condition.getValue();
        String stp = step.getValue();
        String var = variable.getValue();
        String strt = start.getValue();
        CodeBlock c = (CodeBlock) getParentComponent().getStatement().getValue();
        ForCodeBlockHead head = (ForCodeBlockHead) c.getHead();
        head.setPostItrExpression(new OperationExpression(stp));
        head.setCondition(new ConditionExpression(cond));
        head.setDeclareExpression(new DeclareExpression(var));
    }

    public String getVariable() {
        return variable.getValue();
    }

    public String getStart() {
        return start.getValue();
    }

    public String getCondition() {
        return condition.getValue();
    }

    public String getStep() {
        return step.getValue();
    }

    private void createListeners() {
        condition.addListener(e -> parametersChanged());
        variable.addListener(e -> parametersChanged());
        start.addListener(e -> parametersChanged());
        step.addListener(e -> parametersChanged());
    }
}
