package seedu.address.logic.commands.room;

import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PatientRecords;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalRooms;

public class InitRoomCommandTest {

    @Test
    void execute_numberOfRooms_notDefined() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());

        assertCommandFailure(new InitRoomCommand(-100), model,
                InitRoomCommand.MESSAGE_NEGATIVE_VALUES_CANNOT_BE_INPUT);
    }

    @Test
    void execute_numberOfRooms_success() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());
        Model expectedModel =
                new ModelManager(model.getPatientRecords(), new RoomList(), new UserPrefs());
        String expectedMessage = String.format(InitRoomCommand.MESSAGE_SUCCESS, 100);
        expectedModel.addRooms(100);
        assertCommandSuccess(new InitRoomCommand(100), model, expectedMessage, expectedModel);
    }

    @Test
    void execute_increaseNumberOfRooms_success() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());
        Model expectedModel =
                new ModelManager(model.getPatientRecords(), new RoomList(), new UserPrefs());

        //initRoom to 10 rooms first
        String expectedMessage = String.format(InitRoomCommand.MESSAGE_SUCCESS, 10);
        expectedModel.addRooms(10);
        assertCommandSuccess(new InitRoomCommand(10), model, expectedMessage, expectedModel);

        //initRoom to 50 rooms -> increase number of rooms
        String expectedMessage2 = String.format(InitRoomCommand.MESSAGE_SUCCESS, 50);
        expectedModel.addRooms(50);
        assertCommandSuccess(new InitRoomCommand(50), model, expectedMessage2, expectedModel);
    }

    @Test
    void execute_decreaseNumberOfRooms_success() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());
        Model expectedModel =
                new ModelManager(model.getPatientRecords(), new RoomList(), new UserPrefs());

        //initRoom to 50 rooms first
        String expectedMessage = String.format(InitRoomCommand.MESSAGE_SUCCESS, 50);
        expectedModel.addRooms(50);
        assertCommandSuccess(new InitRoomCommand(50), model, expectedMessage, expectedModel);

        //initRoom to 10 rooms -> decrease number of rooms
        String expectedMessage2 = String.format(InitRoomCommand.MESSAGE_SUCCESS, 10);
        expectedModel.addRooms(10);
        assertCommandSuccess(new InitRoomCommand(10), model, expectedMessage2, expectedModel);
    }

    @Test
    void execute_increaseNumberOfOccupiedRooms_success() {
        RoomList roomList = TypicalRooms.getTypicalRoomList();
        Model model = new ModelManager(new PatientRecords(), roomList, new UserPrefs());
        Model expectedModel =
                new ModelManager(model.getPatientRecords(), roomList, new UserPrefs());

        //initRoom to 50 rooms -> increase number of rooms
        String expectedMessage2 = String.format(InitRoomCommand.MESSAGE_SUCCESS, 50);
        expectedModel.addRooms(50);
        assertCommandSuccess(new InitRoomCommand(50), model, expectedMessage2, expectedModel);
    }

    @Test
    void execute_decreaseNumberOfOccupiedRooms_success() {
        RoomList roomList = TypicalRooms.getTypicalRoomList();

        Model model = new ModelManager(new PatientRecords(), roomList, new UserPrefs());
        Model expectedModel =
                new ModelManager(model.getPatientRecords(), roomList, new UserPrefs());

        //initRoom to 5 rooms -> decrease number of rooms
        String expectedMessage2 = String.format(InitRoomCommand.MESSAGE_SUCCESS, 5);
        expectedModel.addRooms(5);
        assertCommandSuccess(new InitRoomCommand(5), model, expectedMessage2, expectedModel);
    }

    @Test
    void execute_decreaseNumberOfOccupiedRooms_failure() {
        RoomList roomList = TypicalRooms.getTypicalRoomList();

        Model model = new ModelManager(new PatientRecords(), roomList, new UserPrefs());

        //initRoom to 2 rooms -> decrease number of rooms
        String expectedMessage = String.format(InitRoomCommand.MESSAGE_INSUFFICIENT_ROOMS, 2);
        assertCommandFailure(new InitRoomCommand(1), model,
                expectedMessage);
    }

}
//@@author itssodium
