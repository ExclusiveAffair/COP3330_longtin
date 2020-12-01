import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactListTest {
    @Test
    public void editingFailsWithAllBlankValues() {
        ContactList c = new ContactList();
        c.addItem("aa", "bb", "333-333-3333", "ok@uh.com");
        assertEquals(c.editItem("", "", "", "", 0), false);
    }
    @Test
    public void editingSucceedsWithBlankFirstName() {
        ContactList c = new ContactList();
        c.addItem("aa", "bb", "333-333-3333", "ok@uh.com");
        assertEquals(c.editItem("", "well", "123-456-7890", "yes@yes.yes", 0), true);
    }
    @Test
    public void editingSucceedsWithBlankLastName() {
        ContactList c = new ContactList();
        c.addItem("aa", "bb", "333-333-3333", "ok@uh.com");
        assertEquals(c.editItem("no", "", "404-404-4040", "no@no.no", 0), true);
    }
    @Test
    public void editingSucceedsWithBlankPhoneNumber() {
        ContactList c = new ContactList();
        c.addItem("aa", "bb", "333-333-3333", "ok@uh.com");
        assertEquals(c.editItem("alright", "alrighttt", "", "ok@ok.ok", 0), true);
    }
    @Test
    public void editingSucceedsWithBlankEmailAddress() {
        ContactList c = new ContactList();
        c.addItem("aa", "bb", "333-333-3333", "ok@uh.com");
        assertEquals(c.editItem("lala", "dudu", "123-456-1230", "", 0), true);
    }
    @Test
    public void editingFailsWithTooLowIndex() {
        ContactList c = new ContactList();
        c.addItem("aa", "bb", "333-333-3333", "ok@uh.com");
        assertEquals(c.editItem("lala", "dudu", "123-456-1230", "", -1), false);
    }
    @Test
    public void editingFailsWithTooHighIndex() {
        ContactList c = new ContactList();
        c.addItem("aa", "bb", "333-333-3333", "ok@uh.com");
        assertEquals(c.editItem("lala", "dudu", "123-456-1230", "", 1), false);
    }
    @Test
    public void addingItemsIncreasesSize() {
        ContactList c = new ContactList();
        int origSize = c.size();
        c.addItem("aa", "bb", "333-333-3333", "ok@uh.com");
        int newSize = c.size();
        assertTrue(newSize > origSize);
    }
    @Test
    public void removingItemsDecreasesSize() {
        ContactList c = new ContactList();
        c.addItem("aa", "bb", "333-333-3333", "ok@uh.com");
        int origSize = c.size();
        c.remove(0);
        int newSize = c.size();
        assertTrue(newSize < origSize);
    }
    @Test
    public void removingItemFailsWithInvalidIndex() {
        ContactList c = new ContactList();
        c.addItem("aa", "bb", "333-333-3333", "ok@uh.com");
        ContactItem x = c.remove(1);
        assertEquals(x, null);
    }
    @Test
    public void removingItemSucceedsWithValidIndex() {
        ContactList c = new ContactList();
        c.addItem("aa", "bb", "333-333-3333", "ok@uh.com");
        ContactItem x = c.remove(0);
        assertNotEquals(x, null);
    }
    @Test
    public void newListIsEmpty() {
        ContactList c = new ContactList();
        assertEquals(c.size(), 0);
    }
    @Test
    public void savedListCanBeLoaded() {
        ContactList c = new ContactList();
        c.addItem("aa", "bb", "333-333-3333", "ok@uh.com");
        c.addItem("and again", "", "123-456-1230", "again@and.again");
        c.write("again.txt");

        ContactList d = new ContactList();
        d.load("again.txt");

        assertEquals(c.size(), d.size());
        assertEquals(c.get(0).toString(), d.get(0).toString());
        assertEquals(c.get(1).toString(), d.get(1).toString());
    }
}