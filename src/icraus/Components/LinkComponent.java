/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TreeItem;

/**
 *
 * @author Shoka
 */
public class LinkComponent extends Component{
    private ObjectProperty<Component> previousComponent;

    
    private ObjectProperty<Component> nextComponent;
//TODO identify Link Type
//    enum LINK_TYPE {
//    }
    public LinkComponent(){
        previousComponent = new SimpleObjectProperty<>();
        nextComponent = new SimpleObjectProperty<>();
    }
    public ObjectProperty<Component> getPreviousComponent() {
        return previousComponent;
    }

    public void setPreviousComponent(ObjectProperty<Component> previousComponent) {
        this.previousComponent = previousComponent;
    }

    public ObjectProperty<Component> getNextComponent() {
        return nextComponent;
    }

    public void setNextComponent(ObjectProperty<Component> nextComponent) {
        this.nextComponent = nextComponent;
    }

    @Override
    public String getType() {
        return LINK_TYPE;
    }
    public static final String LINK_TYPE = "LINK";

    @Override
    public String toString() {
        return previousComponent.get().toString() + "-" + nextComponent.get().toString();
    }

    @Override
    public TreeItem<Component> toTreeItem() {
        return new TreeItem<>(this);
    }

    @Override
    public String addComponent(Component c) throws IllegalComponent{
        throw new IllegalComponent();
    }
    
    
    
}
