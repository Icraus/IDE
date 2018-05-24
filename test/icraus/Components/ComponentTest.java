/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import ide.ComponentsManager;
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
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shoka
 */
public class ComponentTest {
    
    public ComponentTest() {
    }

    /**
     * Test of getType method, of class Component.
     */
    @Test
    public void testGetType() {
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
        System.out.println(result.get(0));
    }

    
    
}
