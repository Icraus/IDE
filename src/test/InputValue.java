
package test;

import icraus.Components.Component;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class InputValue extends Item {
    
    private String variable;
    
    InputValue(Component parent){
        super(parent);
        setText("Input");
        setMinWidth(120);
        setMinHeight(40);
        getStyleClass().add("inputValueStyle");
        
        setContextMenu(new ItemContextMenu(7, getUUID()));
        
        setOnMouseClicked((MouseEvent mouseEvent) -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
                if (mouseEvent.getClickCount() == 2)
                    new EditItem(7, getUUID());
        });
    }  
    
    public void setVariable(String variable) {
        this.variable = variable;
    }
    public String getVariable() {
        return variable;
    }
}
