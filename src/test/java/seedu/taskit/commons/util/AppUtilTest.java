package seedu.taskit.commons.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.taskit.commons.util.AppUtil;

public class AppUtilTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();



    @Test
    public void getImage_exitingImage() {
        assertNotNull(AppUtil.getImage("/images/address_book_32.png"));
    }


    @Test
    public void getImage_nullGiven_assertionError() {
        thrown.expect(AssertionError.class);
        AppUtil.getImage(null);
    }


}
