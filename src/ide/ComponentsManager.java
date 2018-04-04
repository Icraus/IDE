/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ide;

import com.sun.javafx.collections.ObservableListWrapper;
import icraus.Components.ClassComponent;
import icraus.Components.ComponentPlugin;
import icraus.Components.IfComponentPlugin;
import icraus.Components.MethodComponent;
import icraus.Components.SimpleComponentPlugin;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Shoka
 */
public class ComponentsManager {

    private static ComponentsManager instance = new ComponentsManager();
    private ObservableList<ComponentPlugin> pluginList = new ObservableListWrapper<>(new ArrayList<>());

    public static ComponentsManager getInstance() {
        return instance;
    }
    

    private ComponentsManager() {
        SimpleComponentPlugin plugin = new SimpleComponentPlugin("Class", "Containers", new ClassComponent(), null);
        addComponent(plugin);
        plugin = new IfComponentPlugin("If", "inner blocks");
        addComponent(plugin);
        addComponent(new SimpleComponentPlugin("Method", "inner blocks", new MethodComponent(), null));
        
    }
    public void addComponent(ComponentPlugin plugin){
        pluginList.add(plugin);
    }
    public ObservableList<ComponentPlugin> getPluginList() {
        return pluginList;
    }
    
}
