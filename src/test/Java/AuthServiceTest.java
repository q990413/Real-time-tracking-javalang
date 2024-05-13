import org.testng.annotations.Test;

import static com.cab302groupproject.model.AuthService.isValidEmail;
import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {
    @Test
    public void testIsValidEmailEmpty() {
        assertFalse(isValidEmail(""));
    }

    @Test
    public void testIsValidEmailNoUsername() {
        assertFalse(isValidEmail("@gmail.com"));
    }

    @Test
    public void testIsValidEmailNoAtSymbol() {
        assertFalse(isValidEmail("johndoegmail.com"));
    }

    @Test
    public void testIsValidEmailNoDomainName() {
        assertFalse(isValidEmail("johndoe@.com"));
    }

    @Test
    public void testIsValidEmailNoTLDName() {
        assertFalse(isValidEmail("johndoe@gmail."));
    }

    @Test
    public void testIsValidEmailNoDot() {
        assertFalse(isValidEmail("johndoe@gmailcom"));
    }

    @Test
    public void testIsValidEmailCompleteEmail() {
        assertTrue(isValidEmail("johndoe@gmail.com"));
    }
}
