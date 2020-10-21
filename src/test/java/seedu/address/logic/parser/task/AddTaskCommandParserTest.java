package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.NewCommandTestUtil.DATETIME_DUE_DESC_ORDER_BEDSHEETS;
import static seedu.address.logic.commands.NewCommandTestUtil.DATETIME_DUE_DESC_REMIND_PATIENT;
import static seedu.address.logic.commands.NewCommandTestUtil.DESCRIPTION_DESC_ORDER_BEDSHEETS;
import static seedu.address.logic.commands.NewCommandTestUtil.DESCRIPTION_DESC_REMIND_PATIENT;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_DATETIME_DUE_FORMAT_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_DATETIME_DUE_VALUE_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_ROOM_NUMBER_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.NewCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.NewCommandTestUtil.ROOM_NUMBER_DESC_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.ROOM_NUMBER_EIGHT_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.ROOM_NUMBER_SEVEN_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_ROOM_NUMBER_SEVEN;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.task.DateTimeDue;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandParserTest {

    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(REMIND_PATIENT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DESCRIPTION_DESC_REMIND_PATIENT + ROOM_NUMBER_SEVEN_DESC
                + DATETIME_DUE_DESC_REMIND_PATIENT, new AddTaskCommand(expectedTask, VALID_ROOM_NUMBER_SEVEN));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_ORDER_BEDSHEETS + DESCRIPTION_DESC_REMIND_PATIENT
                + ROOM_NUMBER_SEVEN_DESC + DATETIME_DUE_DESC_REMIND_PATIENT, new AddTaskCommand(expectedTask,
                VALID_ROOM_NUMBER_SEVEN));

        // multiple room numbers - last room number accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_REMIND_PATIENT + ROOM_NUMBER_EIGHT_DESC
                + ROOM_NUMBER_SEVEN_DESC + DATETIME_DUE_DESC_REMIND_PATIENT, new AddTaskCommand(expectedTask,
                VALID_ROOM_NUMBER_SEVEN));

        // multiple due dates - last due date accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_REMIND_PATIENT + ROOM_NUMBER_SEVEN_DESC
                + DATETIME_DUE_DESC_ORDER_BEDSHEETS + DATETIME_DUE_DESC_REMIND_PATIENT,
                new AddTaskCommand(expectedTask, VALID_ROOM_NUMBER_SEVEN));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no due date
        Task expectedTask = new TaskBuilder(REMIND_PATIENT).withDateTimeDue(Optional.empty()).build();
        assertParseSuccess(parser, DESCRIPTION_DESC_REMIND_PATIENT + ROOM_NUMBER_SEVEN_DESC,
                new AddTaskCommand(expectedTask, VALID_ROOM_NUMBER_SEVEN));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, DATETIME_DUE_DESC_REMIND_PATIENT + ROOM_NUMBER_SEVEN_DESC, expectedMessage);

        // missing room number prefix
        assertParseFailure(parser, DESCRIPTION_DESC_REMIND_PATIENT
                + DATETIME_DUE_DESC_REMIND_PATIENT, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid due date value
        assertParseFailure(parser, DESCRIPTION_DESC_REMIND_PATIENT + ROOM_NUMBER_DESC_ONE
                + INVALID_DATETIME_DUE_VALUE_DESC, DateTimeDue.MESSAGE_CONSTRAINTS);

        // invalid due date format
        assertParseFailure(parser, DESCRIPTION_DESC_REMIND_PATIENT + ROOM_NUMBER_DESC_ONE
                + INVALID_DATETIME_DUE_FORMAT_DESC, DateTimeDue.MESSAGE_CONSTRAINTS);

        // invalid room number
        assertParseFailure(parser, DESCRIPTION_DESC_REMIND_PATIENT + INVALID_ROOM_NUMBER_DESC
                + DESCRIPTION_DESC_REMIND_PATIENT, ParserUtil.MESSAGE_INVALID_NUMBER);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, DESCRIPTION_DESC_REMIND_PATIENT + INVALID_ROOM_NUMBER_DESC
                + INVALID_DATETIME_DUE_VALUE_DESC, ParserUtil.MESSAGE_INVALID_NUMBER);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DESCRIPTION_DESC_REMIND_PATIENT
                + ROOM_NUMBER_DESC_ONE + DATETIME_DUE_DESC_REMIND_PATIENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
