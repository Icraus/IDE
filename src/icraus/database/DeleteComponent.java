/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database;

import icraus.database.ui.DeleteUi;
import icraus.Components.SimpleComponent;

/**
 *
 * @author Shoka
 */
public class DeleteComponent extends SimpleComponent{

    public DeleteComponent() {
        super(new DeleteStatement(), null, "DELETE_DATABASE");
        setUiDelegate(new DeleteUi(this));
    }
    
}
