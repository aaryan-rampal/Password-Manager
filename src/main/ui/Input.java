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

}
