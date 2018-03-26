
package test;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Comment extends Item {
    
    private String comment;
    
    Comment(){
        setText("Comment");
        setMinWidth(120);
        setMinHeight(40);
        getStyleClass().add("commentStyle");
        
        setContextMenu(new ItemContextMenu(10, getUUID()));
        
        setOnMouseClicked((MouseEvent mouseEvent) -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
                if (mouseEvent.getClickCount() == 2)
                    new EditItem(10, getUUID());
        });
    }  
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getComment() {
        return comment;
    }
}
