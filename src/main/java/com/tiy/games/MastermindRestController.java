package com.tiy.games;

import mastermind.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbash on 1/24/2017.
 */
@RestController
public class MastermindRestController {

    @RequestMapping(path = "/get-random-color-combination.json", method = RequestMethod.GET)
    public ColorCombination getRandomColorCombination() {
        return ColorCombination.getRandomUniqueCombination();
    }

    @RequestMapping(path = "/submit-user-guess.json", method = RequestMethod.POST)
    public GameBoard submitUserGuess(HttpSession session, @RequestBody UserGuessRequest userGuessRequest) {
        GameBoard gameBoard = (GameBoard)session.getAttribute("game-board");
        if (gameBoard == null) {
            ColorCombination computerCombo = ColorCombination.getRandomUniqueCombination();
            gameBoard = new GameBoard(computerCombo);
            session.setAttribute("game-board", gameBoard);
        }

        List<MastermindColor> userColors = new ArrayList<MastermindColor>();
        userColors.add(MastermindColor.valueOf(userGuessRequest.getColor1().toUpperCase()));
        userColors.add(MastermindColor.valueOf(userGuessRequest.getColor2().toUpperCase()));
        userColors.add(MastermindColor.valueOf(userGuessRequest.getColor3().toUpperCase()));
        userColors.add(MastermindColor.valueOf(userGuessRequest.getColor4().toUpperCase()));

        ColorCombination userColorCombo = new ColorCombination(userColors);

        List<ColorMatchingResult> matchingResults = gameBoard.getComputerCombo().compare(userColorCombo);
        userGuessRequest.setMatchingResults(matchingResults);
        gameBoard.getUserGuesses().add(userGuessRequest);

        return gameBoard;
    }
}
