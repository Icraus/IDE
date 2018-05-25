/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import icraus.Components.event.CanvasDragEventHandler;
import static icraus.Components.UiManager.GUI_QUALIFIER;
import java.io.IOException;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Shoka
 */
public class MethodContentPane extends ScrollPane implements Selectable{

    @FXML
    private AnchorPane pane;
    private MethodComponent parent;

    private MethodContentPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MethodContentPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public MethodContentPane(MethodComponent _parent) {
        this();
        parent = _parent;
        addEventFilter(DragEvent.ANY, new CanvasDragEventHandler());
        parent.getChildern().addListener((Observable e) -> {
            drawContent();
        });
        setId(GUI_QUALIFIER + parent.getUUID());
        String css = getClass().getResource("styleclass.css").toExternalForm();
        getStylesheets().add(css);

    }

    protected void drawContent() {
        pane.getChildren().clear();
        ObservableList<Node> lst = pane.getChildren();
        for (Component c : parent.getChildern()) {
            lst.add(c.getUiDelegate().getValue());
        }
    }

     @Override
    public String getParentComponentUuid() {
        return parent.getUUID();
    }

    
}
