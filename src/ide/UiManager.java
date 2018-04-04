/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ide;

import icraus.Components.ComponentNotFoundException;
import icraus.Components.ComponentsModel;
import icraus.Components.Pageable;
import icraus.Components.Selectable;
import icraus.Components.event.CanvasDragEventHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Shoka
 */
public class UiManager {
    public static final String GUI_QUALIFIER = "GUI";
    private static UiManager instance = new UiManager();
    private ObservableList<Tab> methodsTabs;   
    private TabPane mainTabPane;
    public static UiManager getInstance() {
        return instance;
    }
    private final ComponentsModel model;
    private final Tab mainTab;
    
    private UiManager() {
        this.mainTabPane = new TabPane();
        mainTabPane.getTabs().clear();
        this.methodsTabs = mainTabPane.getTabs();
        mainTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        methodsTabs.clear();
        mainTab = new MainTab();
        AnchorPane pane = new AnchorPane();
        pane.addEventHandler(DragEvent.ANY, new CanvasDragEventHandler());
        mainTab.setContent(new ScrollPane(pane));
        addTab(mainTab);
        model = ComponentsModel.getInstance();
        mainTabPane.getSelectionModel().selectedItemProperty().addListener((Observable e )-> {
            Selectable content = (Selectable)((ScrollPane)mainTabPane.getSelectionModel().getSelectedItem().getContent());
            ComponentsModel.getInstance().setCurrentComponent(content.getParentComponentUuid());
        });
    }
    public Node selectElementByUuId(String uuid){
        return getMainTabPane().lookup("#" + GUI_QUALIFIER + uuid);
    }
    public ObservableList<Tab> getMethodsTabs() {
        return methodsTabs;
    }
    public void addTab(Tab t){
        methodsTabs.add(t);
    }
    public Tab selectTabByUuid(String uuid){
        try {
            Pageable comp = (Pageable) ComponentsModel.getInstance().getComponentByUuid(uuid);
            Tab methodTab = comp.getTab();
            return methodTab;
        } catch (ComponentNotFoundException ex) {
            Logger.getLogger(UiManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Tab getCurrentTab(){
        return mainTabPane.getSelectionModel().getSelectedItem();
    }
    public TabPane getMainTabPane() {
        return mainTabPane;
    }

    public void setMainTabPane(TabPane mainTabPane) {
        this.mainTabPane = mainTabPane;
    }

    
     
}
