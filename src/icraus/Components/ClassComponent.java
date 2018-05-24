/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import com.icraus.vpl.codegenerator.ClassCodeBlockHead;
import com.icraus.vpl.codegenerator.CodeBlock;
import com.icraus.vpl.codegenerator.CodeBlockBody;
import com.sun.javafx.collections.ObservableListWrapper;
import java.util.ArrayList;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class ClassComponent extends Component {

    public static final String CLASS_TYPE = "CLASS";
    private StringProperty className;
    private StringProperty packageName;
    private ClassCodeBlockHead head;
    private CodeBlockBody body;
    private ObservableList<MethodComponent> methodsList;
    private ObservableList<SimpleComponent> fieldsList;
    
    public ClassComponent() {
        super();
        className = new SimpleStringProperty("");
        packageName = new SimpleStringProperty("");
        head = new ClassCodeBlockHead();
        body = new CodeBlockBody();
        methodsList = new ObservableListWrapper<>(new ArrayList<>());
        fieldsList = new ObservableListWrapper<>(new ArrayList<>());
        CodeBlock block = new CodeBlock();
        block.setBody(body);
        block.setHead(head);
        setStatement(block);
        setUiDelegate(new ClassContentPane(this));
        createListners();
    }

    public ClassComponent(String className, String packageName) {
        this();
        this.className.setValue(className);
        this.packageName.setValue(packageName);

    }

    @Override
    public int getFlags() {
        return super.getFlags() | ComponentFlags.DRAGGABLE_FLAG; //To change body of generated methods, choose Tools | Templates.
    }
    
    protected void createListners() {
        className.addListener(e -> {
            changeClassHead();
        });
        methodsList.addListener((Observable e) -> {
            methodListChanged();
            
        });
        fieldsList.addListener((Observable e) -> {
            methodListChanged();
//            childrenChanged();
        });
        packageName.addListener((Observable e) -> {
            packageNameChanged();
        });

    }
    //TODO Replace it with IOC
    private void childrenChanged() {
        getChildern().clear();
        getChildern().addAll(fieldsList);
        getChildern().addAll(methodsList);
    }

    private void methodListChanged() {
        body.getChildren().clear();
        for (MethodComponent c : methodsList) {
            body.getChildren().add(c.getStatement().get());
        }
    }

    private void changeClassHead() {
        head.setClassName(className.getValue());
    }

    public StringProperty getClassName() {
        return className;
    }

    public void setClassName(StringProperty className) {
        this.className = className;
    }

    public void setClassName(String v) {
        this.className.setValue(v);
    }

    public StringProperty getPackageName() {
        return packageName;
    }

    public void setPackageName(StringProperty packageName) {
        this.packageName = packageName;
    }

    public ClassCodeBlockHead getHead() {
        return head;
    }

    public void setHead(ClassCodeBlockHead head) {
        this.head = head;
    }

    public CodeBlockBody getBody() {
        return body;
    }

    public void setBody(CodeBlockBody body) {
        this.body = body;
    }

    public ObservableList<MethodComponent> getMethodsList() {
        return methodsList;
    }

    public void setMethodsList(ObservableList<MethodComponent> methodsList) {
        this.methodsList = methodsList;
    }

    public ObservableList<SimpleComponent> getFieldsList() {
        return fieldsList;
    }

    public void setFieldsList(ObservableList<SimpleComponent> fieldsList) {
        this.fieldsList = fieldsList;
    }

    @Override
    public String getType() {
        return CLASS_TYPE;
    }

    @Override
    public String toString() {
        return className.getValue();
    }

    @Override
    public TreeItem<Component> toTreeItem() {
        TreeItem<Component> root = new TreeItem<>(this);
        for (Component c : fieldsList) {
            root.getChildren().add(c.toTreeItem());

        }
        for (Component c : methodsList) {
            root.getChildren().add(c.toTreeItem());
        }
        return root;
    }

    @Override
    public String addComponent(Component c) throws IllegalComponent{
        if(c.getType() == MethodComponent.METHOD_TYPE){
            super.addComponent(c);
            getMethodsList().add((MethodComponent)c); //TODO add children method Listener
            return c.getUUID();
        }
        throw new IllegalComponent();
        //TODO add field Component
    }

    private void packageNameChanged() {
        head.setPackageName(getPackageName().get());
    }

}
