package seedu.address.logic.commands.room;

import java.util.Objects;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds the number of hotel rooms in a hotel
 */
//@@author itssodium
public class InitRoomCommand extends Command {
    public static final String COMMAND_WORD = "initroom";
    public static final String MESSAGE_ZERO_CANNOT_BE_AN_INPUT = "Please input a positive value";
    public static final String MESSAGE_NEGATIVE_VALUES_CANNOT_BE_INPUT = "Please check your value! "
            + "You have input a negative value!";
    public static final String MESSAGE_LARGE_NUMBER_OF_ROOMS_INPUT = "Please reduce the number of rooms input."
            + "maximum number of rooms are 5000";
    public static final String MESSAGE_INSUFFICIENT_ROOMS = "There would be insufficient rooms for existing number of "
            + "patients of %d";
    public static final String MESSAGE_SUCCESS = "Initialize the number of rooms to %d rooms in the application";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Initializes the number of rooms in the "
            + "quarantine facility to the app, if there was data given previously, they would be stored.\n"
            + "Parameters: NUMBER_OF_ROOMS\n"
            + "Example: " + COMMAND_WORD + " 123";

    private int numOfRooms;

    /**
     * Creates an AddCommand to add the number of rooms available in a hotel
     */
    public InitRoomCommand(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.setInitNumOfRooms(numOfRooms);
        if (numOfRooms == 0) {
            throw new CommandException(MESSAGE_ZERO_CANNOT_BE_AN_INPUT);
        } else if (numOfRooms < 0) {
            throw new CommandException(MESSAGE_NEGATIVE_VALUES_CANNOT_BE_INPUT);
        } else if (numOfRooms > 5000) {
            throw new CommandException(MESSAGE_LARGE_NUMBER_OF_ROOMS_INPUT);
        } else if (model.getNumOfRooms() > numOfRooms && !model.hasSpaceForRooms()) {
            throw new CommandException(String.format(MESSAGE_INSUFFICIENT_ROOMS, model.numOfExcessOccupiedRooms()));
        }
        model.addRooms(numOfRooms);
        return new CommandResult(String.format(MESSAGE_SUCCESS, numOfRooms));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InitRoomCommand that = (InitRoomCommand) o;
        return numOfRooms == that.numOfRooms;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOfRooms);
    }
}
