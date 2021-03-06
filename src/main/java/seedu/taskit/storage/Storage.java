package seedu.taskit.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.taskit.commons.events.model.TaskManagerChangedEvent;
import seedu.taskit.commons.events.storage.DataSavingExceptionEvent;
import seedu.taskit.commons.exceptions.DataConversionException;
import seedu.taskit.model.ReadOnlyTaskManager;
import seedu.taskit.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TaskManagerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;

    @Override
    String getAddressBookFilePath();

    @Override
    Optional<ReadOnlyTaskManager> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyTaskManager addressBook) throws IOException;

    /**
     * Saves the current version of the Address Book to the hard disk.
     *   Creates the data file if it is missing.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving.
     */
    void handleAddressBookChangedEvent(TaskManagerChangedEvent abce);
}
