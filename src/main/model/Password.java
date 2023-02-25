package model;

import me.gosimple.nbvcxz.Nbvcxz;
import me.gosimple.nbvcxz.resources.Feedback;
import me.gosimple.nbvcxz.scoring.Result;

import java.util.List;

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

    private long findEntropy() {
        return Math.round(result.getEntropy());
    }

    /**
     * @EFFECTS: returns the score (between 1 and 4) of the password calculated by the nbvcxz library
     */
    public int findScore() {
        return result.getBasicScore();
    }

    private List<String> getSuggestions() {
        return feedback.getSuggestion();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

}
