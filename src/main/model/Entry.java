package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

// Represents an entry in the password manager including a name, username, password, url, and notes
public class Entry implements Writable {
    private final String name;
    private final String username;
    private final Password password;
    private final String url;
    private final String notes;

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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        String salt = createSalt();

        json.put("salt", salt);
        encryptPassword(name, json, "name", salt);
        encryptPassword(username, json, "username", salt);
        encryptPassword(password.getPassword(), json, "password", salt);
        encryptPassword(url, json, "url", salt);
        encryptPassword(notes, json, "notes", salt);

        return json;
    }

    private void encryptPassword(String password, JSONObject json, String nameOfField, String salt) {
        Keyset keySet = new Keyset("A");

        byte[] cipherBytes = keySet.encrypt(password, salt);
        String cipherText = convertBytesToString(cipherBytes);

//        json.put(nameOfField, convertToJSONArray(cipherBytes));
        json.put(nameOfField, Base64.getDecoder().decode(cipherBytes));
    }

    private JSONArray convertToJSONArray(byte[] cipherBytes) {
        JSONArray jsArray = new JSONArray();
        for (int i = 0; i < cipherBytes.length; i++) {
            jsArray.put(cipherBytes[i]);
        }
        return jsArray;
    }

    private String createSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        String salt = convertBytesToString(saltBytes);
        return salt;
    }

    private String convertBytesToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

}
