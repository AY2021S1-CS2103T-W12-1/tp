package seedu.address.model;

import static java.util.Objects.checkFromIndexSize;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.function.Supplier;
import java.util.logging.Logger;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.UniquePatientList;
import seedu.address.model.room.Room;
import seedu.address.model.room.exceptions.DuplicateRoomException;
import seedu.address.model.room.exceptions.RoomNotFoundException;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.storage.JsonPatientRecordsStorage;

/**
 * Contains information regarding the Room information
 */
public class RoomList implements ReadOnlyRoomList {
    private static final Logger logger = LogsCenter.getLogger(JsonPatientRecordsStorage.class);

    private final UniqueRoomList rooms;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        rooms = new UniqueRoomList();
    }

    public RoomList() {}

    /**
     * Converts data from readOnlyRoomList to roomList
     */
    public RoomList(ReadOnlyRoomList readOnlyRoomList) {
        this();
        resetData(readOnlyRoomList);
    }

    public boolean containsPatientInExcessRoom() {
        return rooms.containsPatientInExcessRoom();
    }

    public boolean hasEmptyRooms() {
        return rooms.hasEmptyRooms();
    }

    /*private int numOfRooms;
    private PriorityQueue<Room> rooms = new PriorityQueue<>();
    private ObservableList<Room> internalList = FXCollections.observableArrayList();
    private final ObservableList<Room> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);*/

    public static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    /** Creates default RoomList() object where all fields are null**/
   // public RoomList() {}


    /**
     * Creates a RoomList object using the information given in files containing information about
     * which rooms are occupied and number of rooms
     */
    /*public RoomList(PriorityQueue<Room> rooms, int numOfRooms) {
        this.rooms = rooms;
        this.numOfRooms = numOfRooms;
    }*/

    /**
     * Resets the existing data of this {@code RoomList} with {@code newData}.
     */
    public void resetData(ReadOnlyRoomList readOnlyRoomList) {
        /*ObservableList<Room> roomLists = readOnlyRoomList.getRoomObservableList();
        numOfRooms = roomLists.size();
        rooms.addAll(roomLists);
        internalList.addAll(roomLists);*/
        rooms.resetData(readOnlyRoomList);
    }
    /**
     * Returns Priority Queue of rooms
     */
    public PriorityQueue<Room> getRooms() {
        return rooms.getRooms();
    }

    /**
     * Returns number of rooms in hotel
     */
    public int getNumOfRooms() {
        return rooms.getNumOfRooms();
    }

    public ObservableList<Room> getRoomObservableList() {
        return rooms.getRoomObservableList();
    }

    private void addRooms() {
        //rooms.
    }

    /**
     * Adds the number of the rooms in a hotel
     *
     * @param numOfRooms is the number of rooms to be added
     */
    public void addRooms(int numOfRooms) {
        rooms.addRooms(numOfRooms);
    }

    /**
     * Adds this room to the RoomList
     * @param room is added to RoomList
     */
    public void addRooms(Room room) {
        rooms.addRooms(room);
    }

    /**
     * Adds a task to a room.
     * The room must exist in the {@code RoomList}.
     *
     * @param task The task to add.
     * @param room The room to which the task should be added.
     * @throws RoomNotFoundException if {@code room} is not in {@code RoomList}.
     */
    public void addTaskToRoom(Task task, Room room) {
        requireAllNonNull(task, room);

        rooms.addTaskToRoom(task, room);
    }

    /**
     * Deletes a task from a room.
     * The room must exist in the {@code RoomList}.
     * The task must exist in the {@code TaskList} of the room.
     *
     * @param task The task to delete.
     * @param room The room to which the task should be deleted.
     * @throws RoomNotFoundException if {@code room} is not in {@code RoomList}.
     * @throws TaskNotFoundException if {@code task} is not in {@code room}.
     */
    public void deleteTaskFromRoom(Task task, Room room) {
        requireAllNonNull(task, room);

        rooms.deleteTaskFromRoom(task, room);
    }

    public void setTaskToRoom(Task target, Task editedTask, Room room) {
        requireAllNonNull(target, editedTask, room);

        rooms.setTaskToRoom(target, editedTask, room);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Room> asUnmodifiableObservableList() {
        return rooms.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomList // instanceof handles nulls
                && rooms.equals(((RoomList) other).rooms));
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RoomList roomList = (RoomList) o;
        Room[] roomsForPQ = this.rooms.toArray(new Room[0]);
        Room[] rooms1ForPQ = roomList.rooms.toArray(new Room[0]);

        Room[] roomsForObservableList = internalList.toArray(new Room[0]);
        Room[] rooms1FOrObservableList = roomList.internalList.toArray(new Room[0]);
        return numOfRooms == roomList.numOfRooms
                && Arrays.equals(roomsForPQ, rooms1ForPQ)
                && Arrays.equals(roomsForObservableList, rooms1FOrObservableList);
    }*/

    /**
     * Tests whether 2 PriorityQueues are equal by checking whether at each relative position they contain the equal
     * rooms
     */
    public boolean equals(PriorityQueue<Room> rooms1, PriorityQueue<Room> rooms2) {
        if (rooms1.size() != rooms2.size()) {
            return false;
        } else {
            int size = rooms1.size();
            for (int i = 0; i < size; i++) {
                if (!rooms1.poll().equals(rooms2.poll())) {
                    return false;
                }
            }
            return true;
        }
    }
    /**
     * Returns true if the list contains an equivalent room as the given argument.
     */
    public boolean containsRoom(Room toCheck) {
        requireNonNull(toCheck);
        return rooms.containsRoom(toCheck);
    }

    /**
     * Clears the room which contains the patient with the given name.
     * @param patientName to clear the room from.
     */
    public void clearRoom(Name patientName) {
        requireNonNull(patientName);
        rooms.clearRoom(patientName);
    }

    /**
     * Replaces the room {@code target} in the list with {@code editedRoom}.
     * {@code target} must exist in the list.
     * The room identity of {@code editedRoom} must not be the same as another existing room in the list.
     *
     * @param target Room to be changed.
     * @param editedRoom Room that has been changed.
     */
    public void setSingleRoom(Room target, Room editedRoom) {
        rooms.setSingleRoom(target, editedRoom);
    }
    @Override
    public int hashCode() {
        return rooms.hashCode();
    }

    public void setNumOfRooms(int numOfRooms) {
        rooms.setNumOfRooms(numOfRooms);
    }

    public void setRooms(PriorityQueue<Room> rooms) {
        this.rooms.setRooms(rooms);
    }

}
