/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import com.icraus.ide.ui.components.DraggableCanvasComponentEventHandler;
import com.sun.javafx.collections.ObservableListWrapper;
import com.sun.javafx.collections.ObservableMapWrapper;
import ide.UiManager;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Shoka
 */
public class ComponentsModel {

    private ObservableMap<String, ProjectComponent> model;
    private StringProperty currentComponent; //TODO DONE BUT NEEDS FIX add Current Component Selector
    private static ComponentsModel instance = new ComponentsModel();
    private ObjectProperty<TreeItem<Component>> root;

    private ComponentsModel() {
        this.model = new ObservableMapWrapper<>(new HashMap<>());
        this.currentComponent = new SimpleStringProperty();
        root = new SimpleObjectProperty<>();
        model.addListener((Observable e) -> {
            calculateRoot();
        });
    }

    public static ComponentsModel getInstance() {
        return instance;
    }

    public Component getComponentByUuid(String uuid) throws ComponentNotFoundException {
        ProjectComponent comp = model.get(uuid);
        if (comp != null) {
            return comp;
        }
        ObservableList<ProjectComponent> lst = toList();
        for (ProjectComponent c : lst) {
            try {
                Component f = c.getComponentByUuid(uuid);
                if (f != null) {
                    return f;
                }
            } catch (ComponentNotFoundException ex) {

            }

        }
        throw new ComponentNotFoundException();
    }
//    public List<Component> getComponentsByProperty(Component c, String prorpetyName, String value){
//        List<Component> components = new ArrayList<>();
//        c.getPropreties().get(prorpetyName);
//     TODO add getComponentsByProperty   
//    }

    public List<Component> getComponentsByType(String type) {
        List<Component> components = new ArrayList<>();
        ObservableList<ProjectComponent> temp = toList();
        Queue<Component> q = new ArrayDeque<>();
        q.addAll(temp);
        while (!q.isEmpty()) {
            Component c = q.poll();
            if(c == null)
                continue;
            if (c.getType() == type) {
                components.add(c);
            }
            q.addAll(c.getChildern());
        }

        return components;
    }

    public String addProject(String projectName) {
        ProjectComponent c = new ProjectComponent(projectName);
        model.put(c.getUUID(), c);
        addComponentHelper(c);

        return c.getUUID();
    }

    public String addComponent(String uuid, Component c) throws ComponentNotFoundException, IllegalComponent {
        Component parent = getComponentByUuid(uuid);
        if (parent == null) {
            throw new ComponentNotFoundException();
        }
        parent.addComponent(c);
        addComponentHelper(c);
        return c.getUUID();
    }

    protected void addComponentHelper(Component c) {
        c.getChildern().addListener((Observable e) -> {
            ComponentsModel.getInstance().calculateRoot();//FIXME calculate root 
        });
        if ((c.getFlags() & ComponentFlags.PAGEABLE_FLAG) > 0) {

            Pageable p = (Pageable) c;
            UiManager.getInstance().addTab(p.getTab());
        }
        // c.getUiDelegate().getValue() instanceof DraggableComponent        FIXME DONE addEvenetHandler for new Components 
        if ((c.getFlags() & ComponentFlags.DRAGGABLE_FLAG) > 0) {
            c.getUiDelegate().getValue().addEventHandler(MouseEvent.DRAG_DETECTED, new DraggableCanvasComponentEventHandler());
        }

    }

    public boolean removeComponetByUuid(String uuid) throws ComponentNotFoundException {
        //TODO DONE add remove by finding
        Component c = getComponentByUuid(uuid);
        c.getParent().getChildern().remove(c);
        return true;
    }

    public ObservableList<MethodComponent> getAllMethods() {
        ObservableList<MethodComponent> lst = new ObservableListWrapper<>(new ArrayList<>());
        for (String s : model.keySet()) {
            ProjectComponent get = model.get(s);
            lst.addAll(get.getAllMethods());
        }
        return lst;
    }

    public ObservableList<MethodComponent> getClassMethods(String uuid) throws ComponentNotFoundException {
        ClassComponent comp = (ClassComponent) getComponentByUuid(uuid);
        ObservableList<MethodComponent> lst = new ObservableListWrapper<>(new ArrayList<>());
        lst.setAll(comp.getMethodsList());
        return lst;
    }

    public ClassComponent getClassByName(String name) throws ComponentNotFoundException {
        ObservableList<ProjectComponent> lst = toList();
        for (ProjectComponent c : lst) {
//FIXME fix return of class name
            return c.getClassByName(name);
        }

        throw new ComponentNotFoundException("Class Not Found");
    }

    public String addMethodByUuid(String uuid, MethodComponent c) throws IllegalComponent, ComponentNotFoundException {
        try {
            return addComponent(uuid, c);
        } catch (NullPointerException ex) {
            throw new ComponentNotFoundException();
        }

    }

    public ObjectProperty<TreeItem<Component>> treeItemsProperty() {
        return root;
    }

    public void calculateRoot() {
        ObservableList<ProjectComponent> lst = toList(); //FIXME change project name
        root.setValue(new TreeItem<>(new SimpleComponent()));

        for (Component c : lst) {
            root.get().getChildren().add(calculateRootHelper(c));
        }
    }

    private TreeItem<Component> calculateRootHelper(Component c) {
        TreeItem<Component> root = new TreeItem<>(c);
        if (c.getChildern().isEmpty()) {
            return root;
        } else {
            for (Component child : c.getChildern()) {
                root.getChildren().add(calculateRootHelper(child));
            }
            return root;
        }
    }

    public ObservableList<ProjectComponent> toList() {
        return FXCollections.observableArrayList(model.values());
    }

    public ObservableMap<String, ProjectComponent> getModel() {
        return model;
    }

    public void setModel(ObservableMap<String, ProjectComponent> model) {
        this.model = model;
    }

    public StringProperty currentComponentProperty() {
        return currentComponent;
    }

    public String getCurrentComponent() {
        return currentComponent.getValue();
    }

    public void setCurrentComponent(StringProperty currentComponent) {
        this.currentComponent = currentComponent;
    }

    public void setCurrentComponent(String currentComponent) {
        this.currentComponent.setValue(currentComponent);
    }
}
