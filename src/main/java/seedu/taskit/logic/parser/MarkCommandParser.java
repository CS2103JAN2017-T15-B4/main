package seedu.taskit.logic.parser;

import static seedu.taskit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;
import java.util.Optional;

import seedu.taskit.logic.commands.Command;
import seedu.taskit.logic.commands.IncorrectCommand;
import seedu.taskit.logic.commands.MarkCommand;

public class MarkCommandParser {
    
    public Command parse(String args) {
        List<Optional<String>> markInformation = ParserUtil.splitArgument(args.trim(),2);
        
        Optional<Integer> index = markInformation.get(0).flatMap(ParserUtil::parseIndex);
        if (!index.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_NOT_MARKED));
        }
        
        Optional<String> parameter = markInformation.get(1).flatMap(ParserUtil::parseParameter);
        if (!parameter.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_NOT_MARKED));
        }
        
        if(parameter.get().equals("done")||parameter.get().equals("undone")){
            return new MarkCommand (index.get(), parameter.get());
        } else {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }
    }
}
