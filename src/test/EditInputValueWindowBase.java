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

public class EditInputValueWindowBase extends BorderPane {

    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final Label forLoopGridPane1;
    final TextField txtVariable;
    final Button btnApply;

    public EditInputValueWindowBase() {

        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        forLoopGridPane1 = new Label();
        txtVariable = new TextField();
        btnApply = new Button();

        setPadding(new Insets(0.0, 0.0, 10.0, 0.0));

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);
        gridPane.setAlignment(javafx.geometry.Pos.CENTER);
        gridPane.setHgap(20.0);
        gridPane.setVgap(20.0);

        columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);

        columnConstraints0.setHalignment(javafx.geometry.HPos.CENTER);

        rowConstraints.setValignment(javafx.geometry.VPos.CENTER);

        forLoopGridPane1.setAlignment(javafx.geometry.Pos.CENTER);
        forLoopGridPane1.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        forLoopGridPane1.setText("Variable");
        forLoopGridPane1.setFont(new Font("System Bold", 24.0));

        GridPane.setColumnIndex(txtVariable, 1);
        txtVariable.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        txtVariable.setFont(new Font(15.0));
        BorderPane.setMargin(gridPane, new Insets(10.0, 0.0, 20.0, 0.0));
        gridPane.setOpaqueInsets(new Insets(0.0));
        setCenter(gridPane);

        BorderPane.setAlignment(btnApply, javafx.geometry.Pos.CENTER);
        btnApply.setMinHeight(44.0);
        btnApply.setMinWidth(81.0);
        btnApply.setMnemonicParsing(false);
        btnApply.setOnAction(this::applyNewValues);
        btnApply.setText("Apply");
        btnApply.setFont(new Font(20.0));
        btnApply.setOpaqueInsets(new Insets(0.0));
        setBottom(btnApply);
        setOpaqueInsets(new Insets(0.0));

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getChildren().add(forLoopGridPane1);
        gridPane.getChildren().add(txtVariable);

    }

    protected void applyNewValues(javafx.event.ActionEvent actionEvent){
        
    }

}
