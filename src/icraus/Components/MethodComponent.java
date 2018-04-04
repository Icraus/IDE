/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import com.icraus.vpl.codegenerator.CodeBlock;
import com.icraus.vpl.codegenerator.CodeBlockBody;
import com.icraus.vpl.codegenerator.DeclareExpression;
import com.icraus.vpl.codegenerator.MethodCodeBlockHead;
import com.icraus.vpl.codegenerator.Statement;
import com.sun.javafx.collections.ObservableListWrapper;
import ide.UiManager;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;

/**
 *
 * @author Shoka
 */
public class MethodComponent extends Component implements Pageable{

    public static final String METHOD_TYPE = "METHOD";
    private StringProperty methodName;
    private StringProperty methodReturnType;
    private StringProperty accessType;
    //TODO add Params functionality
    private ObservableList<DeclareExpression> params;
    private MethodCodeBlockHead head;
    private CodeBlockBody body;
    private Tab methodTab;
    private MethodLineLabel lineLabel;

    public MethodComponent() {
        super();
//FIXME Fix this 
        setUiDelegate(new MethodContentPane(this));
        accessType = new SimpleStringProperty();
        methodName = new SimpleStringProperty();
        methodReturnType = new SimpleStringProperty();
        params = new ObservableListWrapper<>(new ArrayList<>());
        head = new MethodCodeBlockHead();
        body = new CodeBlockBody();
        CodeBlock blk = new CodeBlock();    
        blk.setBody(body);
        blk.setHead(head);
        setStatement(blk);
        lineLabel = new MethodLineLabel(this);
        methodTab = new Tab();
        methodTab.contentProperty().bindBidirectional(getUiDelegate());
        UiManager.getInstance().addTab(methodTab);
        createListners();
    }

    public MethodComponent(String _name, String _returnType, String _access) {
        this();
        setMethodName(_name);
        setMethodReturnType(_returnType);
        setAccessType(_access);
    }

    public StringProperty getMethodName() {
        return methodName;
    }

    public void setMethodName(StringProperty methodName) {
        this.methodName = methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName.setValue(methodName);
    }

    public StringProperty getMethodReturnType() {
        return methodReturnType;
    }

    public void setMethodReturnType(StringProperty methodReturnType) {
        this.methodReturnType = methodReturnType;
    }

    public void setMethodReturnType(String methodReturnType) {
        this.methodReturnType.setValue(methodReturnType);
    }

    public ObservableList<DeclareExpression> getParams() {
        return params;
    }

    public void setParams(ObservableList<DeclareExpression> params) {
        this.params = params;
    }

    public MethodCodeBlockHead getHead() {
        return head;
    }

    public void setHead(MethodCodeBlockHead head) {
        this.head = head;
    }

    public CodeBlockBody getBody() {
        return body;
    }

    public void setBody(CodeBlockBody body) {
        this.body = body;
    }
    @Override
    public Tab getTab() {
        return methodTab;
    }

    public void setMethodTab(Tab methodTab) {
        this.methodTab = methodTab;
    }

    public MethodLineLabel getLineLabel() {
        return lineLabel;
    }

    public void setLineLabel(MethodLineLabel lineLabel) {
        this.lineLabel = lineLabel;
    }

    public StringProperty getAccessType() {
        return accessType;
    }

    public void setAccessType(StringProperty accessType) {
        this.accessType = accessType;
    }

    public void setAccessType(String _access) {
        accessType.setValue(_access);
    }

    @Override
    public String getType() {
        return METHOD_TYPE;
    }

    @Override
    public String toString() {
        return getMethodName().getValue();
    }

    @Override
    public TreeItem<Component> toTreeItem() {
        TreeItem<Component> root = new TreeItem<>(this);
        for (Component itm : getChildern()) {
            TreeItem<Component> sim = new TreeItem<>(itm);
            root.getChildren().add(sim);
        }
        return root;
    }

    protected void createListners() {
        methodTab.contentProperty().bindBidirectional(getUiDelegate());
        methodName.bindBidirectional(methodTab.textProperty());
        methodName.addListener(e -> {
            methodChanged();
        });
        methodReturnType.addListener(e -> {
            methodChanged();
        });
        getChildern().addListener((Observable e) -> {
            methodContentChanged();
        });
    }

    private void methodContentChanged() {
        List<Statement> children = body.getChildren();
        children.clear();
        for (Component c : getChildern()) {
            children.add(c.getStatement().getValue());
        }
    }

    private void methodChanged() {
        String str = methodName.getValue();
        head.setMethodName(str);
        head.setReturnType(methodReturnType.getValue());
        lineLabel.setText(str, methodReturnType.getValue(), getAccessType().getValue());
    }
}
