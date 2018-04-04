/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Labeled;

/**
 *
 * @author Shoka
 */
public interface ComponentPlugin {
    public Component createComponent() throws IllegalComponentInstantiation;
    public String getSectionName();
    public String getComponentName();
    public Node getIcon();
    public void initalize(Component n);
}
