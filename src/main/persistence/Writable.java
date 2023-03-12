package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS:
    JSONObject toJson();
}
