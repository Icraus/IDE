/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import com.icraus.vpl.codegenerator.Statement;
import javafx.scene.Node;

/**
 *
 * @author Shoka
 */
public class SimpleComponent extends Component{

    private String type = "";

    public SimpleComponent() {
        
    }

    
    public SimpleComponent(Statement s, Node delegate, String _type) {
        super();
        setStatement(s);
        setUiDelegate(delegate);
        type = _type;
    }
    

    @Override
    public String getType() {
        return "SIMPLE_COMPONENT";
    }

    @Override
    public String toString() {
        return type;
    }

}
