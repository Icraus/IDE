package test;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class EditCallMethodWindowBase extends BorderPane {

    final Button btnApply;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    final TextField txtMethod;
    protected final Label label;

    public EditCallMethodWindowBase() {

        btnApply = new Button();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        txtMethod = new TextField();
        label = new Label();

        BorderPane.setAlignment(btnApply, javafx.geometry.Pos.CENTER);
        btnApply.setMnemonicParsing(false);
        btnApply.setOnAction(this::applyNewValues);
        btnApply.setText("Apply");
        btnApply.setOpaqueInsets(new Insets(0.0));
        BorderPane.setMargin(btnApply, new Insets(15.0, 0.0, 10.0, 0.0));
        btnApply.setFont(new Font(18.0));
        setBottom(btnApply);

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);
        gridPane.setAlignment(javafx.geometry.Pos.CENTER);
        gridPane.setHgap(10.0);

        columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);

        columnConstraints0.setHalignment(javafx.geometry.HPos.CENTER);

        rowConstraints.setValignment(javafx.geometry.VPos.CENTER);

        GridPane.setColumnIndex(txtMethod, 1);
        txtMethod.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        txtMethod.setMinWidth(380.0);
        txtMethod.setPromptText("Enter name and arguments");
        txtMethod.setFont(new Font(29.0));

        label.setText("Method");
        label.setFont(new Font("System Bold", 29.0));
        setCenter(gridPane);

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getChildren().add(txtMethod);
        gridPane.getChildren().add(label);

    }

    protected void applyNewValues(javafx.event.ActionEvent actionEvent){
        
    }

}
