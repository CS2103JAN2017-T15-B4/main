//@@author A0097141H
package seedu.taskit.model.task;
import seedu.taskit.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's Recurring state in the task manager. Guarantees: immutable;
 */
public class Recurring {

    public static final String MESSAGE_RECURRING_CONSTRAINTS = "Recurring can only occur when an end date is provided.";

    public final boolean isRecurring;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException
     *             if given name string is invalid.
     */
    public Recurring(String recurring) throws IllegalValueException {
    	if(recurring != null && recurring.equals("true")){
    		this.isRecurring = true;
    	}else{
    		this.isRecurring = false;
    	}
    }

    @Override
    public String toString() {
        return this.isRecurring ? "true" : "false";
    }


}
