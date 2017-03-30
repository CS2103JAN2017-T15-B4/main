package seedu.taskit.testutil;

import seedu.taskit.commons.exceptions.IllegalValueException;
import seedu.taskit.model.tag.Tag;
import seedu.taskit.model.tag.UniqueTagList;
import seedu.taskit.model.task.Title;
import seedu.taskit.model.task.Priority;


/**
 *
 */
public class TaskBuilder {
    //@@author A0141011J
    private TestTask task;

    public TaskBuilder() {
        this.task = new TestTask();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(TestTask taskToCopy) {
        this.task = new TestTask(taskToCopy);
    }

    public TaskBuilder withTitle(String title) throws IllegalValueException {
        this.task.setTitle(new Title(title));
        return this;
    }

    public TaskBuilder withPriority(String priority) throws IllegalValueException {
        this.task.setPriority(new Priority(priority));
        return this;
    }

    public TaskBuilder withTags(String ... tags) throws IllegalValueException {
        task.setTags(new UniqueTagList());
        for (String tag: tags) {
            task.getTags().add(new Tag(tag));
        }
        return this;
    }

    public TestTask build() {
        return this.task;
    }

}
