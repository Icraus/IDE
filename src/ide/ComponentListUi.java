/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ide;

import com.sun.javafx.collections.ObservableMapWrapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.Observable;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Shoka
 */
public class ComponentListUi extends VBox implements MapChangeListener<Object, Object> {

    private ObservableMap<String, TitledPane> sections = new ObservableMapWrapper<>(new HashMap());
    @FXML
    private Accordion rootAccordionPane;
    @FXML
    private Label lbl;

    public Accordion getRootAccordionPane() {
        return rootAccordionPane;
    }

    public Map<String, TitledPane> getSections() {
        return sections;
    }

//    public void setSections(Map<String, TitledPane> sections) {
//        this.sections = sections;
//    }
    public void setSections(ObservableMap<String, TitledPane> sections) {
        this.sections = sections;
    }

    public ComponentListUi() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ComponentUi.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        sections.addListener((Observable e) -> {
            loadPanes();
        });

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    protected void addDraggableEventHandler(Node lbl) {
//        lbl.addEventHandler(MouseEvent.DRAG_DETECTED, new DraggableComponentEventHandler());

    }

    protected void loadPanes() {
        rootAccordionPane.getPanes().clear();
        for (String key : sections.keySet()) {
            TitledPane p = sections.get(key);
            p.setText(key);
            rootAccordionPane.getPanes().add(p);
        }
    }

    protected void addNodeToSection(Node n, String section) {
        TitledPane section1 = getSection(section);
        Pane content = (Pane) section1.getContent();
        content.getChildren().add(n);
    }

    private TitledPane getSection(String section) {
        boolean cond = sections.containsKey(section);
        TitledPane cu = null;
        if (cond) {
            cu = sections.get(section);
        } else {
            cu = new TitledPane();
            cu.setContent(new VBox());
            sections.put(section, cu);
        }
        return cu;
    }

    protected void loadComponenets() {
//        Collection<ComponentPlugin> coms = ComponentController.getInstance().getAllComponents();
//        for (ComponentPlugin c : coms) {
//            Node n = new Label(c.getName(), c.getIcon());
//            n.setUserData(c.getGUIUUID());
//            addNodeToSection(n, c.getSection());
//            addDraggableEventHandler(n);
//        }
    }

    @FXML
    private void initialize() {
        loadComponenets();
//        loadPanes();
    }

    @Override
    public void onChanged(Change<? extends Object, ? extends Object> change) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO add observer for component changes
    }

}
