/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ide;

import com.icraus.vpl.codegenerator.ErrorGenerateCodeException;
import com.sun.javafx.collections.ObservableListWrapper;
import icraus.Components.ClassContentPane;
import icraus.Components.Component;
import icraus.Components.ComponentNotFoundException;
import icraus.Components.ComponentsModel;
import icraus.Components.IllegalComponent;
import icraus.Components.MethodComponent;
import icraus.Components.event.CanvasDragEventHandler;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.DragEvent;
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
    @FXML
    private TabPane mainTabPane;
    @FXML
    private VBox libraryVBox;
    @FXML
    TreeView<Component> projectTree;
    @FXML
    private Pane canvas;
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
                            cell.getItem().getUiDelegate().get().requestFocus();
                        }
                    });

                });
                return cell;
            }
        });
        
//        removeBlock();
//        removeBlock();

    }

    @FXML
    private void addIfStatment() {
       ClassContentPane n = (ClassContentPane) this.lookup("#GUI" + uuid);
        Platform.runLater(()->n.requestFocus());
    }

    @FXML
    public void initialize() {
        ComponentListUi comps = new ComponentListUi();
        model.getModel().addListener((Observable e) -> {
            drawClasses();
        });
        libraryVBox.getChildren().add(comps);
        canvas.addEventHandler(DragEvent.ANY, new CanvasDragEventHandler());

    }

    @FXML
    public void removeBlock() throws IllegalComponent, ComponentNotFoundException {
//        TextInputDialog d = new TextInputDialog("A");
//        String name = d.showAndWait().get();
        uuid = model.addClass("A", "Default");
        
        MethodComponent c = new MethodComponent("A", "B", "C");
        model.addMethodByUuid(uuid, c);
        projectTree.setRoot(model.toTreeItems());
    }

    @FXML
    public void generateCode() {
        String s = "";
        try {
            for (Component cs : model.toList()) {
                s += cs.getStatement().get().toText();

            }
        } catch (ErrorGenerateCodeException ex) {
            Logger.getLogger(MainIDEController.class
                    .getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
        }
        System.out.println(s);
    }

    public TabPane getMainTabPane() {
        return mainTabPane;
    }

    public void drawClasses() {
        canvas.getChildren().clear();
        for (Component c : model.toList()) {
            canvas.getChildren().add(c.getUiDelegate().get());
        }
    }

}
