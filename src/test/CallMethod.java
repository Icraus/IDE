
package test;

import com.icraus.vpl.codegenerator.GrammerConstants;
import com.icraus.vpl.codegenerator.SimpleStatement;
import com.icraus.vpl.codegenerator.Statement;
import icraus.Components.Component;
import icraus.Components.SimpleComponent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CallMethod extends Item {
    
    private String method;
    private final EditCallMethodWindowBase d;
    
    public CallMethod(SimpleComponent parent){
        super(parent);
        setText("Call");
        setMinWidth(120);
        setMinHeight(40);
        getStyleClass().add("callMethodStyle");
        String css = getClass().getResource("callStyle.css").toExternalForm();
        getStylesheets().add(css);
        getStyleClass().add("callMethod");
        d = new EditCallMethodWindowBase();
        setOnAction(e ->{
            Stage stg = createDialog(d);
            d.btnApply.setOnAction(c -> {
                parametersChanged(d.txtMethod.getText());
                stg.close();
            });
            stg.showAndWait();
        });
        
    }  
    
    public void setMethod(String method) {
        this.method = method;
    }
    public String getMethod() {
        return method;
    }

    private void parametersChanged(String text) {
        SimpleStatement st = (SimpleStatement) getParentComponent().getStatement().get();
        st.setStatementString(text + GrammerConstants.OP_END_LINE);
    }

    
}
