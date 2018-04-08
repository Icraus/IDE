package test;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class EditForLoopWindowBase extends BorderPane {

    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final RowConstraints rowConstraints2;
    protected final Label forLoopGridPane1;
    protected final Label forLoopGridPane;
    protected final Label label;
    protected final Label label0;
    final TextField txtVariable;
    final TextField txtStart;
    final TextField txtCondition;
    final TextField txtStep;
    final Button btnApply;
    
    public EditForLoopWindowBase() {

        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        forLoopGridPane1 = new Label();
        forLoopGridPane = new Label();
        label = new Label();
        label0 = new Label();
        txtVariable = new TextField();
        txtStart = new TextField();
        txtCondition = new TextField();
        txtStep = new TextField();
        btnApply = new Button();

        setPadding(new Insets(0.0, 0.0, 10.0, 0.0));

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);
        gridPane.setAlignment(javafx.geometry.Pos.CENTER);
        gridPane.setHgap(20.0);
        gridPane.setVgap(20.0);

        columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);

        columnConstraints0.setHalignment(javafx.geometry.HPos.CENTER);

        rowConstraints.setValignment(javafx.geometry.VPos.CENTER);

        rowConstraints0.setValignment(javafx.geometry.VPos.CENTER);

        rowConstraints1.setValignment(javafx.geometry.VPos.CENTER);

        rowConstraints2.setValignment(javafx.geometry.VPos.CENTER);

        forLoopGridPane1.setAlignment(javafx.geometry.Pos.CENTER);
        forLoopGridPane1.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        forLoopGridPane1.setText("Variable");
        forLoopGridPane1.setFont(new Font("System Bold", 24.0));

        GridPane.setRowIndex(forLoopGridPane, 1);
        forLoopGridPane.setAlignment(javafx.geometry.Pos.CENTER);
        forLoopGridPane.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        forLoopGridPane.setText("Start");
        forLoopGridPane.setFont(new Font("System Bold", 24.0));

        GridPane.setRowIndex(label, 2);
        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        label.setText("Condition");
        label.setFont(new Font("System Bold", 24.0));

        GridPane.setRowIndex(label0, 3);
        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        label0.setText("Step");
        label0.setFont(new Font("System Bold", 24.0));

        GridPane.setColumnIndex(txtVariable, 1);
        txtVariable.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        txtVariable.setFont(new Font(15.0));

        GridPane.setColumnIndex(txtStart, 1);
        GridPane.setRowIndex(txtStart, 1);
        txtStart.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        GridPane.setMargin(txtStart, new Insets(0.0));
        txtStart.setFont(new Font(15.0));

        GridPane.setColumnIndex(txtCondition, 1);
        GridPane.setRowIndex(txtCondition, 2);
        txtCondition.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        GridPane.setMargin(txtCondition, new Insets(0.0));
        txtCondition.setFont(new Font(15.0));

        GridPane.setColumnIndex(txtStep, 1);
        GridPane.setRowIndex(txtStep, 3);
        txtStep.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        GridPane.setMargin(txtStep, new Insets(0.0));
        txtStep.setFont(new Font(15.0));
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
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getRowConstraints().add(rowConstraints2);
        gridPane.getChildren().add(forLoopGridPane1);
        gridPane.getChildren().add(forLoopGridPane);
        gridPane.getChildren().add(label);
        gridPane.getChildren().add(label0);
        gridPane.getChildren().add(txtVariable);
        gridPane.getChildren().add(txtStart);
        gridPane.getChildren().add(txtCondition);
        gridPane.getChildren().add(txtStep);

    }

    protected void applyNewValues(javafx.event.ActionEvent actionEvent){
        
    }

}
