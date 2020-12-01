import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

public class ContactList {
    private List<ContactItem> contacts;
    public ContactList() {
        contacts = new ArrayList<>();
    }
    public boolean validateIndex(int id) {
        try {
            if(id < 0 || id >= size()) {
                throw new InvalidListIndexQueryException();
            }
        } catch(InvalidListIndexQueryException e) {
            System.out.println("WARNING: index is not in range. Progress not saved.\n");
            return false;
        }
        return true;
    }
    public ContactItem validateItem(String firstName, String lastName, String phoneNumber, String emailAddress) {
        try {
            ContactItem newItem = new ContactItem(firstName, lastName, phoneNumber, emailAddress);
            return newItem;

        } catch(InvalidPhoneNumberException e) {
            System.out.println("WARNING: phone number must be in xxx-xxx-xxxx format. Progress not saved.\n");
        } catch(InvalidEmailAddressException e) {
            System.out.println("WARNING: email address must be in x@y.z format. Progress not saved.\n");
        } catch(EmptyContactException e) {
            System.out.println("WARNING: at least one field must be non-empty. Progress not saved.\n");
        }
        return null;
    }
    public void addItem(String firstName, String lastName, String phoneNumber, String emailAddress) {
        ContactItem itemToAdd = validateItem(firstName, lastName, phoneNumber, emailAddress);
        if(itemToAdd != null) contacts.add(itemToAdd);
    }
    public boolean editItem(String firstName, String lastName, String phoneNumber, String emailAddress, int id) {
        ContactItem newItem = validateItem(firstName, lastName, phoneNumber, emailAddress);
        boolean indexIsValid = validateIndex(id);

        if(newItem != null && indexIsValid) {
            ContactItem toModify = contacts.get(id);
            toModify.setFirstName(firstName);
            toModify.setLastName(lastName);
            toModify.setPhoneNumber(phoneNumber);
            toModify.setEmailAddress(emailAddress);
            return true;
        }
        return false;
    }
    public ContactItem remove(int id) {
        boolean indexIsValid = validateIndex(id);
        if(!indexIsValid) return null;
        return contacts.remove(id);
    }
    public ContactItem get(int id) {
        boolean indexIsValid = validateIndex(id);
        if(!indexIsValid) return null;
        return contacts.get(id);
    }
    public int size() {
        return contacts.size();
    }
    public void clear() {
        contacts.clear();
    }
    public boolean emptyCheck() {
        if(size() == 0) {
            System.out.println("Cannot proceed: there are no items in the current list");
            return true;
        }
        return false;
    }
    public void write(String filename) {
        try(Formatter output = new Formatter(filename)) {
            output.format(toString());

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to find the file "+filename);
        }
    }
    public String process(String field) {
        if(field.equals("N/A")) {
            return "";
        }
        return field;
    }
    public void load(String filename) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    filename));

            //skipping the header
            reader.readLine();
            reader.readLine();

            String line = reader.readLine();
            while (line != null) {
                String firstName = process(line.split(":")[1].trim());
                line = reader.readLine();
                String lastName = process(line.split(":")[1].trim());
                line = reader.readLine();
                String phoneNumber = process(line.split(":")[1].trim());
                line = reader.readLine();
                String emailAddress = process(line.split(":")[1].trim());
                reader.readLine();
                line = reader.readLine();

                addItem(firstName, lastName, phoneNumber, emailAddress);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find the file "+filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        String list = "Current Contacts\n---------";
        for(int i = 0; i < contacts.size(); i++) {
            list += '\n';
            list += i+") ";
            list += contacts.get(i).toString();
        }
        return list;
    }
}
