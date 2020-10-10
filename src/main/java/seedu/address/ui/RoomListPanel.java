package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.room.Room;

/**
 * Panel containing the list of rooms.
 */
public class RoomListPanel extends UiPart<Region> {
    private static final String FXML = "RoomListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RoomListPanel.class);

    private RoomDetailsPanel roomDetailsPanel;
    private Room room;
    @FXML
    private ListView<Room> roomListView;
    //@FXML
    //private ListView<Room> roomie;
    @FXML
    private AnchorPane roomDetailsPanelPlaceholder;
    private ObservableList<Room> rooms = FXCollections.observableArrayList();

    /**
     * Creates a {@code RoomListPanel} with the given {@code ObservableList}.
     */
    public RoomListPanel(ObservableList<Room> roomList) {
        super(FXML);
        if (!roomList.isEmpty()) {
            roomDetailsPanel = new RoomDetailsPanel(roomList.get(0));
            roomDetailsPanelPlaceholder.getChildren().add(roomDetailsPanel.getRoot());
        }
        roomListView.setItems(roomList);
        roomListView.setCellFactory(listView -> new RoomListViewCell());
    }


    /**
     * Handles mouse click event on the items.
     *
     * @param mouseEvent Created by the user.
     */
    @FXML
    public void handleMouseClick(MouseEvent mouseEvent) {
        Room roomToDisplay = roomListView.getSelectionModel().getSelectedItem();
        roomDetailsPanel = new RoomDetailsPanel(roomToDisplay);
        roomDetailsPanelPlaceholder.getChildren().add(roomDetailsPanel.getRoot());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Room} using a {@code RoomCard}.
     */
    class RoomListViewCell extends ListCell<Room> {
        @Override
        protected void updateItem(Room room, boolean empty) {
            super.updateItem(room, empty);
            if (empty || room == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RoomCard(room).getRoot());
            }
        }
    }

}
