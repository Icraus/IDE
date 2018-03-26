/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Shoka
 */
public class MethodLineLabel extends Label {
    final private MethodComponent parent;
    public MethodLineLabel(MethodComponent d){
        super();
        parent = d;
        setEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if(e.getClickCount() == 2){
                Tab t = parent.getMethodTab();
                t.getTabPane().getSelectionModel().select(t);
            }
        });
    }
    public MethodLineLabel(String name, String returnType, String accessType, MethodComponent d){
        this(d);
        setText(name, returnType, accessType);
        
    }
    public void setText(String name, String returnType, String accessType){
        //TODO add Access Type Specifier and Parameters
        super.setText(name + " (): " + returnType);
        
    }
    
    
}
