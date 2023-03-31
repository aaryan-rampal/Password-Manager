package model;

import org.json.JSONObject;
import persistence.Writable;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;

// Represents an entry in the password manager including a name, username, password, url, and notes
public class Entry implements Writable {
    private String name;
    private String username;
    private Password password;
    private String masterPassword;
    private String url;
    private String notes;
    private ByteConvertor bc;
    private String algorithm;

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
        algorithm = "SHA-256";
        bc = new ByteConvertor();
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
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

    public String getUrl() {
        return url;
    }

    public String getNotes() {
        return notes;
    }

    /**
     * @REQUIRES: name, username, password, url, and notes are not null
     * @EFFECTS: creates a JSONObject and adds the encrypted strings of the fields to it
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        byte[] saltBytes = createSalt();
        Keyset keySet;
        try {
            keySet = new Keyset(masterPassword, algorithm);
        } catch (GeneralSecurityException e) {
            return null;
        }

        json.put("salt", bc.bytesToString(saltBytes));
        encryptField(name, json, "name", saltBytes, keySet);
        encryptField(username, json, "username", saltBytes, keySet);
        encryptField(password.getPassword(), json, "password", saltBytes, keySet);
        encryptField(url, json, "url", saltBytes, keySet);
        encryptField(notes, json, "notes", saltBytes, keySet);

        return json;
    }

    /**
     * @REQUIRES: field is not null, json is not null, salt has 16 elements, keySet is not null
     * @MODIFIES: json
     * @EFFECTS: converts plaintext string to an encrypted string which it then puts into the json object
     */
    private void encryptField(String field, JSONObject json, String nameOfField, byte[] salt, Keyset keySet) {
        byte[] cipherBytes = keySet.encrypt(field, salt);
        json.put(nameOfField, bc.bytesToString(cipherBytes));
    }

    /**
     * @EFFECTS: creates an array of 16 random bytes which will be used as the salt
     */
    private byte[] createSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return saltBytes;
    }

}
