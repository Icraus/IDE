package test;

import icraus.Components.SimpleComponentTabbed;
import java.util.ArrayList;

public class WhileLoop extends IfStatement {

    private String condition;
    public ArrayList<Item> loopContent = new ArrayList<>(5);

    public WhileLoop(SimpleComponentTabbed parent) {
        super(parent);
        setText("While");
        setMinWidth(120);
        setMinHeight(40);
        String css = getClass().getResource("whileStyle.css").toExternalForm();
        getStylesheets().add(css);
        getStyleClass().add("whileLoop");

    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }
}
