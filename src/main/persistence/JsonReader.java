//package persistence;
//
//import model.File;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.stream.Stream;
//
//import org.json.*;
//
//// Represents a reader that reads workroom from JSON data stored in file
//public class JsonReader {
//    private String source;
//
//    // EFFECTS: constructs reader to read from source file
//    public JsonReader(String source) {
//        this.source = source;
//    }
//
//    // EFFECTS: reads workroom from file and returns it;
//    // throws IOException if an error occurs reading data from file
//    public File read() throws IOException {
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parseFile(jsonObject);
//    }
//
//    // EFFECTS: reads source file as string and returns it
//    private String readFile(String source) throws IOException {
//        StringBuilder contentBuilder = new StringBuilder();
//
//        try (Stream<String> stream = Files.lines( Paths.get(source), StandardCharsets.UTF_8)) {
//            stream.forEach(s -> contentBuilder.append(s));
//        }
//
//        return contentBuilder.toString();
//    }
//
//    // EFFECTS: parses workroom from JSON object and returns it
//    private File parseFile(JSONObject jsonObject) {
//        String name = jsonObject.getString("name");
//        File f = new File(name);
//        addThingies(f, jsonObject);
//        return f;
//    }
//
//    // MODIFIES: wr
//    // EFFECTS: parses thingies from JSON object and adds them to workroom
//    private void addThingies(File f, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("thingies");
//        for (Object json : jsonArray) {
//            JSONObject nextThingy = (JSONObject) json;
//            addThingy(f, nextThingy);
//        }
//    }
//
//    // MODIFIES: wr
//    // EFFECTS: parses thingy from JSON object and adds it to workroom
////    private void addThingy(File f, JSONObject jsonObject) {
////        String name = jsonObject.getString("name");
////        Category category = Category.valueOf(jsonObject.getString("category"));
////        Thingy thingy = new Thingy(name, category);
////        f.addThingy(thingy);
////    }
//}
