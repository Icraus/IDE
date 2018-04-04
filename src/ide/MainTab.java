/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ide;

import icraus.Components.ClassComponent;
import icraus.Components.ComponentsModel;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Shoka
 */
public class MainTab extends Tab {

    private final ComponentsModel model;

    private final AnchorPane contentPane;

    public MainTab() {
        super("MAIN");
        model = ComponentsModel.getInstance();
        contentPane = new AnchorPane();
        setContent(new ScrollPane(contentPane));
        model.getModel().addListener((Observable e) -> drawClasses());

    }

    private void drawClasses() {

        ObservableList<ClassComponent> lst = model.toList();
        contentPane.getChildren().clear();
        for (ClassComponent c : lst) {
            contentPane.getChildren().add(c.getUiDelegate().getValue());
        }

    }

}
