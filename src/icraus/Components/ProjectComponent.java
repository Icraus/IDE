/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import com.sun.javafx.collections.ObservableListWrapper;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;

/**
 *
 * @author Shoka
 */
public class ProjectComponent extends Component implements Pageable {

    private StringProperty projectName;
//    private ObservableMap<String, ClassComponent> model;
    private ObjectProperty<TreeItem<Component>> root;

    public static final String PROJECT_TPYE = "PROJECT_TPYE";
    private ObjectProperty<Tab> projectMainTab;

    public ProjectComponent(String name) {
        super();
        projectName = new SimpleStringProperty();
        projectName.setValue(name);
        ScrollAnchorPane pane = new ScrollAnchorPane(this);
        Tab tab = new Tab("", pane);
        setUiDelegate(pane);
        tab.textProperty().bindBidirectional(projectName);
        projectMainTab = new SimpleObjectProperty<>(tab);
//        this.model = new ObservableMapWrapper<>(new HashMap<>());
        root = new SimpleObjectProperty<>();
        getChildern().addListener((Observable e) -> {
            calculateRoot();
        });

    }

    @Override
    public String toString() {
        return projectName.getValue();
    }

    @Override
    public String getType() {
        return PROJECT_TPYE;
    }

    public Component getComponentByUuid(String uuid) throws ComponentNotFoundException {
        if (getUUID() == uuid) {
            return this;
        }
//        ClassComponent comp = model.get(uuid);
//        if (comp != null) {
//            return comp;
//        }
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

    public String addClass(String className, String packageName) throws IllegalComponent {
        ClassComponent c = new ClassComponent(className, packageName);
        addComponent(c);
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
        //TODO DONE add remove by finding
        ClassComponent comp = (ClassComponent) getComponentByUuid(uuid);
        if (comp != null) {
            return true;
        }
        return false;
    }

    public ObservableList<MethodComponent> getAllMethods() {
        ObservableList<MethodComponent> lst = new ObservableListWrapper<>(new ArrayList<>());
        ObservableList<ClassComponent> clst = toList();
        for (ClassComponent c : clst) {
            lst.addAll(c.getMethodsList());
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
        return getComponentByUuid(uuid).addComponent(c);

    }

    public ObjectProperty<TreeItem<Component>> treeItemsProperty() {
        return root;
    }

    public void calculateRoot() {
        ObservableList<ClassComponent> lst = toList(); //FIXME change project name
        root.setValue(new TreeItem<>(this));
        for (Component c : lst) {
            TreeItem<Component> itm = c.toTreeItem();
            root.get().getChildren().add(itm);
        }
    }

    public ObservableList<ClassComponent> toList() {
        ObservableList<Component> lst = getChildern();
        ObservableList<ClassComponent> clst = FXCollections.observableArrayList();
        for(Component c : lst){
            clst.add((ClassComponent)c);
        }
        return clst;
    }

    @Override
    public String addComponent(Component c) throws IllegalComponent {
        if (c.getType() == ClassComponent.CLASS_TYPE) {
            return super.addComponent(c);
        } //To change body of generated methods, choose Tools | Templates.
        throw new IllegalComponent("Not a Class Type");
    }

    @Override
    public TreeItem<Component> toTreeItem() {
        return root.getValue();
    }

    @Override
    public Tab getTab() {
        return projectMainTab.getValue();
    }

    
}
