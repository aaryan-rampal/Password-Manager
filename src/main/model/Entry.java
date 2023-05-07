package model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

// Represents an entry in the password manager including a name, username, password, url, and notes
public class Entry {
    private String name;
    private String username;
    private Password password;
    private String url;
    private String notes;

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

}
