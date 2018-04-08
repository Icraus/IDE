package test;

import icraus.Components.Component;
import icraus.Components.DraggableComponent;
import icraus.Components.Selectable;
import ide.UiManager;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Item extends Button implements DraggableComponent, Selectable {
//Add Validation For each component
    private static int currentType = 0;
    private static String currentUUID = "";
    private String uuid;

    public Component getParentComponent() {
        return parent;
    }

    private Component parent;

    public Item(Component _parent) {
        setFont(Font.font("System", FontWeight.BOLD, 20));
        uuid = UUID.randomUUID().toString();
        parent = _parent;
        setId(UiManager.GUI_QUALIFIER + this.parent.getUUID());
        
        setOnAction((ActionEvent e) -> {
            try {
                Tab t = UiManager.getInstance().selectTabByUuid(getParentComponentUuid());
                t.getTabPane().getSelectionModel().select(t);
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        });
    }

    public String getUUID() {
        return uuid;
    }

    public static void setCurrentElement(int type, String uuid) {
        currentType = type;
        currentUUID = uuid;
    }

    public static int getCurrentType() {
        return currentType;
    }

    public static String getCurrentUUID() {
        return currentUUID;
    }

    @Override
    public String getParentComponentUuid() {
        return getParentComponent().getUUID();
    }

    public static Stage createDialog(BorderPane d) {
        Scene s = new Scene(d);
        Stage stg = new Stage();
        stg.setScene(s);
        return stg;
    }
}
