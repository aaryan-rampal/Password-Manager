package model;

import me.gosimple.nbvcxz.Nbvcxz;
import me.gosimple.nbvcxz.resources.Feedback;
import me.gosimple.nbvcxz.scoring.Result;

// Represents a password with the plaintext string password, Result field and Feedback field. The latter two are
// provided by Nbvcxz and are used to calculate password strength and potential feedback on bad passwords.
public class Password {
    private String password;
    private Result result;
    private Feedback feedback;

    /**
     * @REQUIRES: password is not null and not an empty string
     * @EFFECTS: creates password object and sets password field to the parameter it was passed; creates a temporary
     * Nbvcxz to instantiate the result and feedback fields based off the parameter
     */
    public Password(String password) {
        Nbvcxz nbvcxz = new Nbvcxz();

        this.password = password;
        result = nbvcxz.estimate(password);
        feedback = result.getFeedback();
    }

    /**
     * @REQUIRES: result is not null
     * @EFFECTS: returns the score (between 1 and 4) of the password calculated by the nbvcxz library
     */
    public int findScore() {
        return result.getBasicScore();
    }

    public String getPassword() {
        return password;
    }
}
