
package test;

import icraus.Components.Component;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class OutputExpression extends Item {
    
    private String expression;
    
    OutputExpression(Component parent){
        super(parent);
        setText("Output");
        setMinWidth(120);
        setMinHeight(40);
        getStyleClass().add("outputExpressionStyle");
        
        setContextMenu(new ItemContextMenu(8, getUUID()));
        
        setOnMouseClicked((MouseEvent mouseEvent) -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
                if (mouseEvent.getClickCount() == 2)
                    new EditItem(8, getUUID());
        });
    }  
    
    public void setExpression(String expression) {
        this.expression = expression;
    }
    public String getExpression() {
        return expression;
    }
}
