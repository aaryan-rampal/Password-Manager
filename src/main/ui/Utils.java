package ui;

/**
 * Provides
 */
public class Utils {
    private static Utils utils;

    private Utils() {};

    public static Utils getInstance() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }


}
