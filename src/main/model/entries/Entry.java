package model.entries;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.gosimple.nbvcxz.matching.match.Match;
import me.gosimple.nbvcxz.scoring.Result;
import model.security.Decryptor;
import model.security.Encryptor;
import model.security.Keyset;

import java.security.GeneralSecurityException;
import java.util.List;

// Represents an entry in the password manager including a name, username, password, url, and notes
public class Entry {
    private String name;
    private String username;
    private Password password;
    private String url;
    private String notes;
    private byte[] saltBytes;
    private static Encryptor encryptor = Encryptor.getInstance();
    private static Decryptor decryptor = Decryptor.getInstance();
    private static Keyset keySet;
    private static final String ALGORITHM = "SHA-256";

    private void setUpEncryptionFields() {
//        encryptor = Encryptor.getInstance();
//        decryptor = Decryptor.getInstance();
        saltBytes = encryptor.createSalt();
    }

    public static void instantiateKeySet(String masterPassword) {
        try {
            keySet = new Keyset(masterPassword, ALGORITHM);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

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
        setUpEncryptionFields();
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

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    /**
     * @EFFECTS: custom getter for Jackson using the password text instead of the password object
     */
    @JsonIgnore
    public String getPasswordText() {
        return password.getPassword();
    }

    public String getUrl() {
        return url;
    }

    public String getNotes() {
        return notes;
    }

    public byte[] getSaltBytes() {
        return saltBytes;
    }

    @JsonGetter("notes")
    public String getEncryptedNotes() {
        return encryptor.encrypt(notes, keySet, saltBytes);
    }

    @JsonGetter("url")
    public String getEncryptedUrl() {
        return encryptor.encrypt(url, keySet, saltBytes);
    }

    @JsonGetter("name")
    public String getEncryptedName() {
        return encryptor.encrypt(name, keySet, saltBytes);
    }

    @JsonGetter("password")
    public String getEncryptedPassword() {
        return encryptor.encrypt(password.getPassword(), keySet, saltBytes);
    }

    @JsonGetter("username")
    public String getEncryptedUsername() {
        return encryptor.encrypt(username, keySet, saltBytes);
    }
    /**
     * @REQUIRES: name, username, password, url, and notes are not null
     * @EFFECTS: creates a JSONObject and adds the encrypted strings of the fields to it
     */
    public Entry decrypt() throws GeneralSecurityException {
        String name = decryptor.decrypt(this.name, saltBytes, keySet);
        String username = decryptor.decrypt(this.username, saltBytes, keySet);
        String password = decryptor.decrypt(this.password.getPassword(), saltBytes, keySet);
        String url = decryptor.decrypt(this.url, saltBytes, keySet);
        String notes = decryptor.decrypt(this.notes, saltBytes, keySet);
        return new Entry(name, username, new Password(password), url, notes);
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

    public String toString(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("Entry #" + (i + 1));
        sb.append("\nName: " + name);
        sb.append("\nUsername: " + username);
        sb.append("\nPassword: " + password.getPassword());
        sb.append("\nPassword rating: " + parseScore());
        sb.append("\nURL: " + url);
        sb.append("\nNotes: " + url);
        sb.append("\n");
        return sb.toString();
    }

    private String parseScore() {
        String stars = "";
        for (int i = 0; i < password.findScore()+1; i++) {
            stars += "*";
        }
        return stars;
    }

    public StringBuilder detailedView() {
        StringBuilder sb = new StringBuilder();
        List<String> suggestions = password.getFeedback().getSuggestion();

        sb.append("Suggestions: ");
        if (suggestions.size() == 0) sb.append("None. Strong password!");
        else {
            for (int i = 0; i < suggestions.size(); i++) {
                String s = suggestions.get(i);
                if (i == suggestions.size() - 1) sb.append(s);
                else sb.append(s + ", ");
            }
        }

        String warning = password.getFeedback().getWarning();
        sb.append("\nWarning: " + ((warning== null) ? "None. Strong password!" : warning));

        Result result = password.getResult();
        sb.append("\nPassword entropy (higher the better): " + result.getEntropy().shortValue());
        sb.append("\nNumber of guesses to crack: " + result.getGuesses());

        return sb;
    }

}
