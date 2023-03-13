package persistence;

import org.json.JSONObject;

// Interface for classes that are meant to be converted to a JSON object/array
public interface Writable {
    JSONObject toJson();
}
