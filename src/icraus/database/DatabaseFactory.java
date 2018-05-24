/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database;

import icraus.Components.ComponentPlugin;
import icraus.Components.SimpleComponent;
import icraus.Components.SimpleComponentPlugin;
import icraus.database.ui.InsertComponentUi;

/**
 *
 * @author Shoka
 */
public class DatabaseFactory {
    public static final String INSTERT_TYPE = "INSERT_DATABASE";
    public static ComponentPlugin createInsertPlugin() {

        SimpleComponentPlugin p = new SimpleComponentPlugin("Insert", "Database", null,() -> {
            SimpleComponent t = new SimpleComponent(new InsertStatement(), null, INSTERT_TYPE);
            t.setUiDelegate(new InsertComponentUi(t));
            return t;
        });
        return p;
    }
}
