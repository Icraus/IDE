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
import java.util.ArrayList;
import java.util.HashMap;
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
    private StringProperty currentComponent; //TODO add Current Component Selector
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
        return parent.getUUID();
    }

    protected void addComponentHelper(Component c) {
        if (c instanceof Pageable) {
            Pageable p = (Pageable) c;
            UiManager.getInstance().addTab(p.getTab());
        }
        //         FIXME addEvenetHandler for new Components 
        if (c.getUiDelegate().getValue() instanceof DraggableComponent) {
            c.getUiDelegate().getValue().addEventHandler(MouseEvent.DRAG_DETECTED, new DraggableCanvasComponentEventHandler());
        }

    }

    public boolean removeComponetByUuid(String uuid) throws ComponentNotFoundException {
        //TODO add remove by finding
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
            TreeItem<Component> itm = c.toTreeItem();
            root.get().getChildren().add(itm);
        }

//        ObservableList<ProjectComponent> lst = toList(); //TODO migrate to single root calculation
//        root.setValue(new TreeItem<>(new SimpleComponent()));
//        for (Component c : lst) {
//            TreeItem<Component> itmRoot = new TreeItem<>(c);
//            Queue<Component> q = new LinkedList<>();
//            q.addAll(c.getChildern());
//            while (!q.isEmpty()) {
//                Component cin = q.poll();
//                TreeItem<Component> itm = new TreeItem<>(cin);
//                q.addAll(cin.getChildern());
//                itmRoot.getChildren().add(itm);
//            }
//        }
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
