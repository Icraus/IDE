/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import com.icraus.ide.ui.components.DraggableCanvasComponentEventHandler;
import com.icraus.vpl.codegenerator.CodeBlock;
import com.icraus.vpl.codegenerator.CodeBlockBody;
import com.icraus.vpl.codegenerator.ForCodeBlockHead;
import com.icraus.vpl.codegenerator.SimpleStatement;
import com.icraus.vpl.codegenerator.WhileCodeBlockHead;
import ide.UiManager;
import javafx.beans.Observable;
import javafx.scene.input.MouseEvent;
import test.CallMethod;
import test.Comment;
import test.ForLoop;
import test.InputValue;
import test.Item;
import test.OutputExpression;
import test.WhileLoop;

public class ComponentPluginFactories extends SimpleComponentPlugin {

    private ComponentPluginFactories() {
    }

    public static ComponentPlugin createIfComponentPlugin(String componentName, String sectionName) {

        return new SimpleComponentPlugin(componentName,
                sectionName,
                null,
                () -> {
                    return SimpleComponentTabbed.createIfStatement();
                },
                (c) -> {
                });
    }

    public static ComponentPlugin createForComponentPlugin(String name, String section) {
        return new SimpleComponentPlugin(name,
                section,
                null,
                () -> {
                    CodeBlock blk = new CodeBlock();
                    blk.setHead(new ForCodeBlockHead());
                    blk.setBody(new CodeBlockBody());
                    SimpleComponentTabbed comp = new SimpleComponentTabbed(blk, null, "For_Statment");
                    comp.getChildern().addListener((Observable e) -> { //TODO Generalize and refactor to remove duplication
                        CodeBlockBody body = blk.getBody();
                        body.getChildren().clear();
                        for (Component c : comp.getChildern()) {
                            body.getChildren().add(c.getStatement().get());
                        }
                    });
                    Item nod = new ForLoop(comp); //TODO change For Loop Properties to allow code change
                    nod.addEventFilter(MouseEvent.DRAG_DETECTED, new DraggableCanvasComponentEventHandler());
                    nod.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                        UiManager.getInstance().selectTabByUuid(comp.getUUID());
                    });
                    comp.setUiDelegate(nod);

                    return comp;
                },
                (c) -> {
                });
    }

    public static ComponentPlugin createInputValue(String name, String section) {
        return new SimpleComponentPlugin(name, section, null, () -> {
            SimpleStatement s = new SimpleStatement("");
            SimpleComponent c = new SimpleComponent(s, null, "InputStatement");
            c.setUiDelegate(new InputValue(c));

            return c;
        });
    }

    public static ComponentPlugin createComment(String name, String section) {
        return new SimpleComponentPlugin(name, section, null, () -> {
            SimpleStatement s = new SimpleStatement("");
            SimpleComponent c = new SimpleComponent(s, null, "Comment");
            c.setUiDelegate(new Comment(c));
            return c;
        });
    }

    public static ComponentPlugin createCallMethod(String name, String section) {
        return new SimpleComponentPlugin(name, section, null, () -> {
            SimpleStatement s = new SimpleStatement("");
            SimpleComponent c = new SimpleComponent(s, null, "CallMethod");
            c.setUiDelegate(new CallMethod(c));
            return c;
        });
    }

    public static ComponentPlugin createOutput(String name, String section) {
        return new SimpleComponentPlugin(name, section, null, () -> {
            SimpleStatement s = new SimpleStatement("");
            SimpleComponent c = new SimpleComponent(s, null, "Output");
            c.setUiDelegate(new OutputExpression(c));
            return c;
        });
    }

    public static ComponentPlugin createWhileComponentPlugin(String componentName, String sectionName) {

        return new SimpleComponentPlugin(componentName,
                sectionName,
                null,
                () -> {
                    SimpleComponentTabbed comp = new SimpleComponentTabbed(new WhileCodeBlockHead(), null, "WHILE");
                    WhileLoop nod = new WhileLoop(comp);
                    comp.setUiDelegate(nod);
                    return comp;
                },
                (c) -> {
                });
    }
}
