/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ide;

import com.plugins.exception.PluginErrorLoadingException;
import com.plugins.exception.PluginNotFoundException;
import com.plugins.exception.PluginProperiesNotFound;
import com.plugins.pluginloader.JarPluginLoader;
import com.sun.javafx.collections.ObservableListWrapper;
import icraus.Components.ClassComponent;
import icraus.Components.ComponentPlugin;
import icraus.Components.ComponentPluginFactories;
import icraus.Components.SimpleComponentPlugin;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public void loadPluginsDir(String dirName) {
        Path get = Paths.get("F:\\Important\\Code\\Graduation_Project\\IDE", "plugin");
        List<Path> result = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(get, "*.{jar}")) {
            for (Path entry : stream) {
                result.add(entry);
            }
        } catch (DirectoryIteratorException ex) {
            // I/O error encounted during the iteration, the cause is an IOException
//            throw ex.getCause();
        } catch (IOException ex) {
            Logger.getLogger(ComponentsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        JarPluginLoader l = new JarPluginLoader();
        ComponentPlugin p = null;
        try {
            p = (ComponentPlugin)l.loadPlugin(result.get(0).toString());
        } catch (PluginNotFoundException ex) {
            Logger.getLogger(ComponentsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PluginErrorLoadingException ex) {
            Logger.getLogger(ComponentsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PluginProperiesNotFound ex) {
            Logger.getLogger(ComponentsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        addComponent(p);
    }

    private ComponentsManager() {
        ComponentPlugin plugin = new SimpleComponentPlugin("Class", "Containers", new ClassComponent(), null);
        addComponent(plugin);
        plugin = ComponentPluginFactories.createIfComponentPlugin("If", "inner blocks");
        addComponent(plugin);
        plugin = ComponentPluginFactories.createForComponentPlugin("For", "inner blocks");
        addComponent(plugin);
        plugin = ComponentPluginFactories.createInputValue("Input", "IO Operations");
        addComponent(plugin);
        addComponent(ComponentPluginFactories.createComment("Comment", "Others"));
        addComponent(ComponentPluginFactories.createCallMethod("Call Method", "Others"));
        addComponent(ComponentPluginFactories.createOutput("Output", "IO Operations"));
        addComponent(ComponentPluginFactories.createWhileComponentPlugin("While", "inner blocks"));
//        addComponent(DatabaseFactory.createInsertPlugin());
        loadPluginsDir(null);

    }

    public void addComponent(ComponentPlugin plugin) {
        pluginList.add(plugin);
    }

    public ObservableList<ComponentPlugin> getPluginList() {
        return pluginList;
    }

}
