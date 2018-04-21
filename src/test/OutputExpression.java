package test;

import com.icraus.vpl.codegenerator.GrammerConstants;
import com.icraus.vpl.codegenerator.SimpleStatement;
import icraus.Components.SimpleComponent;
import javafx.stage.Stage;

public class OutputExpression extends Item {

    private String expression;

    public OutputExpression(SimpleComponent parent) {
        super(parent);
        setText("Output");
        setMinWidth(120);
        setMinHeight(40);
        String css = getClass().getResource("outputStyle.css").toExternalForm();
        getStylesheets().add(css);
        getStyleClass().add("outputExpression");
        setOnAction(e -> {
            EditOutputExpressionWindowBase d = new EditOutputExpressionWindowBase();
            Stage stg = createDialog(d);
            d.btnApply.setOnAction(c -> {
                parametersChanged(d.txtExpression.getText());
                stg.close();
            });
            stg.showAndWait();
        });
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    private void parametersChanged(String text) {
        SimpleStatement stmnt = (SimpleStatement) getParentComponent().getStatement().get();
        stmnt.setStatementString(GrammerConstants.STAT_OUTPUT_OP + GrammerConstants.OP_PARAN_START + text + GrammerConstants.OP_PARAN_END + GrammerConstants.OP_END_LINE);
    }
}
