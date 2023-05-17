package model.entries;

import me.gosimple.nbvcxz.Nbvcxz;
import me.gosimple.nbvcxz.resources.Feedback;
import me.gosimple.nbvcxz.scoring.Result;

// Represents a password with the plaintext string password, Result field and Feedback field. The latter two are
// provided by Nbvcxz and are used to calculate password strength and potential feedback on bad passwords.
public class Password {
    private String passwordText;
    private Result result;
    private Feedback feedback;

    /**
     * @REQUIRES: password is not null and not an empty string
     * @EFFECTS: creates password object and sets password field to the parameter it was passed; creates a temporary
     * Nbvcxz to instantiate the result and feedback fields based off the parameter
     */
    public Password(String passwordText) {
        Nbvcxz nbvcxz = new Nbvcxz();

        this.passwordText = passwordText;
        result = nbvcxz.estimate(passwordText);
        feedback = result.getFeedback();
    }

    public Result getResult() {
        return result;
    }

    /**
     * @REQUIRES: result is not null
     * @EFFECTS: returns the score (between 1 and 4) of the password calculated by the nbvcxz library
     */
    public int findScore() {
//        return getBasicScore();
        return result.getBasicScore();
    }

    private int getBasicScore() {
        double entropy = result.getEntropy();
        if (entropy <= 10) {
            return 0;
        } else if (entropy <= 32) {
            return 1;
        } else if (entropy <= 64) {
            return 2;
        } else {
            return entropy <= 70 ? 3 : 4;
        }
    }

    public String getPasswordText() {
        return passwordText;
    }

    public Feedback getFeedback() {
        return feedback;
    }
}
