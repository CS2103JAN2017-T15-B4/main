package seedu.address.testutil;

import seedu.taskit.commons.exceptions.IllegalValueException;
import seedu.taskit.model.AddressBook;
import seedu.taskit.model.task.Task;
import seedu.taskit.model.task.UniqueTaskList;

public class TypicalTestTasks {
    public TestTask hw1, hw2, lunch, interview;

    public TypicalTestTasks() {
        try {
            hw1 = new TaskBuilder().withTitle("Do HW 1")
                    .withTags("school").build();
            hw2 = new TaskBuilder().withTitle("Do HW 2")
                    .withTags("school").build();
            lunch = new TaskBuilder().withTitle("Lunch with Bob")
                    .withTags("leisure", "friends").build();
            interview = new TaskBuilder().withTitle("Interview for big company")
                    .withTags("career").build();
                   
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadAddressBookWithSampleData(AddressBook ab) {
        for (TestTask task : new TypicalTestTasks().getTypicalTasks()) {
            try {
                ab.addTask(new Task(task));
            } catch (UniqueTaskList.DuplicateTaskException e) {
                assert false : "not possible";
            }
        }
    }

    public TestTask[] getTypicalTasks() {
        return new TestTask[]{hw1, hw2, lunch, interview};
    }

    public AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        loadAddressBookWithSampleData(ab);
        return ab;
    }
}
