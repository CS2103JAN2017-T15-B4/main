package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.FIELDWORD_BY;
import static seedu.address.logic.parser.CliSyntax.FIELDWORD_FROM;
import static seedu.address.logic.parser.CliSyntax.FIELDWORD_TO;

import java.util.NoSuchElementException;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.IncorrectCommand;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     */
    public Command parse(String args) {
        ArgumentTokenizer argsTokenizer =
                new ArgumentTokenizer(FIELDWORD_BY, FIELDWORD_FROM, FIELDWORD_TO, PREFIX_TAG);
        argsTokenizer.tokenize(args);
        String start = null;
        String end = null;
        //TODOD better way to make these fields optional
        try {
            start = argsTokenizer.getValue(FIELDWORD_FROM).get();
            end = argsTokenizer.getValue(FIELDWORD_TO).get();
        } catch (NoSuchElementException nse) {
            try {
                end = argsTokenizer.getValue(FIELDWORD_BY).get();
            } catch (NoSuchElementException nsee1) {}
        }
        try {
            return new AddCommand(
                    argsTokenizer.getPreamble().get(),
                    start,
                    end,
                    ParserUtil.toSet(argsTokenizer.getAllValues(PREFIX_TAG))
            );
        } catch (NoSuchElementException nsee) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }
}