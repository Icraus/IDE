
package test;

import icraus.Components.Component;
import java.util.ArrayList;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class DoLoop extends Item {
    
    String condition;
    public ArrayList<Item> loopContent = new ArrayList<>(5);
    public DoLoop(Component parent){
        super(parent);
        setText("Do");
        setMinWidth(120);
        setMinHeight(40);
        getStyleClass().add("doLoopStyle");
        setContextMenu(new ItemContextMenu(4, getUUID()));
        
        setOnMouseClicked((MouseEvent mouseEvent) -> {
            //FIXME add Handler
        });
    }  
    
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getCondition() {
        return condition;
    }
}
