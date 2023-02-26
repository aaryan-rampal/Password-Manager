package model;

import me.gosimple.nbvcxz.Nbvcxz;
import me.gosimple.nbvcxz.resources.Feedback;
import me.gosimple.nbvcxz.scoring.Result;

public class Password {
    private String password;
    private Result result;
    private Feedback feedback;

    /**
     * @REQUIRES: password is not null
     * @EFFECTS: creates password object and sets password field to the parameter it was passed; creates a temporary
     * Nbvcxz to instantiate the result and feedback fields
     */
    public Password(String password) {
        this.password = password;

        Nbvcxz nbvcxz = new Nbvcxz();
        result = nbvcxz.estimate(password);
        feedback = result.getFeedback();
    }

    /**
     * @EFFECTS: returns the score (between 1 and 4) of the password calculated by the nbvcxz library
     */
    public int findScore() {
        return result.getBasicScore();
    }

    public String getPassword() {
        return password;
    }
}
