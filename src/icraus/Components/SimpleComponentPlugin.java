/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;


public class SimpleComponentPlugin implements ComponentPlugin {

    private String componentName;
    private String sectionName;
    private Node graphic;
    private Component component;

    public SimpleComponentPlugin(String componentName, String sectionName, Component component, Node graphic) {
        this.componentName = componentName;
        this.sectionName = sectionName;
        this.graphic = graphic;
        this.component = component;
    }
    
    @Override
    public Component createComponent() throws IllegalComponentInstantiation{
        try {
            Component tmp = component.getClass().newInstance();
            initalize(tmp);
            return tmp;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SimpleComponentPlugin.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalComponentInstantiation("Can't create " + getComponentName());
        } 
    }

    @Override
    public String getSectionName() {
        return sectionName;
    }

    @Override
    public String getComponentName() {
        return componentName;
    }

    @Override
    public Node getIcon() {
        return graphic;
    }

    @Override
    public void initalize(Component n) {
        
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public void setGraphic(Node graphic) {
        this.graphic = graphic;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
    
}
