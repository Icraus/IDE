
package test;

import icraus.Components.Component;
import java.util.ArrayList;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ForLoop extends Item {
    
    private String variable;
    private String start;
    private String condition;
    private String step;
    public ArrayList<Item> loopContent = new ArrayList<>(5);
    
    ForLoop(Component parent){
        super(parent);
        setText("For");
        setMinWidth(120);
        setMinHeight(40);
        getStyleClass().add("forLoopStyle");
        
        setContextMenu(new ItemContextMenu(1, getUUID()));
        
        setOnMouseClicked((MouseEvent mouseEvent) -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
                if (mouseEvent.getClickCount() == 2)
                    new EditItem(1, getUUID());
        });
    }  
    
    public void setParameters(String variable, String start, String condition, String step) {
        this.variable = variable;
        this.start = start;
        this.condition = condition;
        this.step = step;
    }
    public String getVariable() {
        return variable;
    }
    public String getStart() {
        return start;
    }
    public String getCondition() {
        return condition;
    }
    public String getStep() {
        return step;
    }
}
