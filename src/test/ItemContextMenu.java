package test;

import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public final class ItemContextMenu extends ContextMenu {

    MenuItem edit = new MenuItem("Edit");
    MenuItem delete = new MenuItem("Delete");

    ItemContextMenu(int type, String uuid) {
        getItems().addAll(edit, delete);
        setActions(type, uuid);
    }

    private void setActions(int type, String uuid) {
        delete.setOnAction((ActionEvent event) -> {
//            Test.mainHandler.deleteItem(uuid);
        });
        edit.setOnAction((ActionEvent event) -> {
//FIXME add Handler
        });
        }
    }
