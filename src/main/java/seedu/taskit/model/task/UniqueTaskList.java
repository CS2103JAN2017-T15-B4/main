// @@author A0163996J
package seedu.taskit.model.task;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taskit.commons.core.UnmodifiableObservableList;
import seedu.taskit.commons.exceptions.DuplicateDataException;
import seedu.taskit.commons.util.CollectionUtil;
import seedu.taskit.model.task.Task;

import static seedu.taskit.logic.parser.CliSyntax.DONE;
import static seedu.taskit.logic.parser.CliSyntax.UNDONE;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see FloatingTask#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueTaskList implements Iterable<Task> {

    private ObservableList<Task> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(ReadOnlyTask toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }

    /**
     * Adds a task to the list.
     *
     * @throws DuplicateTaskException if the task to add is a duplicate of an existing task in the list.
     */
    public void add(Task toAdd) throws DuplicateTaskException {
        assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
        sortTasks();

    }

    // @@author generated

    /**
     * Updates the task in the list at position {@code index} with {@code editedTask}.
     *
     * @throws DuplicateTaskException if updating the task's details causes the task to be equivalent to
     *      another existing task in the list.
     * @throws IndexOutOfBoundsException if {@code index} < 0 or >= the size of the list.
     */
    public void updateTask(int index, ReadOnlyTask editedTask) throws DuplicateTaskException {
        assert editedTask != null;

        Task taskToUpdate = internalList.get(index);
        if (!taskToUpdate.equals(editedTask) && internalList.contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        taskToUpdate.resetData(editedTask);
        // TODO: The code below is just a workaround to notify observers of the updated task.
        // The right way is to implement observable properties in the Task class.
        // Then, TaskCard should then bind its text labels to those observable properties.
        internalList.set(index, taskToUpdate);
        sortTasks();
    }

    //@@author A0141872E
    /**
     * Mark the task in the list {@code taskToMark} with {@code parameter}.
     *
     * @throws DuplicateMarkingException if task is already be marked as done or undone
     * @throws IndexOutOfBoundsException if {@code index} < 0 or >= the size of the list.
     */
    public void markTask(ReadOnlyTask taskToMark,String parameter)
            throws UniqueTaskList.DuplicateMarkingException {
        assert taskToMark != null;

        int index = internalList.indexOf(taskToMark);
        Task targetTask = internalList.get(index);

        if((taskToMark.isDone() == true && parameter.equals(DONE))
                ||(taskToMark.isDone() == false && parameter.equals(UNDONE))) {
            throw new DuplicateMarkingException();
        }

        targetTask.setDone(parameter);
        internalList.set(index, targetTask);
        sortTasks();
    }//@@author

 // @@author A0163996J

    /**
     * Removes the equivalent task from the list.
     *
     * @throws TaskNotFoundException if no such task could be found in the list.
     */
    public boolean remove(ReadOnlyTask toRemove) throws TaskNotFoundException {
        assert toRemove != null;
        final boolean taskFoundAndDeleted = internalList.remove(toRemove);
        if (!taskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
        sortTasks();
        return taskFoundAndDeleted;
    }

    /**
     * Sorts task using Comparable
     */
    public void sortTasks() {
        FXCollections.sort(this.internalList);
    }

    public void setTasks(UniqueTaskList replacement) {
        this.internalList.setAll(replacement.internalList);
        sortTasks();

    }

    public void setTasks(List<? extends ReadOnlyTask> tasks) throws DuplicateTaskException {
        UniqueTaskList replacement = new UniqueTaskList();
        for (final ReadOnlyTask task : tasks) {
            replacement.add(new Task(task));
        }
        setTasks(replacement);
    }

    public UnmodifiableObservableList<Task> asObservableList() {
        return new UnmodifiableObservableList<>(internalList);
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTaskList // instanceof handles nulls
                && this.internalList.equals(
                ((UniqueTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateTaskException extends DuplicateDataException {
        protected DuplicateTaskException() {
            super("Operation would result in duplicate tasks");
        }
    }

    /**
     * Signals that an mark operation want to mark an done task as done or undone task as undone.
     */
    public static class DuplicateMarkingException extends DuplicateDataException {
        protected DuplicateMarkingException() {
            super("This task is alreadly marked");
        }
    }

    /**
     * Signals that an operation targeting a specified task in the list would fail because
     * there is no such matching task in the list.
     */
    public static class TaskNotFoundException extends Exception {}
}
