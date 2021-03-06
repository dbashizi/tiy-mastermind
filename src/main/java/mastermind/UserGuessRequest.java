package mastermind;

import java.util.List;

/**
 * Created by dbash on 1/24/2017.
 */
public class UserGuessRequest {
    private String color1;
    private String color2;
    private String color3;
    private String color4;
    private List<ColorMatchingResult> matchingResults;

    public UserGuessRequest() {
    }

    public UserGuessRequest(String color1, String color2, String color3, String color4) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
    }

    public List<ColorMatchingResult> getMatchingResults() {
        return matchingResults;
    }

    public void setMatchingResults(List<ColorMatchingResult> matchingResults) {
        this.matchingResults = matchingResults;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getColor3() {
        return color3;
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }

    public String getColor4() {
        return color4;
    }

    public void setColor4(String color4) {
        this.color4 = color4;
    }
}
