
package test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditWindowController {
    
    private int type = Item.getCurrentType();
    private String uuid = Item.getCurrentUUID();
    private boolean error = false;
    @FXML
    private Button btnApply;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtArrayLength;
    @FXML
    private TextField txtVariable;
    @FXML
    private TextField txtValue;
    @FXML
    private TextField txtExpression;
    @FXML
    private TextField txtStart;
    @FXML
    private TextField txtCondition;
    @FXML
    private TextField txtStep;
    @FXML
    private TextField txtMethod;
    @FXML
    private TextField txtComment;
    @FXML
    private CheckBox chkIsArray;
    
    @FXML
    private void applyNewValues(ActionEvent event) {
//        switch (type){
//            case 1:
//                ForLoop f = (ForLoop)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                f.setParameters(txtVariable.getText(), txtStart.getText(), txtCondition.getText(), txtStep.getText());
//                break;
//            case 2:
//                IfStatement i = (IfStatement)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                i.setCondition(txtCondition.getText());
//                break;
//            case 3:
//                WhileLoop w = (WhileLoop)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                w.setCondition(txtCondition.getText());
//                break;
//            case 4:
//                DoLoop d = (DoLoop)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                d.setCondition(txtCondition.getText());
//                break;
//            case 5:
//                DeclareVariable de = (DeclareVariable)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                if(chkIsArray.isSelected())
//                    if(!isPositiveInteger(txtArrayLength.getText())){
//                        error = true;
//                        break;
//                    }
//                    else{
//                        de.setIsArray(true);
//                        de.setArrayLength(Integer.parseInt(txtArrayLength.getText()));
//                        error = false;
//                    }
//                else
//                    de.setIsArray(false);
//                de.setType(txtType.getText());
//                de.setName(txtName.getText());
//                break;
//            case 6:
//                AssignValue a = (AssignValue)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                a.setParameters(txtVariable.getText(),txtValue.getText());
//                break;
//            case 7:
//                InputValue in = (InputValue)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                in.setVariable(txtVariable.getText());
//                break;
//            case 8:
//                OutputExpression o = (OutputExpression)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                o.setExpression(txtExpression.getText());
//                break;
//            case 9:
//                CallMethod c = (CallMethod)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                c.setMethod(txtMethod.getText());
//                break;
//            case 10:
//                Comment co = (Comment)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                co.setComment(txtComment.getText());
//                co.setText(co.getComment());
//                break;
//            default:
//                break;
//        }
//        if(!error){
//            Stage stage = (Stage)btnApply.getScene().getWindow();
//            stage.close();
//        }  
    }
    @FXML 
    private void toggleIsArray(ActionEvent event){
        if(chkIsArray.isSelected())
            txtArrayLength.setDisable(false);
        else
            txtArrayLength.setDisable(true);
    }
    public void dispCurrentValues() {
//        switch (type){
//            case 1:
//                ForLoop f = (ForLoop)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                txtVariable.setText(f.getVariable());
//                txtStart.setText(f.getStart()); 
//                txtCondition.setText(f.getCondition());
//                txtStep.setText(f.getStep());
//                break;
//            case 2:
//                IfStatement i = (IfStatement)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                txtCondition.setText(i.getCondition());
//                break;
//            case 3:
//                WhileLoop w = (WhileLoop)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                txtCondition.setText(w.getCondition());
//                break;
//            case 4:
//                DoLoop d = (DoLoop)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                txtCondition.setText(d.getCondition());
//                break;
//            case 5:
//                DeclareVariable de = (DeclareVariable)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                txtType.setText(de.getType());
//                txtName.setText(de.getName());
//                chkIsArray.setSelected(de.getIsArray());
//                if(de.getIsArray()){
//                    txtArrayLength.setDisable(false);
//                    txtArrayLength.setText(de.getArrayLength()+"");
//                }
//                break;
//            case 6:
//                AssignValue a = (AssignValue)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                txtVariable.setText(a.getVariable());
//                txtValue.setText(a.getValue());
//                break;
//            case 7:
//                InputValue in = (InputValue)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                txtVariable.setText(in.getVariable());
//                break;
//            case 8:
//                OutputExpression o = (OutputExpression)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                txtExpression.setText(o.getExpression());
//                break;
//            case 9:
//                CallMethod c = (CallMethod)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                txtMethod.setText(c.getMethod());
//                break;
//            case 10:
//                Comment co = (Comment)Test.mainHandler.getItem(Test.mainHandler.findByUUID(uuid));
//                txtComment.setText(co.getComment());
//                break;
//            default:
//                break;
//        }
    }
    public boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException | NullPointerException e) { 
            return false; 
        }
        return true;
    }
    public boolean isPositiveInteger(String s) {
        if(isInteger(s) && Integer.parseInt(s)>0)
            return true;
        else
            return false;
    }
}
