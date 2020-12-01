public class ContactItem {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    public ContactItem(String firstName, String lastName, String phoneNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        if(isPhoneNumberValid(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new InvalidPhoneNumberException();
        }
        if(isEmailAddressValid(emailAddress)) {
            this.emailAddress = emailAddress;
        } else {
            throw new InvalidEmailAddressException();
        }
        if(numFields(firstName, lastName, phoneNumber, emailAddress) < 1) {
            throw new EmptyContactException();
        }
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setFirstName(String newFirstName) {
        if(numFields(newFirstName, lastName, phoneNumber, emailAddress) < 1) {
            throw new EmptyContactException();
        } else {
            firstName = newFirstName;
        }
    }
    public void setLastName(String newLastName) {
        if(numFields(firstName, newLastName, phoneNumber, emailAddress) < 1) {
            throw new EmptyContactException();
        } else {
            lastName = newLastName;
        }
    }
    public void setPhoneNumber(String newPhoneNumber) {
        if(!isPhoneNumberValid(newPhoneNumber)) {
            throw new InvalidPhoneNumberException();
        } else if(numFields(firstName, lastName, newPhoneNumber, emailAddress) < 1) {
            throw new EmptyContactException();
        } else {
            phoneNumber = newPhoneNumber;
        }
    }
    public void setEmailAddress(String newEmailAddress) {
        if(!isEmailAddressValid(newEmailAddress)) {
            throw new InvalidEmailAddressException();
        } else if(numFields(firstName, lastName, phoneNumber, newEmailAddress) < 1) {
            throw new EmptyContactException();
        } else {
            emailAddress = newEmailAddress;
        }
    }

    public boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.matches("(^$)|(^\\d{3}-\\d{3}-\\d{4}$)");
    }
    public boolean isEmailAddressValid(String emailAddress) {
        return emailAddress.matches("(^$)|(^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$)");
    }
    public int numFields(String firstName, String lastName, String phoneNumber, String emailAddress) {
        int ct = 0;
        if(firstName.length() >= 1) {
            ct++;
        }
        if(lastName.length() >= 1) {
            ct++;
        }
        if(phoneNumber.length() >= 1) {
            ct++;
        }if(emailAddress.length() >= 1) {
            ct++;
        }
        return ct;
    }

    @Override
    public String toString() {
        StringBuilder contact = new StringBuilder();
        contact.append("First name: ");
        contact.append((firstName.length() == 0 ? "N/A" : firstName) + "\n");
        contact.append("Last name: ");
        contact.append((lastName.length() == 0 ? "N/A" : lastName) + "\n");
        contact.append("Phone Number: ");
        contact.append((phoneNumber.length() == 0 ? "N/A" : phoneNumber) + "\n");
        contact.append("Email Address: ");
        contact.append((emailAddress.length() == 0 ? "N/A" : emailAddress) + "\n");

        return contact.toString();
    }
}
class InvalidPhoneNumberException extends IllegalArgumentException {}
class InvalidEmailAddressException extends IllegalArgumentException {}
class EmptyContactException extends IllegalArgumentException {}