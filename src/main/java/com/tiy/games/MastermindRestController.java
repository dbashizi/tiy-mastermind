package com.tiy.games;

import mastermind.ColorCombination;
import mastermind.ColorMatchingResult;
import mastermind.MastermindColor;
import mastermind.UserGuessRequest;
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
    public List<ColorMatchingResult> submitUserGuess(HttpSession session, @RequestBody UserGuessRequest userGuessRequest) {
        ColorCombination computerCombo = (ColorCombination)session.getAttribute("computer-combo");
        if (computerCombo == null) {
            computerCombo = ColorCombination.getRandomUniqueCombination();
            session.setAttribute("computer-combo", computerCombo);
        }
        List<MastermindColor> userColors = new ArrayList<MastermindColor>();
        userColors.add(MastermindColor.valueOf(userGuessRequest.getColor1().toUpperCase()));
        userColors.add(MastermindColor.valueOf(userGuessRequest.getColor2().toUpperCase()));
        userColors.add(MastermindColor.valueOf(userGuessRequest.getColor3().toUpperCase()));
        userColors.add(MastermindColor.valueOf(userGuessRequest.getColor4().toUpperCase()));

        ColorCombination userColorCombo = new ColorCombination(userColors);

        return computerCombo.compare(userColorCombo);
    }



}
