/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ide;

import icraus.Components.UiManager;
import com.icraus.vpl.codegenerator.ErrorGenerateCodeException;
import com.icraus.vpl.codegenerator.parsers.JavaCodeGenerator;
import icraus.Components.ClassComponent;
import icraus.Components.Component;
import icraus.Components.ComponentNotFoundException;
import icraus.Components.ComponentsModel;
import icraus.Components.IllegalComponent;
import icraus.Components.ProjectComponent;
import icraus.Components.Selectable;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 *
 * @author Shoka
 */
public class MainIDEController extends BorderPane /*implements Initializable */ {

    ComponentsModel model = ComponentsModel.getInstance();
    private static MainIDEController instance = new MainIDEController();
    private JavaCodeGenerator jc = new JavaCodeGenerator();

    @FXML
    private VBox libraryVBox;
    @FXML
    TreeView<Component> projectTree;

    @FXML
    private BorderPane mainPane;
    @FXML
    private Pane root;
    @FXML
    private Button bt2;
    @FXML
    private Button generateCodeButton;

    public static MainIDEController getInstance() {
        return instance;
    }

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

    }

    @FXML
    public void initialize() {
        ComponentListUi comps = new ComponentListUi();
        libraryVBox.getChildren().add(comps);
        sceneProperty().addListener(c -> {
            getScene().focusOwnerProperty().addListener((Observable e) -> {
                Node f = getScene().getFocusOwner();
                if (f instanceof Selectable) {
                    Selectable currentItem = (Selectable) f;
                    model.setCurrentComponent(currentItem.getParentComponentUuid());

                }

            });
        });
    }

    @FXML
    public void createNewProject() throws IllegalComponent, ComponentNotFoundException {
        new TextInputDialog().showAndWait().ifPresent(e -> {
            model.addProject(e);
        });
    }

    @FXML
    public void calculateTree() {
        model.calculateRoot();
        projectTree.setRoot(model.treeItemsProperty().get());
    }

    @FXML
    public void closeApp() {
        Platform.exit();
    }

    @FXML
    public void removeComponent() {
        try {
            model.removeComponetByUuid(model.getCurrentComponent());
        } catch (ComponentNotFoundException ex) {
            Logger.getLogger(MainIDEController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        scene.get
    }

    @FXML
    public void generateCode() {
        String s = "";
        JavaCodeGenerator g = new JavaCodeGenerator();
        DirectoryChooser fc = new DirectoryChooser();

        File d = fc.showDialog(null);

        try {
            for (ProjectComponent cs : model.toList()) {
                for (Component c : cs.getChildern()) {
                    ClassComponent cc = (ClassComponent) c;
                    String txt = cc.getStatement().get().toText();
                    g.generateClass(d.getAbsolutePath(), cc.getClassName().get(), txt);
                    s += c.getStatement().get().toText();
                }
            }
        } catch (ErrorGenerateCodeException ex) {
            Logger.getLogger(MainIDEController.class
                    .getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
        }
        String ss = jc.generateCode(s);
        System.out.println(ss);
    }
    
    //REMOVETHIS
    @FXML
    public void testAdd() {
//        String uuid = model.addProject("new Project");
//        try {
//            String ccuuid = model.addComponent(uuid, new ClassComponent("Add", "BB"));
//            ccuuid = model.addComponent(ccuuid, new MethodComponent("AA", "AAB", "AAD"));
//            ConnectDatabaseComponent ss = new ConnectDatabaseComponent();
//            String ccuuidd = model.addComponent(ccuuid, ss);
//            DatabaseTableComponent cc = createDatabaseTable("employee", "id", "String");
//            model.addComponent(ccuuidd, cc);
//            cc = createDatabaseTable("Customer", "text", "integer");
//            model.addComponent(ccuuidd, cc);
//            model.addComponent(ccuuid, DatabaseFactory.createInsertPlugin().createComponent());
//            model.addComponent(ccuuid, new DeleteComponent());
//
//        } catch (ComponentNotFoundException ex) {
//            Logger.getLogger(MainIDEController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalComponent ex) {
//            Logger.getLogger(MainIDEController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalComponentInstantiation ex) {
//            Logger.getLogger(MainIDEController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
////REMOVETHIS
//    private DatabaseTableComponent createDatabaseTable(String d, String t, String s) throws IllegalComponent {
//        DatabaseTableComponent cc = new DatabaseTableComponent();
//        cc.setTableName(d);
//        DatabaseColumnComponent ss = new DatabaseColumnComponent();
//        ss.setColumnName(t);
//        ss.setColumnType(s);
//        cc.addComponent(ss);
//        ss = new DatabaseColumnComponent();
//        ss.setColumnName(s);
//        ss.setColumnType(t);
//        cc.addComponent(ss);
//        
//        return cc;
//    }

}
