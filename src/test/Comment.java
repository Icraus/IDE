package test;

import com.icraus.vpl.codegenerator.GrammerConstants;
import com.icraus.vpl.codegenerator.SimpleStatement;
import icraus.Components.SimpleComponent;
import javafx.stage.Stage;

public class Comment extends Item {

    private String comment;
    private EditCommentWindowBase d;

    public Comment(SimpleComponent parent) {
        super(parent);
        setText("Comment");
        setMinWidth(120);
        setMinHeight(40);
        String css = getClass().getResource("commentStyle.css").toExternalForm();
        getStylesheets().add(css);
        getStyleClass().add("comment");
        setOnAction((e) -> {
            d = new EditCommentWindowBase();

            Stage stg = createDialog(d);
            d.btnApply.setOnAction(c -> {
                parametersChanged(d.txtComment.getText());
                stg.close();
            });
            stg.showAndWait();
        });
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    private void parametersChanged(String text) {
        SimpleStatement value = (SimpleStatement) getParentComponent().getStatement().getValue();
        value.setStatementString(GrammerConstants.OP_COMMENT_START + " " + text + " " + GrammerConstants.OP_COMMENT_END);
    }
}
