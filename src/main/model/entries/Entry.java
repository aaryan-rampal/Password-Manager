package model.entries;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.security.Encryptor;
import model.security.Keyset;

import java.security.GeneralSecurityException;

// Represents an entry in the password manager including a name, username, password, url, and notes
public class Entry {
    private String name;
    private String username;
    private Password password;
    private String url;
    private String notes;
    private byte[] salt = Encryptor.getInstance().createSalt();

    /**
     * @REQUIRES: name, username, url, and notes have non-zero length; password is not null
     * @EFFECTS: creates entry object which instantiates all the fields with the parameters that are passed into the
     * constructor
     */
    public Entry(String name, String username, Password password, String url, String notes) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.url = url;
        this.notes = notes;
    }

    @JsonCreator
    /**
     * @REQUIRES: name, username, url, notes, and passowrd have non-zero length
     * @EFFECTS: custom constructor for Entry when reading from json file
     */
    public Entry(@JsonProperty("name") String name,
                 @JsonProperty("username") String username,
                 @JsonProperty("password") String password,
                 @JsonProperty("url") String url,
                 @JsonProperty("notes") String notes) {
        this.name = name;
        this.username = username;
        this.password = new Password(password);
        this.url = url;
        this.notes = notes;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    /**
     * @EFFECTS: custom getter for Jackson using the password text instead of the password object
     */
    @JsonGetter("password")
    public String getPasswordText() {
        return password.getPassword();
    }

    @JsonProperty
    public String getUrl() {
        return url;
    }

    @JsonProperty
    public String getNotes() {
        return notes;
    }

    /**
     * @REQUIRES: name, username, password, url, and notes are not null
     * @EFFECTS: creates a JSONObject and adds the encrypted strings of the fields to it
     */
    public void toJson(String masterPassword) {
        Encryptor encryptor = Encryptor.getInstance();
        String algorithm = "SHA-256";
        try {
            byte[] saltBytes = encryptor.createSalt();
            Keyset keySet = new Keyset(masterPassword, algorithm);
            encryptor.encrypt(name, keySet, saltBytes);
            encryptor.encrypt(username, keySet, saltBytes);
            encryptor.encrypt(password.getPassword(), keySet, saltBytes);
            encryptor.encrypt(url, keySet, saltBytes);
            encryptor.encrypt(notes, keySet, saltBytes);

        } catch (GeneralSecurityException e) {
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Entry entry = (Entry) o;

        if (getName() != null ? !getName().equals(entry.getName()) : entry.getName() != null) {
            return false;
        }
        if (getUsername() != null ? !getUsername().equals(entry.getUsername()) : entry.getUsername() != null) {
            return false;
        }
        if (getPasswordText() != null ? !getPasswordText().equals(entry.getPasswordText())
                : entry.getPasswordText() != null) {
            return false;
        }
        if (getUrl() != null ? !getUrl().equals(entry.getUrl()) : entry.getUrl() != null) {
            return false;
        }
        return getNotes() != null ? getNotes().equals(entry.getNotes()) : entry.getNotes() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + (getNotes() != null ? getNotes().hashCode() : 0);
        return result;
    }
}
