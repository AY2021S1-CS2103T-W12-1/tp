package seedu.address.model;

import java.nio.file.Path;
import java.util.PriorityQueue;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.room.Room;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' covigent app file path.
     */
    Path getCovigentAppFilePath();

    /**
     * Sets the user prefs' covigent app file path.
     */
    void setCovigentAppFilePath(Path covigentAppFilePath);

    /**
     * Replaces patient records with the data in {@code covigentApp}.
     */
    void setPatientRecords(ReadOnlyPatientRecords patientRecords);

    /** Returns the patient records */
    ReadOnlyPatientRecords getPatientRecords();

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the address book.
     */
    boolean hasPatient(Patient patient);

    /**
     * Deletes the given patient.
     * The patient must exist in the address book.
     */
    void deletePatient(Patient target);

    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the address book.
     */
    void addPatient(Patient patient);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the address book.
     * The patient identity of {@code editedPatient} must not be the same as
     * another existing patient in the address book.
     */
    void setPatient(Patient target, Patient editedPatient);

    /**
     * Checks if patient is already assigned to a room.
     *
     * @param name Of the patient.
     * @return Boolean value of whether patient is already assigned.
     */
    boolean isPatientAssignedToRoom(Name name);

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code UniquePatientList}.
     */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);

    /**
     * Returns total number of rooms in the application's {@code RoomList}.
     */
    int getNumOfRooms();

    void addRooms(int num);

    /**
     * Checks if the given room number is present in the application.
     *
     * @param roomNumber to check if it is in the application.
     * @return Index Of room that is found.
     */
    Index checkIfRoomPresent(Integer roomNumber);

    void displayFindRoom(Room room);

    void displayAllRoom();

    /**
     * Returns an unmodifiable view of the list of {@code Room} backed by the internal list of
     * {@code RoomList}.
     */
    ObservableList<Room> getRoomList();

    RoomList getModifiableRoomList();

    /**
     * Returns Priority Queue of rooms
     */
    PriorityQueue<Room> getRooms();

    /**
     * Returns true if a room with the same identity as {@code room} exists in Covigent.
     *
     * @param room The room .
     * @return true if {@code room} is in Covigent; false otherwise.
     */
    boolean hasRoom(Room room);

    /**
     * Replaces the given room {@code target} with {@code editedRoom}.
     * {@code target} must exist in the application.
     * The room identity of {@code editedRoom} must not be the same as
     * another existing room in the application.
     *
     * @param target Of the room to be changed.
     * @param editedRoom Is the newly edited room.
     */
    void setSingleRoom(Room target, Room editedRoom);

    /**
     * Adds {@code task} to {@code room}.
     * The room must exist in {@code CovigentApp}.
     *
     * @param task The task to add.
     * @param room The room to which the task should be added.
     */
    void addTaskToRoom(Task task, Room room);

    /**
     * Deletes {@code task} from {@code room}.
     * The room must exist in Covigent.
     * The task must exist in room.
     *
     * @param task The task to delete.
     * @param room The room from which the task should be delete.
     */
    void deleteTaskFromRoom(Task task, Room room);
}
