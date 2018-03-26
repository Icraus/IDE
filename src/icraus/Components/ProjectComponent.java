/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TreeItem;

/**
 *
 * @author Shoka
 */
public class ProjectComponent extends Component{
    private StringProperty projectName;
    public static final String PROJECT_TPYE = "PROJECT_TPYE";

    public ProjectComponent(String name) {
        projectName = new SimpleStringProperty();
        projectName.setValue(name);
    }

    @Override
    public String toString() {
        return projectName.getValue();
    }

    @Override
    public String getType() {
        return PROJECT_TPYE;
    }

//    @Override
//    public void addComponent(Component c) throws IllegalComponent {
//        super.addComponent(c); //To change body of generated methods, choose Tools | Templates.
//        //TODO add ProjectCompoent
//    }

    
    @Override
    public TreeItem<Component> toTreeItem() {
        return new TreeItem<>(this);
    }
}
