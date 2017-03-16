package seedu.address.testutil;

import seedu.taskit.commons.exceptions.IllegalValueException;
import seedu.taskit.model.AddressBook;
import seedu.taskit.model.person.Person;
import seedu.taskit.model.person.UniquePersonList;
import seedu.taskit.model.tag.Tag;
import seedu.taskit.model.task.Task;
import seedu.taskit.model.task.UniqueTaskList;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").withTag("Friend").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public AddressBookBuilder withTask(Task task) throws UniqueTaskList.DuplicateTaskException {
        addressBook.addTask(task);
        return this;
    }

    public AddressBookBuilder withTag(String tagName) throws IllegalValueException {
        addressBook.addTag(new Tag(tagName));
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
