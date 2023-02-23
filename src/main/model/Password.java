package model;

import me.gosimple.nbvcxz.Nbvcxz;
import me.gosimple.nbvcxz.resources.Feedback;
import me.gosimple.nbvcxz.scoring.Result;

import java.util.List;

public class Password {

    private String password;
    private Result result;
    private Feedback feedback;


    public Password(String password) {
        this.password = password;

        Nbvcxz nbvcxz = new Nbvcxz();
        result = nbvcxz.estimate(password);
        feedback = result.getFeedback();
    }

    private long findEntropy() {
        return Math.round(result.getEntropy());
    }

    private List<String> getSuggestions() {
        return feedback.getSuggestion();
    }


}
