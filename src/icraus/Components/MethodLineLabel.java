/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import ide.UiManager;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;

/**
 *
 * @author Shoka
 */
public class MethodLineLabel extends Button implements Selectable{

    final private MethodComponent parent;

    public MethodLineLabel(MethodComponent d) {
        super();
        parent = d;
        setOnAction(e -> {
            UiManager.getInstance().setCurrentTabByUuid(parent.getUUID());
        });
        
    }

    public MethodLineLabel(String name, String returnType, String accessType, MethodComponent d) {
        this(d);
        setText(name, returnType, accessType);

    }

    public void setText(String name, String returnType, String accessType) {
        //TODO add Access Type Specifier and Parameters
        super.setText(name + " (): " + returnType);

    }

    @Override
    public String getParentComponentUuid() {
        return parent.getUUID();
    }

}
