package seedu.address.logic.parser.room;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.NewCommandTestUtil.AGE_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_AGE_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_PERIOD_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_TEMP_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PERIOD_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.room.EditRoomCommand.MESSAGE_ROOM_NOT_EDITED;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.room.EditRoomCommand;
import seedu.address.model.patient.Age;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.PeriodOfStay;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Temperature;

public class EditRoomCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRoomCommand.MESSAGE_USAGE);

    private static final String VALID_ROOM_NUMBER = "1";
    private static final String INVALID_ROOM_NUMBER = "-1";
    private static final String INVALID_ROOM_INPUT = "a";

    private EditRoomCommandParser parser = new EditRoomCommandParser();

    @Test
    public void parse_invalidInputs_failure() {
        // No field specified
        assertParseFailure(parser, VALID_ROOM_NUMBER, MESSAGE_ROOM_NOT_EDITED);

        // Negative integer input
        assertParseFailure(parser, INVALID_ROOM_NUMBER, MESSAGE_INVALID_FORMAT);

        // Non-integer input
        assertParseFailure(parser, INVALID_ROOM_INPUT, MESSAGE_INVALID_FORMAT);

        // No index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, VALID_NAME_AMY + INVALID_NAME_DESC,
            Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, VALID_NAME_AMY + INVALID_PHONE_DESC,
            Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, VALID_NAME_AMY + INVALID_AGE_DESC,
            Age.MESSAGE_CONSTRAINTS); // invalid age
        assertParseFailure(parser, VALID_NAME_AMY + INVALID_PERIOD_DESC,
            PeriodOfStay.MESSAGE_CONSTRAINTS); // invalid period
        assertParseFailure(parser, VALID_NAME_AMY + INVALID_TEMP_DESC,
            Temperature.MESSAGE_CONSTRAINTS); // invalid temperature

        // Invalid phone followed by valid age
        assertParseFailure(parser, VALID_NAME_AMY + INVALID_PHONE_DESC + AGE_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // Valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // Multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_AGE_DESC + VALID_PERIOD_AMY + VALID_PHONE_AMY,
            Name.MESSAGE_CONSTRAINTS);
    }
}
