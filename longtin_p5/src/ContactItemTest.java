import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactItemTest {
    @Test
    public void creationFailsWithAllBlankValues() {
        assertThrows(EmptyContactException.class, () -> {
            new ContactItem("", "", "", "");
        });
    }
    @Test
    public void creationSucceedsWithBlankFirstName() {
        assertDoesNotThrow(() -> {
            new ContactItem("", "no", "333-333-3333", "lala@leaveme.alone");
        });
    }
    @Test
    public void creationSucceedsWithBlankLastName() {
        assertDoesNotThrow(() -> {
            new ContactItem("name", "", "333-867-5309", "ok@fine.comein");
        });
    }
    @Test
    public void creationSucceedsWithBlankPhoneNumber() {
        assertDoesNotThrow(() -> {
            new ContactItem("hey", "howsit", "", "how@are.you");
        });
    }
    @Test
    public void creationSucceedsWithBlankEmail() {
        assertDoesNotThrow(() -> {
            new ContactItem("hey", "howsit", "333-333-3333", "");
        });
    }
    @Test
    public void creationSucceedsWithNonBlankValues() {
        assertDoesNotThrow(() -> {
            new ContactItem("name", "yes", "333-867-5309", "ok@fine.comein");
        });
    }
    @Test
    public void testToStringNonBlankValues() {
        ContactItem c = new ContactItem("name", "yes", "333-867-5309", "ok@fine.comein");

        StringBuilder cToStr = new StringBuilder();
        cToStr.append("First name: ");
        cToStr.append("name\n");
        cToStr.append("Last name: ");
        cToStr.append("yes\n");
        cToStr.append("Phone Number: ");
        cToStr.append("333-867-5309\n");
        cToStr.append("Email Address: ");
        cToStr.append("ok@fine.comein\n");

        assertEquals(c.toString(), cToStr.toString());
    }
    @Test
    public void testToStringBlankFirstName() {
        ContactItem c = new ContactItem("", "yes", "333-867-5309", "ok@fine.comein");

        StringBuilder cToStr = new StringBuilder();
        cToStr.append("First name: ");
        cToStr.append("N/A\n");
        cToStr.append("Last name: ");
        cToStr.append("yes\n");
        cToStr.append("Phone Number: ");
        cToStr.append("333-867-5309\n");
        cToStr.append("Email Address: ");
        cToStr.append("ok@fine.comein\n");

        assertEquals(c.toString(), cToStr.toString());
    }
    @Test
    public void testToStringBlankLastName() {
        ContactItem c = new ContactItem("name", "", "333-867-5309", "ok@fine.comein");

        StringBuilder cToStr = new StringBuilder();
        cToStr.append("First name: ");
        cToStr.append("name\n");
        cToStr.append("Last name: ");
        cToStr.append("N/A\n");
        cToStr.append("Phone Number: ");
        cToStr.append("333-867-5309\n");
        cToStr.append("Email Address: ");
        cToStr.append("ok@fine.comein\n");

        assertEquals(c.toString(), cToStr.toString());
    }
    @Test
    public void testToStringBlankPhoneNumber() {
        ContactItem c = new ContactItem("name", "yes", "", "ok@fine.comein");

        StringBuilder cToStr = new StringBuilder();
        cToStr.append("First name: ");
        cToStr.append("name\n");
        cToStr.append("Last name: ");
        cToStr.append("yes\n");
        cToStr.append("Phone Number: ");
        cToStr.append("N/A\n");
        cToStr.append("Email Address: ");
        cToStr.append("ok@fine.comein\n");

        assertEquals(c.toString(), cToStr.toString());
    }
    @Test
    public void testToStringBlankEmail() {
        ContactItem c = new ContactItem("name", "yes", "333-867-5309", "");

        StringBuilder cToStr = new StringBuilder();
        cToStr.append("First name: ");
        cToStr.append("name\n");
        cToStr.append("Last name: ");
        cToStr.append("yes\n");
        cToStr.append("Phone Number: ");
        cToStr.append("333-867-5309\n");
        cToStr.append("Email Address: ");
        cToStr.append("N/A\n");

        assertEquals(c.toString(), cToStr.toString());
    }
}