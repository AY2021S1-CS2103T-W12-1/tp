package seedu.address.logic.parser.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_COMMENTS;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_PERIOD_OF_STAY;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_TEMP;

import seedu.address.logic.commands.patient.EditPatientCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditPatientCommand object.
 */
public class EditPatientCommandParser implements Parser<EditPatientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPatientCommand
     * and returns an EditPatientCommand object for execution.
     *
     * @param args that are inputted by the user.
     * @return EditPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditPatientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_COMMENTS, PREFIX_TEMP, PREFIX_AGE, PREFIX_PERIOD_OF_STAY);

        String patientTobeEdited = argMultimap.getPreamble().trim().toLowerCase();
        if (args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPatientCommand.MESSAGE_USAGE));
        }
        EditPatientCommand.EditPatientDescriptor editPatientDescriptor = new EditPatientCommand.EditPatientDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPatientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPatientDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_TEMP).isPresent()) {
            editPatientDescriptor.setTemperature(ParserUtil.parseTemperature(argMultimap.getValue(PREFIX_TEMP).get()));
        }
        if (argMultimap.getValue(PREFIX_AGE).isPresent()) {
            editPatientDescriptor.setAge(ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get()));
        }
        if (argMultimap.getValue(PREFIX_PERIOD_OF_STAY).isPresent()) {
            editPatientDescriptor.setPeriodOfStay(ParserUtil
                    .parsePeriodOfStay(argMultimap.getValue(PREFIX_PERIOD_OF_STAY).get()));
        }
        if (argMultimap.getValue(PREFIX_COMMENTS).isPresent()) {
            editPatientDescriptor.setComment(ParserUtil.parseComment(argMultimap.getValue(PREFIX_COMMENTS).get()));
        }

        if (!editPatientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPatientCommand.MESSAGE_PATIENT_NOT_EDITED);
        }

        return new EditPatientCommand(patientTobeEdited, editPatientDescriptor);
    }

}


