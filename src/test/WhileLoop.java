
package test;

import icraus.Components.Component;
import icraus.Components.SimpleComponentTabbed;
import java.util.ArrayList;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class WhileLoop extends IfStatement {
    
    private String condition;
    public ArrayList<Item> loopContent = new ArrayList<>(5);
    
    public WhileLoop(SimpleComponentTabbed parent){
        super(parent);
        setText("While");
        setMinWidth(120);
        setMinHeight(40);
        getStyleClass().add("whileLoopStyle");
        
        
    }  
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getCondition() {
        return condition;
    }
}
