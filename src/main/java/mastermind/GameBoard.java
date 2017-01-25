package mastermind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbash on 1/19/2017.
 */
public class GameBoard {
    private List<UserGuessRequest> userGuesses;
    private ColorCombination computerCombo;

    public GameBoard() {
        userGuesses = new ArrayList<UserGuessRequest>();
    }

    public GameBoard(ColorCombination computerCombo) {
        userGuesses = new ArrayList<UserGuessRequest>();
        this.computerCombo = computerCombo;
    }

    public List<UserGuessRequest> getUserGuesses() {
        return userGuesses;
    }

    public void setUserGuesses(List<UserGuessRequest> userGuesses) {
        this.userGuesses = userGuesses;
    }

    public ColorCombination getComputerCombo() {
        return computerCombo;
    }

    public void setComputerCombo(ColorCombination computerCombo) {
        this.computerCombo = computerCombo;
    }
}
