/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ide;

import com.icraus.vpl.codegenerator.ErrorGenerateCodeException;
import com.sun.javafx.collections.ObservableListWrapper;
import icraus.Components.Component;
import icraus.Components.ComponentNotFoundException;
import icraus.Components.ComponentsModel;
import icraus.Components.IllegalComponent;
import icraus.Components.MethodComponent;
import icraus.Components.ProjectComponent;
import icraus.Components.SimpleComponent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 *
 * @author Shoka
 */
public class MainIDEController extends BorderPane /*implements Initializable */ {

    ComponentsModel model = ComponentsModel.getInstance();
    private static MainIDEController instance = new MainIDEController();
//    @FXML
//    private TabPane mainTabPane;
    @FXML
    private VBox libraryVBox;
    @FXML
    TreeView<Component> projectTree;
//    @FXML
//    private Pane canvas;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Pane root;
    @FXML
    private Button bt2;
    @FXML
    private Button generateCodeButton;
    ObservableList<Component> lst = new ObservableListWrapper<>(new ArrayList<>());

    public static MainIDEController getInstance() {
        return instance;
    }
    private String uuid;

    private MainIDEController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainIDE.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        projectTree.setCellFactory(new Callback<TreeView<Component>, TreeCell<Component>>() {
            @Override
            public TreeCell<Component> call(TreeView<Component> param) {
                TextFieldTreeCell<Component> cell = new TextFieldTreeCell<Component>(new StringConverter<Component>() {

                    @Override
                    public String toString(Component object) {
                        return object.toString();
                    }

                    @Override
                    public Component fromString(String string) {
                        //TODO add Return from String
                        return null;
                    }
                });
                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //FIXME fix this can't get right foucs
                            if (cell.getIndex() != 0) {
                                cell.getItem().getUiDelegate().get().requestFocus();
                            }
                        }
                    });

                });
                return cell;
            }
        });
        projectTree.rootProperty().bind(model.treeItemsProperty());
        mainPane.centerProperty().setValue(UiManager.getInstance().getMainTabPane());
    }

    @FXML
    private void addIfStatment() {
//        String str = model.getAllMethods().get(0).getUUID();
//        System.out.println(str);
//        
//        Node lookup = UiManager.getInstance().getMainTabPane().lookup("#GUI" + str);
//        lookup.setStyle("-fx-background-color: 'red'");
//        Component com = SimpleComponent.createIfStatement();
//        String ty = projectTree.getSelectionModel().getSelectedItem().getValue().getUUID();
//
//        try {
//            model.addComponent(ty, com);
//        } catch (ComponentNotFoundException ex) {
//            Logger.getLogger(MainIDEController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalComponent ex) {
//            Logger.getLogger(MainIDEController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    public void initialize() {
        ComponentListUi comps = new ComponentListUi();
        model.getModel().addListener((Observable e) -> {
            drawClasses();
        });
        libraryVBox.getChildren().add(comps);
//        canvas.addEventHandler(DragEvent.ANY, new CanvasDragEventHandler());

    }

    @FXML
    public void removeBlock() throws IllegalComponent, ComponentNotFoundException {
        System.out.println(model.addProject("Java Project"));
    }

    @FXML
    public void generateCode() {
        String s = "";
        try {
            for (ProjectComponent cs : model.toList()) {
                for(Component c : cs.getChildern())
                {
                    s += c.getStatement().get().toText();   
                }
            }
        } catch (ErrorGenerateCodeException ex) {
            Logger.getLogger(MainIDEController.class
                    .getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
        }
        System.out.println(s);
    }

    public void drawClasses() {
//        canvas.getChildren().clear();
//        for (Component c : model.toList()) {
//            canvas.getChildren().add(c.getUiDelegate().get());
//        }
    }

}
