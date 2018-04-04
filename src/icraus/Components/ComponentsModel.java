/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import com.sun.javafx.collections.ObservableListWrapper;
import com.sun.javafx.collections.ObservableMapWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TreeItem;

/**
 *
 * @author Shoka
 */
public class ComponentsModel {
    private ProjectComponent project;
    private ObservableMap<String, ClassComponent> model;
    private StringProperty currentComponent; //TODO add Current Component Selector
    private static ComponentsModel instance = new ComponentsModel();
    private ObjectProperty<TreeItem<Component>> root;

    private ComponentsModel() {
        this.project = new ProjectComponent("Java");
        this.currentComponent = new SimpleStringProperty();
        this.model = new ObservableMapWrapper<>(new HashMap<>());
        root = new SimpleObjectProperty<>();
        model.addListener((Observable e) -> {
            calculateRoot();
        });
    }

    public static ComponentsModel getInstance() {
        return instance;
    }

    public Component getComponentByUuid(String uuid) throws ComponentNotFoundException {
        if(project.getUUID() == uuid)
            return project;
        ClassComponent comp = model.get(uuid);
        if (comp != null) {
            return comp;
        }
        Queue<Component> q = new LinkedList<>();
        ObservableList<ClassComponent> lst = toList();
        q.addAll(lst);
        Component tmp = null;
        while (!q.isEmpty()) {
            Component top = q.poll();
            if (top.getUUID() == uuid) {
                tmp = top;
                break;
            }
            q.addAll(top.getChildern());
        }
        if (tmp == null) {
            throw new ComponentNotFoundException();
        }
        return tmp;
    }

    public String addClass(String className, String packageName) {
        ClassComponent c = new ClassComponent(className, packageName);
        model.put(c.getUUID(), c);
        calculateRoot();
        return c.getUUID();
    }

    public String addComponent(String uuid, Component c) throws ComponentNotFoundException, IllegalComponent {
        Component parent = getComponentByUuid(uuid);
        if (parent == null) {
            throw new ComponentNotFoundException();
        }
        parent.addComponent(c);
        return parent.getUUID();
    }

    public boolean removeComponetByUuid(String uuid) throws ComponentNotFoundException {
        //TODO add remove by finding
        ClassComponent comp = model.remove(uuid);
        if (comp != null) {
            return true;
        }
        throw new ComponentNotFoundException();
    }

    public ObservableList<MethodComponent> getAllMethods() {
        ObservableList<MethodComponent> lst = new ObservableListWrapper<>(new ArrayList<>());
        for (String s : model.keySet()) {
            ClassComponent get = model.get(s);
            lst.addAll(get.getMethodsList());
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
        ObservableList<ClassComponent> lst = toList();
        for (ClassComponent c : lst) {
            if (c.getClassName().getValue() == name) {
                return c;
            }
        }
        throw new ComponentNotFoundException("Class Not Found");
    }

    public String addMethodByUuid(String uuid, MethodComponent c) throws IllegalComponent, ComponentNotFoundException {
        try {
            return model.get(uuid).addComponent(c);
        } catch (NullPointerException ex) {
            throw new ComponentNotFoundException();
        }

    }

    public ObjectProperty<TreeItem<Component>> treeItemsProperty() {
        return root;
    }

    public void calculateRoot() {
        ObservableList<ClassComponent> lst = toList(); //FIXME change project name
        root.setValue(new TreeItem<>(project));
        for (Component c : lst) {
            TreeItem<Component> itm = c.toTreeItem();
            root.get().getChildren().add(itm);
        }
    }

    public ObservableList<ClassComponent> toList() {
        return FXCollections.observableArrayList(model.values());
    }

    public ObservableMap<String, ClassComponent> getModel() {
        return model;
    }

    public void setModel(ObservableMap<String, ClassComponent> model) {
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
