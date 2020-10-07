package seedu.address.logic.parser.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_DATETIME_DUE_FORMAT;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_DATETIME_DUE_VALUE;
import static seedu.address.logic.commands.NewCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_DATETIME_DUE_REMIND_PATIENT;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_DESCRIPTION_REMIND_PATIENT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.DateTimeDue;
import seedu.address.model.task.Description;

public class TaskParserUtilTest {

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> TaskParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION_REMIND_PATIENT);
        assertEquals(expectedDescription, TaskParserUtil.parseDescription(VALID_DESCRIPTION_REMIND_PATIENT));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        String descriptionWithWhitespace = PREAMBLE_WHITESPACE
                + VALID_DESCRIPTION_REMIND_PATIENT + PREAMBLE_WHITESPACE;
        Description expectedDescription = new Description(VALID_DESCRIPTION_REMIND_PATIENT);
        assertEquals(expectedDescription, TaskParserUtil.parseDescription(descriptionWithWhitespace));
    }

    @Test
    public void parseDateTimeDue_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> TaskParserUtil.parseDateTimeDue((Optional<String>) null));
    }

    @Test
    public void parseDateTimeDue_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () ->
                TaskParserUtil.parseDateTimeDue(Optional.of(INVALID_DATETIME_DUE_VALUE)));
    }

    @Test
    public void parseDateTimeDue_dateTimeFormatNotAllowed_throwsParseException() {
        assertThrows(ParseException.class, () ->
                TaskParserUtil.parseDateTimeDue(Optional.of(INVALID_DATETIME_DUE_FORMAT)));
    }

    @Test
    public void parseDateTimeDue_validValueWithoutWhitespace_returnsDateTimeDue() throws Exception {
        DateTimeDue expectedDateTimeDue = new DateTimeDue(VALID_DATETIME_DUE_REMIND_PATIENT);
        assertEquals(expectedDateTimeDue,
                TaskParserUtil.parseDateTimeDue(Optional.of(VALID_DATETIME_DUE_REMIND_PATIENT)));
    }

    @Test
    public void parseDateTimeDue_validValueWithWhitespace_returnsDateTimeDue() throws Exception {
        String dateTimeDueWithWhitespace = PREAMBLE_WHITESPACE
                + VALID_DATETIME_DUE_REMIND_PATIENT + PREAMBLE_WHITESPACE;
        DateTimeDue expectedDateTimeDue = new DateTimeDue(VALID_DATETIME_DUE_REMIND_PATIENT);

        assertEquals(expectedDateTimeDue,
                TaskParserUtil.parseDateTimeDue(Optional.of(dateTimeDueWithWhitespace)));
    }

}
