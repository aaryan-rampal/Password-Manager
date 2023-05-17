package ui;

import java.util.HashMap;
import java.util.Map;

public enum Input {
    CREATE_COMMAND,
    LIST_COMMAND,
    EXIT_COMMAND,
    CUSTOM_COMMAND,
    RANDOM_COMMAND,
    PASSPHRASE_COMMAND,
    PASSWORD_COMMAND,
    SAVE_COMMAND,
    LOAD_COMMAND,
    VIEW_COMMAND;

    Input() { }

    public static Map<Input, String> createMap() {
        Map<Input, String> map = new HashMap<>();
        map.put(CREATE_COMMAND, "create");
        map.put(LIST_COMMAND, "list");
        map.put(EXIT_COMMAND, "exit");
        map.put(CUSTOM_COMMAND, "custom");
        map.put(RANDOM_COMMAND, "random");
        map.put(PASSPHRASE_COMMAND, "passphrase");
        map.put(PASSWORD_COMMAND, "password");
        map.put(SAVE_COMMAND, "save");
        map.put(LOAD_COMMAND, "load");
        map.put(VIEW_COMMAND, "view");
        return map;
    }
}
