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
public class SimpleComponent extends Component {

    private String type = "";
    private String componentString = "";

    public SimpleComponent() {
        super();
    }

    public SimpleComponent(Statement s, Node delegate, String _type) {
        super();
        setStatement(s);
        setUiDelegate(delegate);
        type = _type;
        componentString = _type;
    }

    public SimpleComponent(Statement s, Node delegate, String _type, String str) {
        super();
        setStatement(s);
        setUiDelegate(delegate);
        type = _type;
        componentString = str;

    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return componentString;
    }

}
