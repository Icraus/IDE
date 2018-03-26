/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import com.sun.javafx.collections.ObservableMapWrapper;
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

    private String projectName = "java";
    private ObservableMap<String, ClassComponent> model;
    private StringProperty currentComponent; //TODO add Current Component Selector
    private static ComponentsModel instance = new ComponentsModel();
    private ObjectProperty<TreeItem<Component>> root;

    private ComponentsModel() {
        this.model = new ObservableMapWrapper<>(new HashMap<>());
        this.currentComponent = new SimpleStringProperty();
        root = new SimpleObjectProperty<>();
        model.addListener((Observable e) ->{ 
            calculateRoot();
        });
    }

    public static ComponentsModel getInstance() {
        return instance;
    }

    public Component getComponentByUuid(String uuid) throws ComponentNotFoundException {
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
//        parent.addComponent(c);
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

    public ClassComponent getClassByName(String name) throws ComponentNotFoundException {
        ObservableList<ClassComponent> lst = toList();
        for (ClassComponent c : lst) {
            if (c.getClassName().getValue() == name) {
                return c;
            }
        }
        throw new ComponentNotFoundException("Class Not Found");
    }

    public void addMethodByUuid(String uuid, MethodComponent c) throws IllegalComponent, ComponentNotFoundException {
        try {
            model.get(uuid).addComponent(c);
        } catch (NullPointerException ex) {
            throw new ComponentNotFoundException();
        }

    }
    public ObjectProperty<TreeItem<Component>> treeItemsProperty(){
        return root;
    }
    public void calculateRoot() {
        ObservableList<ClassComponent> lst = toList();
        root.setValue(new TreeItem<>(new ProjectComponent(projectName)));
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
}
