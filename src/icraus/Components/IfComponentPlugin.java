/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

public class IfComponentPlugin extends SimpleComponentPlugin {

    public IfComponentPlugin(String componentName, String sectionName) {
        super(componentName, sectionName, null, null);

    }

    @Override
    public Component createComponent() throws IllegalComponentInstantiation {
        return SimpleComponent.createIfStatement();
    }

}
