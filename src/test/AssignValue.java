
package test;

import icraus.Components.Component;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class AssignValue extends Item {
    
    private String variable;
    private String value;
    
    AssignValue(Component _parent){
        super(_parent);
        setText("Assign");
        setMinWidth(120);
        setMinHeight(40);
        getStyleClass().add("assignValueStyle");
       
        setContextMenu(new ItemContextMenu(6, getUUID()));
        
        setOnMouseClicked((MouseEvent mouseEvent) -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
                if (mouseEvent.getClickCount() == 2)
                    new EditItem(6, getUUID());
        });
    }  
    
    public void setParameters(String variable, String value) {
        this.variable = variable;
        this.value = value;
    }
    public String getVariable() {
        return variable;
    }
    public String getValue() {
        return value;
    }
}
