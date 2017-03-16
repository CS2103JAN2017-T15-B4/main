package seedu.taskit.logic.parser;

import java.util.regex.Pattern;

import seedu.taskit.logic.parser.ArgumentTokenizer.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final String FIELDWORD_TITLE= new String("title");
    public static final String FIELDWORD_TAG= new String("tag");
    
    /* Patterns definitions */
    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

}