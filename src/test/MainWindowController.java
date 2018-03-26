
package test;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainWindowController{  
    
    private ArrayList<Item> mainMethod = new ArrayList<>(5); 
    @FXML
    private Button btnFor;
    @FXML
    private Button btnIf;
    @FXML
    private Button btnWhile;
    @FXML
    private Button btnDo;
    @FXML
    private Button btnDeclare;
    @FXML
    private Button btnAssign;
    @FXML
    private Button btnInput;
    @FXML
    private Button btnOutput;
    @FXML
    private Button btnCall;
    @FXML
    private Button btnComment;
    @FXML
    private VBox mainPage;
        
    @FXML
    private void addNewItem(ActionEvent event) {
        Button source = (Button)event.getSource();
        if(source == btnFor)
            mainMethod.add(new ForLoop());
        else if(source == btnIf)
            ;//FIXME
//            mainMethod.add(new IfStatement());
        else if(source == btnWhile)
            mainMethod.add(new WhileLoop());
        else if(source == btnDo)
            mainMethod.add(new DoLoop());
        else if(source == btnDeclare)
            mainMethod.add(new DeclareVariable());
        else if(source == btnAssign)
            mainMethod.add(new AssignValue());
        else if(source == btnInput)
            mainMethod.add(new InputValue());
        else if(source == btnOutput)
            mainMethod.add(new OutputExpression());
        else if(source == btnCall)
            mainMethod.add(new CallMethod());
        else if(source == btnComment)
            mainMethod.add(new Comment());
        redraw();
    }  
    public void initialize(){
        btnFor.getStyleClass().add("forLoopStyle");
        btnIf.getStyleClass().add("ifStatementStyle");
        btnWhile.getStyleClass().add("whileLoopStyle");
        btnDo.getStyleClass().add("doLoopStyle");
        btnDeclare.getStyleClass().add("declareVariableStyle");
        btnAssign.getStyleClass().add("assignValueStyle");
        btnInput.getStyleClass().add("inputValueStyle");
        btnOutput.getStyleClass().add("outputExpressionStyle");
        btnCall.getStyleClass().add("callMethodStyle");
        btnComment.getStyleClass().add("commentStyle");
    }
    public void deleteItem(String uuid){
        mainMethod.remove(findByUUID(uuid));
        redraw();
    }
    private void redraw(){
        mainPage.getChildren().clear();
        for(Item u : mainMethod)
            mainPage.getChildren().add(u);
    }
    public Item getItem(int index){
        return mainMethod.get(index);
    }
    public int findByUUID(String uuid){
        for(int i=0;i<mainMethod.size();i++)
            if(mainMethod.get(i).getUUID().equals(uuid))
                return i;                                 
        return -1;
    }    
}
