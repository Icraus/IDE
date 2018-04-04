package test;

import icraus.Components.Component;
import java.util.UUID;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Item extends Button {

    private static int currentType = 0;
    private static String currentUUID = "";
    private String uuid;
    private Component parent;

    Item(Component _parent) {
        setFont(Font.font("System", FontWeight.BOLD, 20));
        uuid = UUID.randomUUID().toString();

        parent = _parent;
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

    public Component getUiParent() {
        return parent;
    }

}
