/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import com.icraus.ide.ui.components.DraggableCanvasComponentEventHandler;
import com.icraus.vpl.codegenerator.CodeBlock;
import com.icraus.vpl.codegenerator.CodeBlockBody;
import com.icraus.vpl.codegenerator.IfCodeBlockHead;
import com.icraus.vpl.codegenerator.Statement;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import test.IfStatement;

/**
 *
 * @author Shoka
 */
public class SimpleComponent extends Component{

    public SimpleComponent() {
    }

    
    public SimpleComponent(Statement s, Node delegate) {
        super();
        setStatement(s);
        setUiDelegate(delegate);
    }
    public static Component createIfStatement(){
        CodeBlock blk = new CodeBlock();
        blk.setHead(new IfCodeBlockHead());
        blk.setBody(new CodeBlockBody());
        SimpleComponent comp = new SimpleComponent(blk, null);
        IfStatement nod = new IfStatement(comp);
        nod.addEventFilter(MouseEvent.DRAG_DETECTED, new DraggableCanvasComponentEventHandler());
        comp.setUiDelegate(nod);
        
        return comp;
    }

    @Override
    public String getType() {
        return "SIMPLE_COMPONENT";
    }

    @Override
    public String toString() {
        return getType();
    }

    @Override
    public TreeItem<Component> toTreeItem() {
        TreeItem<Component> root = new TreeItem<>(this);
        return root;
    }

}
