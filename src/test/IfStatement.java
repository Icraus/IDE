
package test;

import com.icraus.vpl.codegenerator.CodeBlock;
import com.icraus.vpl.codegenerator.ConditionExpression;
import com.icraus.vpl.codegenerator.ConditionExpressionBuilder;
import com.icraus.vpl.codegenerator.Expression;
import com.icraus.vpl.codegenerator.IfCodeBlockHead;
import icraus.Components.Component;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class IfStatement extends Item {
    StringProperty condition;
    ConditionExpressionBuilder builder;
//    String condition;
        public ArrayList<Item> conditionTrue = new ArrayList<>(5);
    public ArrayList<Item> loopContentFalse = new ArrayList<>(5);
    private Component parent;
    public IfStatement(Component _parent){
        this.builder = new ConditionExpressionBuilder();
        this.condition = new SimpleStringProperty();
        parent = _parent;
        setText("If");
        setMinHeight(80);
        setMinWidth(80);
        getStyleClass().add("ifStatementStyle");
        setContextMenu(new ItemContextMenu(2, getUUID()));
        condition.addListener(e -> {
            conditionChanged();
        });
        setOnMouseClicked((MouseEvent mouseEvent) -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
                if (mouseEvent.getClickCount() == 2)
//                    new Ed itItem(2, getUUID());
                {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.showAndWait().ifPresent(e ->{
                        condition.setValue(e);
                    });
                }
        });
    }  
    protected void conditionChanged(){
        CodeBlock value = (CodeBlock) parent.getStatement().getValue();
        IfCodeBlockHead head = (IfCodeBlockHead) value.getHead();
        builder.appendAndCondition(condition.getValue());
        Expression cond = builder.buildExpression();
        head.setCondition((ConditionExpression) cond);
    }

    public void setCondition(String condition) {
        this.condition.setValue(condition);
    }
    public String getCondition() {
        return condition.getValue();
    }
    
}
