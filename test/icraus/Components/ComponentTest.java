/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

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
        Component c = new ClassComponent("A", "B");
        String uuid = c.getUUID();
        assertEquals(uuid.startsWith("CLASS"), true);
    }

    
    
}
