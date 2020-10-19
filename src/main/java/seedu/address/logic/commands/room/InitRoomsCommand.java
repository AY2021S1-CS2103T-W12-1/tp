package seedu.address.logic.commands.room;

import java.util.Objects;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds the number of hotel rooms in a hotel
 */
public class InitRoomsCommand extends Command {
    public static final String COMMAND_WORD = "initroom";
    public static final String MESSAGE_ZERO_CANNOT_BE_AN_INPUT = "Please input a positive value";
    public static final String MESSAGE_NEGATIVE_VALUES_CANNOT_BE_INPUT = "Please check your value! "
            + "You have input a negative value!";
    public static final String MESSAGE_SUCCESS = "Initialize the number of rooms to %d rooms in the application";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Initializes the number of rooms in the "
        + "quarantine facility to the app, if there was data given previously, they would be stored.\n"
        + "Parameters: NUMBER_OF_ROOMS\n"
        + "Example: " + COMMAND_WORD + " 123";

    private int numOfRooms;

    /**
     * Creates an AddCommand to add the number of rooms available in a hotel
     */
    public InitRoomsCommand(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (numOfRooms == 0) {
            throw new CommandException(MESSAGE_ZERO_CANNOT_BE_AN_INPUT);
        } else if (numOfRooms < 0) {
            throw new CommandException(MESSAGE_NEGATIVE_VALUES_CANNOT_BE_INPUT);
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
        InitRoomsCommand that = (InitRoomsCommand) o;
        return numOfRooms == that.numOfRooms;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOfRooms);
    }
}
