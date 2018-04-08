/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import icraus.Components.event.CanvasDragEventHandler;
import static ide.UiManager.GUI_QUALIFIER;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Shoka
 */
public class ScrollAnchorPane extends ScrollPane implements Selectable {

    private Component parentComponent;

    public ScrollAnchorPane(Component _parent) {
        setWidth(2000);
        setHeight(2000);
        parentComponent = _parent;
        setContent(new ContentPane());
        parentComponent.getChildern().addListener((Observable e) -> {
            drawClasses();
        });
        setId(GUI_QUALIFIER + parentComponent.getUUID());

        getContent().addEventHandler(DragEvent.ANY, new CanvasDragEventHandler());
    }

    @Override
    public String getParentComponentUuid() {
        return parentComponent.getUUID();
    }

    private void drawClasses() {

        ObservableList<Component> lst = parentComponent.getChildern();
        ((AnchorPane) getContent()).getChildren().clear();
        for (Component c : lst) {
            ((AnchorPane) getContent()).getChildren().add(c.getUiDelegate().getValue());
        }

    }
}

class ContentPane extends AnchorPane {

    public ContentPane() {
        setWidth(2000);
        setHeight(2000);
    }

}
