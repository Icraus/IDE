
package test;

import icraus.Components.Component;
import icraus.Components.SimpleComponent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class AssignValue extends Item {
    
    private String variable;
    private String value;
    public AssignValue(SimpleComponent _parent){
        super(_parent);
        setText("Assign");
        setMinWidth(120);
        setMinHeight(40);
        getStyleClass().add("assignValueStyle");
       
        createContextMenu();
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

    private void createContextMenu() {
        
        
    }
}
