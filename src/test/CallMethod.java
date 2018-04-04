
package test;

import icraus.Components.Component;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CallMethod extends Item {
    
    private String method;
    
    CallMethod(Component parent){
        super(parent);
        setText("Call");
        setMinWidth(120);
        setMinHeight(40);
        getStyleClass().add("callMethodStyle");
        
        setContextMenu(new ItemContextMenu(9, getUUID()));
        
        setOnMouseClicked((MouseEvent mouseEvent) -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
                if (mouseEvent.getClickCount() == 2)
                    new EditItem(9, getUUID());
        });
    }  
    
    public void setMethod(String method) {
        this.method = method;
    }
    public String getMethod() {
        return method;
    }
}
