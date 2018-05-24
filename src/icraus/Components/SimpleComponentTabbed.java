/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import com.icraus.ide.ui.components.DraggableCanvasComponentEventHandler;
import com.icraus.vpl.codegenerator.CodeBlock;
import com.icraus.vpl.codegenerator.CodeBlockBody;
import com.icraus.vpl.codegenerator.CodeBlockHead;
import com.icraus.vpl.codegenerator.IfCodeBlockHead;
import com.icraus.vpl.codegenerator.Statement;
import ide.UiManager;
import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import test.IfStatement;

public class SimpleComponentTabbed extends SimpleComponent implements Pageable {

    private Tab tab;

    public SimpleComponentTabbed() {
    }

    public SimpleComponentTabbed(Statement s, Node delegate, String _type) {
        super(s, delegate, _type);
        tab = new Tab(_type, new ScrollAnchorPane(this));
    }

    public SimpleComponentTabbed(CodeBlockHead head, Node delegate, String _type) {
        super(null, delegate, _type);
        tab = new Tab("", new ScrollAnchorPane(this));
        CodeBlock blk = new CodeBlock();
        blk.setHead(head);
        blk.setBody(new CodeBlockBody());
        setStatement(blk);
        getChildern().addListener((Observable e) -> {
            CodeBlockBody body = blk.getBody();
            body.getChildren().clear();
            for (Component c : getChildern()) {
                body.getChildren().add(c.getStatement().get());
            }
        });
        
    }

    public static Component createIfStatement() {
        
//        SimpleComponentTabbed comp = new SimpleComponentTabbed(new IfCodeBlockHead(), null, "If_Statment");
        CodeBlock blk = new CodeBlock();
        blk.setHead(new IfCodeBlockHead());
        blk.setBody(new CodeBlockBody());
        SimpleComponentTabbed comp = new SimpleComponentTabbed(blk, null, "If_Statment");
        comp.getChildern().addListener((Observable e) -> {
            CodeBlockBody body = blk.getBody();
            body.getChildren().clear();
            for (Component c : comp.getChildern()) {
                body.getChildren().add(c.getStatement().get());
            }
        });
        IfStatement nod = new IfStatement(comp);
//        nod.addEventFilter(MouseEvent.DRAG_DETECTED, new DraggableCanvasComponentEventHandler());
        nod.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            UiManager.getInstance().getTabByUuid(comp.getUUID());
        });
        comp.setUiDelegate(nod);

        return comp;
    }

    @Override
    public Tab getTab() {
        return tab;
    }

}
