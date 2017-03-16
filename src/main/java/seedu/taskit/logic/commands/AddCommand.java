package seedu.taskit.logic.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.taskit.commons.exceptions.IllegalValueException;
import seedu.taskit.logic.commands.exceptions.CommandException;
import seedu.taskit.model.tag.Tag;
import seedu.taskit.model.tag.UniqueTagList;
import seedu.taskit.model.task.Task;
import seedu.taskit.model.task.Title;
import seedu.taskit.model.task.UniqueTaskList;

/**
 * Add a new task to TaskIt
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to TaskIt. "
            + "Parameters: TITLE [t/TAG]...\n"
            + "Example: " + COMMAND_WORD
            + " Finish SWE project t/school";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in TaskIt";

    private final Task toAdd;

    /**
     * Creates an AddCommand using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String title, Set<String> tags)
            throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Task(
                new Title(title),
                new UniqueTagList(tagSet)
        );
    }

    @Override
    public CommandResult execute() throws CommandException {
        assert model != null;
        try {
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

    }

}