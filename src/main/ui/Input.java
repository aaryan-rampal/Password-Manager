package ui;

public enum Input {
    CREATE,
    LIST,
    EXIT,
    CUSTOM,
    RANDOM,
    PASSPHRASE,
    PASSWORD,
    SAVE,
    LOAD,
    VIEW,
    DEFAULT;

    Input() { }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    /**
     * @EFFECTS: returns enum corresponding to input or Input.DEFAULT if enum doesn't exist
     */
    public static Input findCorrespondingEnum(String input) {
        Input i;
        try {
            i = Input.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            i = DEFAULT;
        }
        return i;
    }

}
