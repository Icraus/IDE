/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.shape.Line;

/**
 *
 * @author Shoka
 */
public class LinkNode extends Line {

    private LinkComponent parent;
    private ObjectProperty<Node> previousNode;
    private ObjectProperty<Node> nextNode;
    
    private LinkNode() {
        super();
        nextNode = new SimpleObjectProperty<>();
        previousNode = new SimpleObjectProperty<>();
        
    }

    public LinkNode(LinkComponent _parent) {
        this();
        this.parent = _parent;
        createBinding();
    }
    private void createBinding(){
        parent.getNextComponent().getValue().getUiDelegate().bindBidirectional(nextNode);
        parent.getPreviousComponent().getValue().getUiDelegate().bindBidirectional(previousNode);
        startXProperty().bindBidirectional(previousNode.getValue().layoutXProperty());
        startYProperty().bindBidirectional(previousNode.getValue().layoutYProperty());
        endXProperty().bindBidirectional(nextNode.getValue().layoutXProperty());
        endXProperty().bindBidirectional(nextNode.getValue().layoutYProperty());
    }

    public ObjectProperty<Node> getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(ObjectProperty<Node> previousNode) {
        this.previousNode = previousNode;
    }
    public void setPreviousNode(Node _previousNode) {
        this.previousNode.setValue(_previousNode);
    }

    public ObjectProperty<Node> getNextNode() {
        return nextNode;
    }

    public void setNextNode(ObjectProperty<Node> nextNode) {
        this.nextNode = nextNode;
    }
    
    public void setNextNode(Node _nextNode) {
        this.nextNode.setValue(_nextNode);
    }

    
}
