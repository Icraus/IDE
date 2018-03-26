
package test;

import java.util.ArrayList;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class WhileLoop extends Item {
    
    private String condition;
    public ArrayList<Item> loopContent = new ArrayList<>(5);
    
    WhileLoop(){
        setText("While");
        setMinWidth(120);
        setMinHeight(40);
        getStyleClass().add("whileLoopStyle");
        
        setContextMenu(new ItemContextMenu(3, getUUID()));
        
        setOnMouseClicked((MouseEvent mouseEvent) -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
                if (mouseEvent.getClickCount() == 2)
                    new EditItem(3, getUUID());
        });
    }  
    
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getCondition() {
        return condition;
    }
}
